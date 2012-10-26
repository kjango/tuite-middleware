package control;

import java.rmi.Remote;

import model.User;

public interface InterfaceLogin extends Remote {
	public User doLogin(String name, char[] password);
}
