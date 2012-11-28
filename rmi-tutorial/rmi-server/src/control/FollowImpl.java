package control;

import model.FollowTO;
import dao.UserDao;

public class FollowImpl {

	public FollowTO doFollow(FollowTO followTO) {

//		if (followTO.getFollowed().isProtectedTuite()) {
//			// TODO dar um jeito de avisar o followed q tem gente querendo
//			// followar ele
//		}
		
		return UserDao.addFollower(followTO);
	}
	
	public FollowTO doNotifyFollow(FollowTO followTO){
		return UserDao.updateNotifyFollow(followTO);
	}

	public FollowTO doUnFollow(FollowTO followTO) {
		return UserDao.removeFollower(followTO);
	}

}
