package model;

import java.util.Date;


public class Tuite {
	private long id;
	private String text;
	private Date time;
	private User myUser;
	private boolean truncated;
	
	public Tuite(long id, String text, Date time, User myUser) {
		super();
		this.id = id;
		this.text = text;
		this.time = time;
		this.myUser = myUser;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public User getMyUser() {
		return myUser;
	}
	public void setMyUser(User myUser) {
		this.myUser = myUser;
	}
}
