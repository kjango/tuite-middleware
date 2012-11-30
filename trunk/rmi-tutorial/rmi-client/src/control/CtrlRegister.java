package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.RegisterTO;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

/**
 * The Class CtrlRegister.
 */
public class CtrlRegister extends UnicastRemoteObject implements RemoteObserver{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The remote interface. */
	public static RmiService remoteService;

	/**
	 * Instantiates a new ctrl register.
	 *
	 * @throws RemoteException the remote exception
	 */
	public CtrlRegister() throws RemoteException {
        super();
    }
	
	/**
	 * Performs the user's registry.
	 *
	 * @param registerTO the register transfer object
	 * @return the register TO with modifications
	 */
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

	/**
	 * Performs the user's profile edition.
	 *
	 * @param registerTO: the register transfer object
	 * @return the register TO with modifications
	 */
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
	
    /* (non-Javadoc)
     * @see base.RemoteObserver#update(java.lang.Object, java.lang.Object)
     */
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlRegister: " + updateMsg);
    }

}
