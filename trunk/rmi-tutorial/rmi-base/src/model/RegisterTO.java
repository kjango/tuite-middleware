package model;

import java.io.Serializable;

public class RegisterTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1174131038564631479L;
	
//	private long userId;
	private String userEmail;
	private String userLoginName;
	private String UserRealName;
	private String UserPassword;
//	private BufferedImage userPhoto;  //TODO ver essa poha das imagens, e criar os getters e setters dela aqui
	private boolean protectedTuite;
	private String errorMessage;
	private boolean isRegistered;
	private User user;   //For returning to client when user changes its status
	
	
	public RegisterTO(String userEmail, String userRealName, String userLoginName,
			String userPassword, boolean protectedTuite) {
		super();
		this.userEmail = userEmail;
		this.userLoginName = userLoginName;
		UserRealName = userRealName;
		UserPassword = userPassword;
		this.protectedTuite = protectedTuite;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserRealName() {
		return UserRealName;
	}
	public void setUserRealName(String userRealName) {
		UserRealName = userRealName;
	}
	public boolean isProtectedTuite() {
		return protectedTuite;
	}
	public void setProtectedTuite(boolean protectedTuite) {
		this.protectedTuite = protectedTuite;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isRegistered() {
		return isRegistered;
	}
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

}
