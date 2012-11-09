package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.FollowTO;
import model.LoginTO;
import model.RegisterTO;
import model.SearchTO;
import model.TuiteTO;

public interface Compute extends Remote {
    
    public static final String SERVICE_NAME = "ComputeEngine";
    
    <T> T executeTask(Task<T> t) throws RemoteException;
    
    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException;
    public void showMessage(String test) throws RemoteException;
    public void sendMessage(String test) throws RemoteException;
    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException;
    public RegisterTO executeEditProfile(RegisterTO registerTO) throws RemoteException;
    public TuiteTO executeTuite(TuiteTO t) throws RemoteException;
    public SearchTO executeSearch(SearchTO t) throws RemoteException;
    public FollowTO executeDoFollow(FollowTO followTO) throws RemoteException;
    public FollowTO executeDoUnFollow(FollowTO followTO) throws RemoteException;
    
}
