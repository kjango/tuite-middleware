package model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterTO.
 */
public class RegisterTO implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1174131038564631479L;
	
	/** The user password. */
	private String userPassword;
	
	/** The error message. */
	private String errorMessage;
	
	/** The attribute to check the user's registration success */
	private boolean isRegistered;
	
	/** The attribute to check if the user changed his login_name in the EditProfile procedure */
	private boolean modifiedLogin;
	
	/** The user. */
	private User user;  
	
	/**
	 * Instantiates a new register Transfer Object.
	 *
	 * @param user: the user
	 * @param password: the  user's password
	 */
	public RegisterTO(User user, String password) {
		super();
		this.user = user;
		userPassword = password;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Checks if is registered.
	 *
	 * @return true, if is registered
	 */
	public boolean isRegistered() {
		return isRegistered;
	}
	
	/**
	 * Sets the flag about the registration procedure.
	 *
	 * @param isRegistered the new register flag
	 */
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	
	/**
	 * Gets the user password.
	 *
	 * @return the user password
	 */
	public String getUserPassword() {
		return userPassword;
	}
	
	/**
	 * Sets the user password.
	 *
	 * @param userPassword: the new user password
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * Checks if the login was modified during the EditProfile procedure.
	 *
	 * @return true, if is modified login
	 */
	public boolean isModifiedLogin() {
		return modifiedLogin;
	}

	/**
	 * Sets the modified login.
	 *
	 * @param modifiedLogin: the new modified login
	 */
	public void setModifiedLogin(boolean modifiedLogin) {
		this.modifiedLogin = modifiedLogin;
	}

}
