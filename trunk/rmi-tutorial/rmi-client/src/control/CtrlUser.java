package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.FollowTO;
import model.User;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

public class CtrlUser extends UnicastRemoteObject implements RemoteObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RmiService remoteService;

	public CtrlUser() throws RemoteException {
        super();
    }
	
	public User refreshUser(User user){
		try {
			remoteService = Util.getRemoteService();
			return remoteService.refreshUser(user);
		} catch (RemoteException e) {
			System.out.println("Message: "
					+ "\nException: " + e.toString());
		}
		return null;
	}
	
	public boolean doesFollow(User a, User b){
//		if (a.getFollowing() == null){
//			return false;
//		}
//		
//		if(a.getFollowing().contains(b)){
//			return true;
//		}
		for (User u : a.getFollowing()) {
			if(u.getId() == b.getId()){
				return true;
			}
		}
		
		return false;
	}
	
	public FollowTO doFollow(FollowTO followTO){
		if (followTO != null) {
			try {
				remoteService = Util.getRemoteService();
				return remoteService.executeDoFollow(followTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		return null;
	}
	
	public FollowTO doUnFollow(FollowTO followTO){
		if (followTO != null) {
			try {
				remoteService = Util.getRemoteService();
				return remoteService.executeDoUnFollow(followTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}

		return followTO;
	}
	
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlUser: " + updateMsg);
    }
}
