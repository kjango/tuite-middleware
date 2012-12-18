package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.FollowTO;
import model.NotifyTO;
import model.User;
import view.MainScreen;
import base.EnumRemoteObject;
import base.RemoteNotify;
import base.RmiService;
import base.Util;

public class CtrlUser extends UnicastRemoteObject implements RemoteNotify {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RmiService remoteService;
	private EnumRemoteObject ero = EnumRemoteObject.FOLLOW;
	private view.MainScreen main;
	/** Pub/Sub for JMS */
	private JmsClientTopic ctrlJms;

	public CtrlUser(MainScreen main) throws RemoteException {
        super();
        this.main = main;
        
		try {
			remoteService = Util.getRemoteService();
			//remoteService.addObserver(this, this.ero, this.main.getUser());
			ctrlJms = new JmsClientTopic(main.getUser(), this);
		} catch (RemoteException e){
			System.out.println("Message: " + e.toString());
		}
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
				
				FollowTO returnFollowTO = remoteService.executeDoFollow(followTO);
				
				if (followTO.getFollowed().isProtectedTuite()){
					//remoteService.sendMessage(followTO, this.ero, "DoFollow protected: ");
					remoteService.sendMessage(followTO, EnumRemoteObject.FOLLOW, followTO.getFollowed().getLoginName());
				}
				
				return returnFollowTO;
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		return null;
	}
	
	public void updateNotifyFromFollow(FollowTO followTO){
		if (followTO != null) {
			try {
				remoteService = Util.getRemoteService();
				remoteService.executeFollowNotify(followTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
	}

	public void doNotifyForUser(NotifyTO notifyTO){
		if (notifyTO != null) {
			try {
				remoteService = Util.getRemoteService();
				remoteService.sendMessage(notifyTO, EnumRemoteObject.NOTIFY, "Update user destiny");
			} catch (RemoteException e) {
				System.out.println("Message: " + "\nException: " + e.toString());
			}
		}
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
    	this.main.updateUser();
    }
}
