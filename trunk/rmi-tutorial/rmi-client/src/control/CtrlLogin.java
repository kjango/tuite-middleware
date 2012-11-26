package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.LoginTO;
import model.User;
import base.EnumRemoteObject;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

/**
 * Classe que possui os controles de login, extende RmiStarter para que sua localização seja explicitada para acesso do RMI
 */
public class CtrlLogin extends UnicastRemoteObject implements RemoteObserver {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static RmiService remoteService;
    private EnumRemoteObject ero = EnumRemoteObject.LOGIN;
  
    /**
     * Método que acessa as informações do usuario no Login
     * @throws RemoteException
     */
	public CtrlLogin() throws RemoteException {
        super();
        
		try {
			remoteService = Util.getRemoteService();
		} catch (RemoteException e){
			System.out.println("Message: " + "\nException: " + e.toString());
		}
        
    }
		
    public LoginTO doLogin(LoginTO loginTO) {
    	
    	if (loginTO != null)
    	{
			try {
				loginTO.setUserPassword(Util.GeraMD5(loginTO.getUserPassword()));
				remoteService = Util.getRemoteService();
				//remoteService.sendMessage("CtrlLogin Login: " + loginTO.getUserLogin(), this.ero);
				loginTO = remoteService.executeLogin(loginTO);
				
				if (loginTO.isValidated()){
					remoteService.addObserver(this, this.ero, loginTO);
					remoteService.sendMessage(loginTO, this.ero, "CtrlLogin: " + loginTO.getUserLogin());	
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

    public boolean doLogoff(User user){
    	if (user != null)
    	{
			try {
				remoteService = Util.getRemoteService();
				return remoteService.executeLogoff(user);
				//remoteService.sendMessage("CtrlLogin Login: " + loginTO.getUserLogin(), this.ero);
   	 		
			} catch (RemoteException e){
				System.out.println("Message: " + "\nException: " + e.toString());
			}
    	}
    	return false;
    }
    
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlLogin: " + updateMsg);
    }


}
