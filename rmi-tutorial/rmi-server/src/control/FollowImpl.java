package control;

import model.FollowTO;
import model.LoginTO;
import model.User;
import dao.UserDao;

public class FollowImpl {

	public FollowTO doFollow(FollowTO followTO) {
		
		LoginTO aux = new LoginTO(followTO.getFollowed().getLoginName());
		User usr = UserDao.returnUser(aux, false);
		followTO.getFollowed().setProtectedTuite(usr.isProtectedTuite());
		followTO.setNotifyFollower(usr.isProtectedTuite());
		followTO = UserDao.addFollower(followTO);
	
//		if (usr.isProtectedTuite()) {
//			followTO.setNotifyFollower(true);
			followTO = doNotifyFollow(followTO);
//		}
		
		return followTO;
	}
	
	public FollowTO doNotifyFollow(FollowTO followTO){
		return UserDao.updateNotifyFollow(followTO);
	}

	public FollowTO doUnFollow(FollowTO followTO) {
		return UserDao.removeFollower(followTO);
	}

}
