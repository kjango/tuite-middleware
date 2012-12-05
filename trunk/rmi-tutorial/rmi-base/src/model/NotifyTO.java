package model;

import java.io.Serializable;

import base.EnumRemoteObject;

public class NotifyTO extends BaseTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String textMessage;
	private boolean optionYesNo;
	private BaseTO objectBaseSource;
	private BaseTO objectBaseDestination;
	private EnumRemoteObject ero;
	
	
	public BaseTO getObjectBaseSource() {
		return objectBaseSource;
	}
	public void setObjectBaseSource(BaseTO objectBaseSource) {
		this.objectBaseSource = objectBaseSource;
	}
	public BaseTO getObjectBaseDestination() {
		return objectBaseDestination;
	}
	public void setObjectBaseDestination(BaseTO objectBaseDestination) {
		this.objectBaseDestination = objectBaseDestination;
	}
	public EnumRemoteObject getEro() {
		return ero;
	}
	public void setEro(EnumRemoteObject ero) {
		this.ero = ero;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	public boolean isOptionYesNo() {
		return optionYesNo;
	}
	public void setOptionYesNo(boolean optionYesNo) {
		this.optionYesNo = optionYesNo;
	}

	
	
	
}
