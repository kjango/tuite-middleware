package control;

import model.FollowTO;
import dao.UserDao;

public class FollowImpl {

	public FollowTO doFollow(FollowTO followTO) {
		return UserDao.addFollower(followTO);

//		if (followTO.getFollowed().isProtectedTuite()) {
//			// TODO dar um jeito de avisar o followed q tem gente querendo
//			// followar ele
//		}

	}

	public FollowTO doUnFollow(FollowTO followTO) {
		return UserDao.removeFollower(followTO);
	}

}