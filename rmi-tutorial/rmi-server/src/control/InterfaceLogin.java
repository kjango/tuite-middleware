package control;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceLogin extends Remote {
	public void doLogin(String name, char[] password) throws RemoteException;
}
