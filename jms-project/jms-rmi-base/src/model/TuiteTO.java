package model;

import java.io.Serializable;


/**
 * The Class TuiteTO.
 */
public class TuiteTO extends BaseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The tuite. */
	private Tuite tuite;
	
	/** The error message. */
	private String errorMessage;
	
	/**
	 * Instantiates a new tuite Transfer Object.
	 *
	 * @param tuite: the tuite
	 */
	public TuiteTO(Tuite tuite) {
		super();
		this.tuite = tuite;
	}
			
	/**
	 * Gets the tuite.
	 *
	 * @return the tuite
	 */
	public Tuite getTuite() {
		return tuite;
	}
	
	/**
	 * Sets the tuite.
	 *
	 * @param tuite: the new tuite
	 */
	public void setTuite(Tuite tuite) {
		this.tuite = tuite;
	}
	
	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the error message.
	 *
	 * @param errorMessage: the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	} 
}
