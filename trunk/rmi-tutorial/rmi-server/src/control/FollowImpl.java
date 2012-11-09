package control;

import model.FollowTO;
import dao.UserDao;

public class FollowImpl {

	public FollowTO doFollow(FollowTO followTO) {
		FollowTO res = UserDao.addFollower(followTO);

		if (followTO.getFollowed().isProtectedTuite()) {
			// TODO dar um jeito de avisar o followed q tem gente querendo
			// followar ele
		}

		return res;
	}

	public FollowTO doUnFollow(FollowTO followTO) {
		FollowTO res = UserDao.removeFollower(followTO);

		return res;
	}

}
