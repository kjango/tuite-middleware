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
	private int tipoBusca; //This attribute accepts the following values:  1 (tuiteSearch) or 2 (userSearch)
	private ArrayList<User> searchedUsers;
	private ArrayList<Tuite> searchedTuites;
	
	
	public SearchTO(String text, int tipoBusca) {
		super();
		this.text = text;
		this.tipoBusca = tipoBusca;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getTipoBusca() {
		return tipoBusca;
	}
	public void setTipoBusca(int tipoBusca) {
		this.tipoBusca = tipoBusca;
	}

	public ArrayList<User> getSearchedUsers() {
		return searchedUsers;
	}

	public void setSearchedUsers(ArrayList<User> searchedUsers) {
		this.searchedUsers = searchedUsers;
	}

	public ArrayList<Tuite> getSearchedTuites() {
		return searchedTuites;
	}

	public void setSearchedTuites(ArrayList<Tuite> searchedTuites) {
		this.searchedTuites = searchedTuites;
	}
	
	

}
