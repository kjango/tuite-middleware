package br.mb.tutorialActiveMQ.chat;

import javax.jms.Message;

public class ObjTest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String text;
	private int numero;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}

