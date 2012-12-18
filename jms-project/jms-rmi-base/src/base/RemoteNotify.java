package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteNotify extends Remote {

	void update(Object observable, Object updateMsg) throws RemoteException;
	
}
