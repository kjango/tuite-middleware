package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.LoginTO;
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

  
    /**
     * Método que acessa as informações do usuario no Login
     * @throws RemoteException
     */
	public CtrlLogin() throws RemoteException {
        super();
    }
		
    public LoginTO doLogin(LoginTO loginTO) {
    	
    	if (loginTO != null)
    	{
			try {
				loginTO.setUserPassword(Util.GeraMD5(loginTO.getUserPassword()));
				remoteService = Util.getRemoteService();
				return remoteService.executeLogin(loginTO);
	   	 		
			} catch (RemoteException e){
				System.out.println("Message: " + loginTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
    	
    	loginTO.setErrorMessage("Error in CtrlLogin.doLogin()");
    	loginTO.setValidated(false);
    	
    	return loginTO;
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlLogin: " + updateMsg);
    }


}
