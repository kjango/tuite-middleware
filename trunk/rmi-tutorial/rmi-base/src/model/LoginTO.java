package model;

import java.io.Serializable;

/**
 * Classe que implementa o Objeto de Transporte de Login
 * Serializable pois necessita trafegar pelo PROXY do RMI
 */
public class LoginTO implements Serializable {

	private static final long serialVersionUID = 3942967283733335029L;
	
	private String userLogin;
	private String userPassword;
	private String errorMessage;
	private boolean isValidated;
	private User user;
	//private Compute compute;

	public LoginTO (String login, String password){
		this.userLogin = login;
		this.userPassword = password;
	}
	
	public LoginTO (String login){
		this.userLogin = login;
	}
	
	//public Compute getCompute() {
		//return compute;
	//}

	//public void setCompute(Compute compute) {
		//this.compute = compute;
	//}

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

	public boolean isValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void showMessage(String test){
		
	}
	
}
