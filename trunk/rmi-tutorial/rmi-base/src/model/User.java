package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String realName;
	private String loginName;
	//private BufferedImage photo;
	private Date registerDate;
	private boolean protectedTuite;
	private ArrayList<User> following;  //deixo em arraylist mesmo?
	private ArrayList<Tuite> tuites; //Timeline
	private ArrayList<Tuite> myTuites;
	private ArrayList<User> followers;

	public User(long id, String email, String realName, String loginName, //BufferedImage photo,
			Date registerDate, boolean protectedTuite,
			ArrayList<User> following, ArrayList<Tuite> tuites,
			ArrayList<Tuite> myTuites, ArrayList<User> followers) {
		super();
		this.id = id;
		this.email = email;
		this.realName = realName;
		this.loginName = loginName;
		//this.photo = photo;
		this.registerDate = registerDate;
		this.protectedTuite = protectedTuite;
		this.following = following;
		this.tuites = tuites;
		this.myTuites = myTuites;
		this.followers = followers;
	}
	
	public User(){
	
	}	

	public boolean addFollowing(User user) {
		following.add(user);
		return true;
	}
	public boolean removeFoller(User user) {
		following.remove(user);
		return true;
	}
	public boolean addFollower(User user) {
		following.add(user);
		return true;
	}
	public boolean removeFollowing(User user) {
		following.remove(user);
		return true;
	}
	public boolean addTuite(Tuite tuite) {
		tuites.add(tuite);
		return true;
	}
	public boolean addMyTuite(Tuite tuite) {
		myTuites.add(tuite);
		return true;
	}
	public ArrayList<Tuite> getMyTuites() {
		return myTuites;
	}
	public void setMyTuites(ArrayList<Tuite> myTuites) {
		this.myTuites = myTuites;
	}
	public ArrayList<User> getFollowing() {
		return following;
	}
	public void setFollowing(ArrayList<User> following) {
		this.following = following;
	}
	public ArrayList<Tuite> getTuites() {
		return tuites;
	}
	public void setTuites(ArrayList<Tuite> tuites) {
		this.tuites = tuites;
	}
	public ArrayList<User> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	//public BufferedImage getPhoto() {
	//	return photo;
	//}
	//public void setPhoto(BufferedImage photo) {
	//	this.photo = photo;
	//}
	public boolean isProtectedTuite() {
		return protectedTuite;
	}
	public void setProtectedTuite(boolean protectedTuite) {
		this.protectedTuite = protectedTuite;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}