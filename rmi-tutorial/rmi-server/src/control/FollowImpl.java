package control;

import model.FollowTO;
import dao.UserDao;

public class FollowImpl {

	public FollowTO doFollow(FollowTO followTO) {

		followTO = UserDao.addFollower(followTO);
		
		if (followTO.getFollowed().isProtectedTuite()) {
			followTO.setNotifyFollower(true);
			followTO = doNotifyFollow(followTO);
		}
		
		return followTO;
	}
	
	public FollowTO doNotifyFollow(FollowTO followTO){
		return UserDao.updateNotifyFollow(followTO);
	}

	public FollowTO doUnFollow(FollowTO followTO) {
		return UserDao.removeFollower(followTO);
	}

}
