package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String email;
	private String realName;
	private String loginName;
	private Date registerDate;
	private boolean protectedTuite;
	private ArrayList<User> following = new ArrayList<User>();  
	private ArrayList<Tuite> tuites = new ArrayList<Tuite>(); //Timeline
	private ArrayList<Tuite> myTuites = new ArrayList<Tuite>();
	private ArrayList<User> followers = new ArrayList<User>();
	private ImageIcon photo;

	public User(long id, String email, String realName, String loginName, ImageIcon photo,
			Date registerDate, boolean protectedTuite,
			ArrayList<User> following, ArrayList<Tuite> tuites,
			ArrayList<Tuite> myTuites, ArrayList<User> followers) {
		super();
		this.id = id;
		this.email = email;
		this.realName = realName;
		this.loginName = loginName;
		this.photo = photo;
		this.registerDate = registerDate;
		this.protectedTuite = protectedTuite;
		this.following = following;
		this.tuites = tuites;
		this.myTuites = myTuites;
		this.followers = followers;
	}
	
	//Construtor para edição de usuário, utiliza somente os dados editáveis
//	public User(String realName, String loginName, String email){
//		this.realName = realName;
//		this.loginName = loginName;
//		this.email = email;
//	}
	
	public User(){
	
	}
	

	public User(long id, String loginName, String realName, ArrayList<User> following,
			ArrayList<Tuite> tuites, ArrayList<User> followers,
			ImageIcon photo) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.following = following;
		this.tuites = tuites;
		this.followers = followers;
		this.photo = photo;
	}

	public User(long id, String loginName, ImageIcon photo) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.photo = photo;
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
	public ImageIcon getPhoto() {
		return photo;
	}
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}
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
