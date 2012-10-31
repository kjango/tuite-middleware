package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.LoginTO;

public interface Compute extends Remote {
    
    public static final String SERVICE_NAME = "ComputeEngine";
    
    <T> T executeTask(Task<T> t) throws RemoteException;
    
    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException;
    public void showMessage(String test) throws RemoteException;
    public void sendMessage(String test) throws RemoteException;
    
}
