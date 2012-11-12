package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.RegisterTO;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

public class CtrlRegister extends UnicastRemoteObject implements RemoteObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RmiService remoteService;

	public CtrlRegister() throws RemoteException {
        super();
    }
	
	public RegisterTO doRegistry(RegisterTO registerTO) {
		if (registerTO != null) {
			try {
				String t = Util.GeraMD5(registerTO.getUserPassword());
				registerTO.setUserPassword(t);
				remoteService = Util.getRemoteService();
				return remoteService.executeRegistry(registerTO);
				
//				res = compute.executeRegistry(registerTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + registerTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		return null;
	}

	public RegisterTO doEditProfile(RegisterTO registerTO) {
		if (registerTO != null) {
			try {
				String password_MD5 = registerTO.getUserPassword();
				if(password_MD5 != null)
					password_MD5 = Util.GeraMD5(password_MD5);
				registerTO.setUserPassword(password_MD5);
				
				remoteService = Util.getRemoteService();
				return remoteService.executeEditProfile(registerTO);
		
				//res = compute.executeEditProfile(registerTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + registerTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		return null;
	}
	
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlRegister: " + updateMsg);
    }

}
