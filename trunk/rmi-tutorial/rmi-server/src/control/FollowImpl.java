package control;

import model.FollowTO;
import dao.UserDao;

public class FollowImpl {
	
	public FollowTO doFollow(FollowTO followTO){
		FollowTO res = null;
		
		if (followTO.getFollowed().isProtectedTuite()){
			//TODO dar um jeito de avisar o followed q tem gente querendo followar ele
		}else{
			//TODO dar um jeito de avisar o followed q tem gente followando ele
//			res = UserDao.
		}
		
		return res;
	}
	
	public FollowTO doUnFollow(FollowTO followTO){
		FollowTO res = null;
		
		
		return res;
	}

}
