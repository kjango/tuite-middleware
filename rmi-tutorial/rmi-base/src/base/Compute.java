package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.LoginTO;

public interface Compute extends Remote {
    
    public static final String SERVICE_NAME = "ComputeEngine";
    
    <T> T executeTask(Task<T> t) throws RemoteException;
    
    LoginTO executeLogin(LoginTO loginTO) throws RemoteException;
    
}
