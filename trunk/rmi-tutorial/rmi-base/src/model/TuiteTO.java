package model;

import java.io.Serializable;

public class TuiteTO implements Serializable {

	private Tuite tuite;
	private String errorMessage;
	
	public TuiteTO(Tuite tuite) {
		super();
		this.tuite = tuite;
	}
			
	public Tuite getTuite() {
		return tuite;
	}
	public void setTuite(Tuite tuite) {
		this.tuite = tuite;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	} 
}
