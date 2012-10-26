package control;

import model.User;

public class CtrlClient {
	
	public boolean follows(User a, User b){
		if (a.getFollowing() == null){
			return false;
		}
		
		if(a.getFollowing().contains(b)){
			return true;
		}
		return false;
	}
}
