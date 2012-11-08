package model;

import java.io.Serializable;
import java.util.Date;


public class Tuite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	private Date createdAt;
	private User myUser;
	private boolean truncated;
	
	public Tuite(Integer id, String text, Date createdAt, User myUser) {
		super();
		this.id = id;
		this.text = text;
		this.createdAt = createdAt;
		this.myUser = myUser;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isTruncated() {
		return truncated;
	}

	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public User getMyUser() {
		return myUser;
	}
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}
