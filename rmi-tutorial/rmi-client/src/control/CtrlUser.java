package control;

import java.rmi.RemoteException;

import model.FollowTO;
import model.User;
import base.Compute;

public class CtrlUser {
	
	public boolean doesFollow(User a, User b){
		if (a.getFollowing() == null){
			return false;
		}
		
		if(a.getFollowing().contains(b)){
			return true;
		}
		return false;
	}
	
	public FollowTO doFollow(FollowTO followTO, Compute compute){
		FollowTO res = null;

		if ((followTO != null) && (compute != null)) {
			try {
				res = compute.executeDoFollow(followTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}

		return res;
	}
	
	public FollowTO doUnFollow(FollowTO followTO, Compute compute){
		FollowTO res = null;

		if ((followTO != null) && (compute != null)) {
			try {
				res = compute.executeDoUnFollow(followTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + followTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}

		return res;
	}
}
