package model;

import java.io.Serializable;

public class RegisterTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1174131038564631479L;
	
	private String userPassword;
//	private BufferedImage userPhoto;  //TODO ver essa poha das imagens, e criar os getters e setters dela aqui
	private String errorMessage;
	private boolean isRegistered;
	private boolean modifiedLogin;
	private User user;   //For returning to client when user changes its status
	
	public RegisterTO(User user, String password) {
		super();
		this.user = user;
		userPassword = password;
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
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isModifiedLogin() {
		return modifiedLogin;
	}

	public void setModifiedLogin(boolean modifiedLogin) {
		this.modifiedLogin = modifiedLogin;
	}

}