package model;

public class UserTO {

	private User user;
	private String errorMessage;
	
	public UserTO(User user) {
		super();
		this.user = user;
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

}
