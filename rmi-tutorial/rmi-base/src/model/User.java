package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;


/**
 * The Class User.
 */
public class User extends BaseTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user's id. */
	private long id;
	
	/** The user's email. */
	private String email;
	
	/** The user's real name. */
	private String realName;
	
	/** The user's login name. */
	private String loginName;
	
	/** The user's register date. */
	private Date registerDate;
	
	/** The attribute that checks if the user's tweets are protected */
	private boolean protectedTuite;
	
	/** The following. */
	private ArrayList<User> following = new ArrayList<User>();  
	
	/** The user's timeline. */
	private ArrayList<Tuite> tuites = new ArrayList<Tuite>(); //Timeline
	
	/** The user's tweets. */
	private ArrayList<Tuite> myTuites = new ArrayList<Tuite>();
	
	/** The followers. */
	private ArrayList<User> followers = new ArrayList<User>();
	
	/** The user's photo. */
	private ImageIcon photo;

	/**
	 * Instantiates a new user.
	 *
	 * @param id: the user's id
	 * @param email: the user's email
	 * @param realName: the user's real name
	 * @param loginName: the user's login name
	 * @param photo: the user's photo
	 * @param registerDate: the user's register date
	 * @param protectedTuite: The attribute that checks if the user's tweets are protected
	 * @param following: the following
	 * @param tuites: the user's timeline.
	 * @param myTuites: the user's tweets
	 * @param followers the followers
	 */
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
	
	/**
	 * Instantiates a new user.
	 */
	public User(){
	
	}
	


	/**
	 * Instantiates a new user.
	 *
	 * @param id: the user's id
	 * @param loginName: the user's login name
	 * @param realName: the user's real name
	 * @param following: the following
	 * @param tuites: the user's timeline.
	 * @param followers the followers
	 * @param photo: the user's photo
	 */
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

	/**
	 * Instantiates a new user.
	 *
	 * @param id: the user's id
	 * @param loginName: the user's login name
	 * @param photo: the user's photo
	 */
	public User(long id, String loginName, ImageIcon photo) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.photo = photo;
	}

	/**
	 * Adds the following.
	 *
	 * @param user: the user
	 * @return true, if successful
	 */
	public boolean addFollowing(User user) {
		following.add(user);
		return true;
	}
	
	/**
	 * Removes the follower.
	 *
	 * @param user: the user
	 * @return true, if successful
	 */
	public boolean removeFollower(User user) {
		following.remove(user);
		return true;
	}
	
	/**
	 * Adds the follower.
	 *
	 * @param user: the user
	 * @return true, if successful
	 */
	public boolean addFollower(User user) {
		following.add(user);
		return true;
	}
	
	/**
	 * Removes the following.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public boolean removeFollowing(User user) {
		following.remove(user);
		return true;
	}
	
	/**
	 * Adds the tuite in the timeline.
	 *
	 * @param tuite: the tuite
	 * @return true, if successful
	 */
	public boolean addTuite(Tuite tuite) {
		tuites.add(tuite);
		return true;
	}
	
	/**
	 * Adds the user's tuite.
	 *
	 * @param tuite: the tuite
	 * @return true, if successful
	 */
	public boolean addMyTuite(Tuite tuite) {
		myTuites.add(tuite);
		return true;
	}
	
	/**
	 * Gets the user's tuites.
	 *
	 * @return the user's tuites
	 */
	public ArrayList<Tuite> getMyTuites() {
		return myTuites;
	}
	
	/**
	 * Sets the user's tweet array.
	 *
	 * @param myTuites: the new user's tweet array
	 */
	public void setMyTuites(ArrayList<Tuite> myTuites) {
		this.myTuites = myTuites;
	}
	
	/**
	 * Gets the following.
	 *
	 * @return the following
	 */
	public ArrayList<User> getFollowing() {
		return following;
	}
	
	/**
	 * Sets the following.
	 *
	 * @param following the new following
	 */
	public void setFollowing(ArrayList<User> following) {
		this.following = following;
	}
	
	/**
	 * Gets the timeline.
	 *
	 * @return the tweets
	 */
	public ArrayList<Tuite> getTuites() {
		return tuites;
	}
	
	/**
	 * Sets the timeline.
	 *
	 * @param tuites: the timeline
	 */
	public void setTuites(ArrayList<Tuite> tuites) {
		this.tuites = tuites;
	}
	
	/**
	 * Gets the followers.
	 *
	 * @return the followers
	 */
	public ArrayList<User> getFollowers() {
		return followers;
	}
	
	/**
	 * Sets the followers.
	 *
	 * @param followers the new followers
	 */
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}
	
	/**
	 * Gets the user's id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the user's id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * Gets the user's email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the user's real name.
	 *
	 * @return the real name
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * Sets the user's real name.
	 *
	 * @param realName: the new real name
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * Gets the user's login name.
	 *
	 * @return the login name
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * Sets the login name.
	 *
	 * @param loginName the new login name
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/**
	 * Gets the user's photo.
	 *
	 * @return the photo
	 */
	public ImageIcon getPhoto() {
		return photo;
	}
	
	/**
	 * Sets the user's photo.
	 *
	 * @param photo the new photo
	 */
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}
	
	/**
	 * Checks if the user's tweets are protected.
	 *
	 * @return true, if is protected tuite
	 */
	public boolean isProtectedTuite() {
		return protectedTuite;
	}
	
	/**
	 * Sets the protected tweet attribute.
	 *
	 * @param protectedTuite the new protected tuite
	 */
	public void setProtectedTuite(boolean protectedTuite) {
		this.protectedTuite = protectedTuite;
	}
	
	/**
	 * Gets the user's register date.
	 *
	 * @return the register date
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	
	/**
	 * Sets the user's register date.
	 *
	 * @param registerDate: the new register date
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}
