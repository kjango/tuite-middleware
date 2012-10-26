package server;

import java.rmi.Remote;

public interface InterfaceLogin extends Remote {
	public void doLogin(String name, char[] password);
}
