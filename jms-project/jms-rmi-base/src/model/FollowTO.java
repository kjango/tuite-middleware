package model;

import java.io.Serializable;

public class FollowTO extends BaseTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7422668119673756987L;

	private String errorMessage;
	private User follower;
	private User followed;
	private boolean isFollowing;
	private boolean notifyFollower;
	
	
	public boolean isNotifyFollower() {
		return notifyFollower;
	}
	
	public void setNotifyFollower(boolean notifyFollower) {
		this.notifyFollower = notifyFollower;
	}
	
	public FollowTO(User follower, User followed) {
		super();
		this.follower = follower;
		this.followed = followed;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public User getFollower() {
		return follower;
	}
	public void setFollower(User follower) {
		this.follower = follower;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}
	public boolean isFollowing() {
		return isFollowing;
	}
	public void setSuccess(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}
}
