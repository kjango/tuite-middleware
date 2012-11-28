package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.BaseTO;
import model.FollowTO;
import model.LoginTO;
import model.RegisterTO;
import model.SearchTO;
import model.TuiteTO;
import model.User;

public interface RmiService extends Remote {
	
	public static final String SERVICE_NAME = "RmiService";
	
	void addObserver(RemoteObserver o, EnumRemoteObject ero, BaseTO baseTO) throws RemoteException;
	void sendMessage(BaseTO baseTO, EnumRemoteObject ero, String Default) throws RemoteException;
	
    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException;
    public boolean executeLogoff(User user) throws RemoteException;
    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException;
    public RegisterTO executeEditProfile(RegisterTO registerTO) throws RemoteException;
    public TuiteTO executeTuite(TuiteTO t) throws RemoteException;
    public SearchTO executeSearch(SearchTO t) throws RemoteException;
    public FollowTO executeDoFollow(FollowTO followTO) throws RemoteException;
    public FollowTO executeFollowNotify(FollowTO followTO) throws RemoteException;
    public FollowTO executeDoUnFollow(FollowTO followTO) throws RemoteException;
    public User refreshUser(User user) throws RemoteException;
	
}
