package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.User;

public interface Compute extends Remote {
    
    public static final String SERVICE_NAME = "ComputeEngine";
    
    <T> T executeTask(Task<T> t) throws RemoteException;
    
    public String decirHola() throws RemoteException;
    
    public boolean UserLogin(String user) throws RemoteException;

	public User doLogin(String name, char[] password) throws RemoteException;
}
