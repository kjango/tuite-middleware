package model;


/**
 * The Class UserTO.
 */
public class UserTO {

	/** The user. */
	private User user;
	
	/** The error message. */
	private String errorMessage;
	
	/**
	 * Instantiates a new user transfer object.
	 *
	 * @param user: the user
	 */
	public UserTO(User user) {
		super();
		this.user = user;
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

}
