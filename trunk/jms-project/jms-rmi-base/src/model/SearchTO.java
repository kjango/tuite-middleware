package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5003764762546509272L;

	//TODO add new attributes that will be returned by the server
	private String text;
	private int searchType; //This attribute accepts the following values:  1 (tuiteSearch) or 2 (userSearch)
	private ArrayList<User> resultUsers = new ArrayList<User>();
	private ArrayList<Tuite> resultTuites = new ArrayList<Tuite>();
	private String errorMessage;
	private User user;
	
	
	public SearchTO(User user, String text, int tipoBusca) {
		super();
		this.user = user;
		this.text = text;
		this.searchType = tipoBusca;
	}
	
	
	public boolean addResultTuite(Tuite tuite) {
		resultTuites.add(tuite);
		return true;
	}
	public boolean removeResultTuite(Tuite tuite) {
		resultTuites.remove(tuite);
		return true;
	}
	public boolean addResultUser(User user) {
		resultUsers.add(user);
		return true;
	}
	public boolean removeResultUser(User user) {
		resultUsers.remove(user);
		return true;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getTipoBusca() {
		return searchType;
	}
	public void setTipoBusca(int tipoBusca) {
		this.searchType = tipoBusca;
	}

	public ArrayList<User> getResultUsers() {
		return resultUsers;
	}

	public void setResultUsers(ArrayList<User> searchedUsers) {
		this.resultUsers = searchedUsers;
	}

	public ArrayList<Tuite> getResultTuites() {
		return resultTuites;
	}

	public void setResultTuites(ArrayList<Tuite> searchedTuites) {
		this.resultTuites = searchedTuites;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
