package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.LoginTO;
import model.User;
import base.EnumRemoteObject;
import base.RemoteNotify;
import base.RmiService;
import base.Util;


/**
 * Class that has the login controls, extends Restarter so its location is explicit to the RMI access.
 */
public class CtrlLogin extends UnicastRemoteObject implements RemoteNotify {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
    /** The remote interface. */
    public static RmiService remoteService;
    
    /** The ero. */
    private EnumRemoteObject ero = EnumRemoteObject.LOGIN;
  
    /** Pub/Sub for JMS */
    private JmsClientTopic ctrlJms;
    /**
     * Method that accesses the information in the User Login.
     *
     * @throws RemoteException the remote exception
     */
	public CtrlLogin() throws RemoteException {
        super();

		try {
			remoteService = Util.getRemoteService();
		} catch (RemoteException e){
			System.out.println("Message: " + "\nException: " + e.toString());
		}
        
    }
		
    /**
     * Performs the user's login.
     *
     * @param loginTO the login transfer object
     * @return the login to
     */
    public LoginTO doLogin(LoginTO loginTO) {
    	
    	if (loginTO != null)
    	{
			try {
				loginTO.setUserPassword(Util.GeraMD5(loginTO.getUserPassword()));
				remoteService = Util.getRemoteService();
				
				if (ctrlJms == null){
					//Instancia do Jms
					ctrlJms = new JmsClientTopic(loginTO, this);
				}
				
				//remoteService.sendMessage("CtrlLogin Login: " + loginTO.getUserLogin(), this.ero);
				loginTO = remoteService.executeLogin(loginTO);
				
				if (loginTO.isValidated()){
					//remoteService.addObserver(this, this.ero, loginTO);
					//remoteService.sendMessage(loginTO, this.ero, "CtrlLogin: " + loginTO.getUserLogin());
					
					// Chamada do JMS
					//ctrlJms.sendMessage(loginTO, this.ero, loginTO.getUserLogin());
				}
				return loginTO;
	   	 		
			} catch (RemoteException e){
				System.out.println("Message: " + loginTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
    	
    	loginTO.setErrorMessage("Error in CtrlLogin.doLogin()");
    	loginTO.setValidated(false);
    	
    	return loginTO;
    }

    /**
     * Performs the user's logoff.
     *
     * @param user: the user
     * @return true, if successful logoff
     */
    public boolean doLogoff(User user){
    	if (user != null)
    	{
			try {
				remoteService = Util.getRemoteService();
				ctrlJms.close();
				return remoteService.executeLogoff(user);
				//remoteService.sendMessage("CtrlLogin Login: " + loginTO.getUserLogin(), this.ero);
				
			} catch (Exception e){
				System.out.println("Message: " + "\nException: " + e.toString());
			}
    	}
    	return false;
    }
    
    /* (non-Javadoc)
     * @see base.RemoteObserver#update(java.lang.Object, java.lang.Object)
     */
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlLogin: " + updateMsg);
    }


}
