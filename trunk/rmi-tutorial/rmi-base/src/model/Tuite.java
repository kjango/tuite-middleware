package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The Class Tuite.
 */
public class Tuite extends BaseTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tweets's id */
	private Integer id;
	
	/** The tweets's text. */
	private String text;
	
	/** The date and time when the tweet was created */
	private Timestamp createdAt;
	
	/** The user whose tweet belongs to */
	private User myUser;
	
	/** The attribute that checks if the tweet has more than 140 characters */
	private boolean truncated;
	
	
	/**
	 * Instantiates a new tuite.
	 *
	 * @param id: the tweets's id
	 * @param text: the tweets's text
	 * @param createdAt: the date and time when the tweet was created
	 * @param myUser: the user whose tweet belongs to
	 */
	public Tuite(Integer id, String text, Timestamp createdAt, User myUser) {
		super();
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.myUser = myUser;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Checks if is truncated.
	 *
	 * @return true, if is truncated
	 */
	public boolean isTruncated() {
		return truncated;
	}

	/**
	 * Sets the truncated.
	 *
	 * @param truncated the new truncated
	 */
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the tweet's date.
	 *
	 * @return the created at
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the tweet's date.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * Gets the tweet's owner.
	 *
	 * @return the my user
	 */
	public User getMyUser() {
		return myUser;
	}
	
	/**
	 * Sets the tweet's owner.
	 *
	 * @param myUser the new my user
	 */
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}
