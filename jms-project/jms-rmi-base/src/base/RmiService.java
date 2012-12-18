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

// TODO: Auto-generated Javadoc
/**
 * The Remote Interface RmiService.
 */
public interface RmiService extends Remote {
	
	/** The Constant SERVICE_NAME. */
	public static final String SERVICE_NAME = "RmiService";
	
	
	/**
	 * Send message.
	 *
	 * @param baseTO the base to
	 * @param ero the ero
	 * @param Default the default
	 * @throws RemoteException the remote exception
	 */
	void sendMessage(BaseTO baseTO, EnumRemoteObject ero, String Default) throws RemoteException;
	
    /**
     * Execute the user's login.
     *
     * @param loginTO: the login Transfer Object
     * @return the loginTO from the server with modifications
     * @throws RemoteException the remote exception
     */
    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException;
    
    /**
     * Execute the user's logoff.
     *
     * @param user: the user
     * @return true, if successful
     * @throws RemoteException: the remote exception
     */
    public boolean executeLogoff(User user) throws RemoteException;
    
    /**
     * Execute the user's registry.
     *
     * @param registerTO: the register Transfer Object
     * @return the register Transer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException;
    
    /**
     * Method that edits the user's profile.
     *
     * @param registerTO: the register Transfer Object
     * @return the register Transer Object with modifications
     * @throws RemoteException the remote exception
     */
    public RegisterTO executeEditProfile(RegisterTO registerTO) throws RemoteException;
    
    /**
     * This method performs sending the tweet.
     *
     * @param t: the tweet Transfer Object
     * @return the tweet Transfer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public TuiteTO executeTuite(TuiteTO t) throws RemoteException;
    
    /**
     * Execute the search for users or tweets.
     *
     * @param t: the search Transfer Object
     * @return the search Transfer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public SearchTO executeSearch(SearchTO t) throws RemoteException;
    
    /**
     * Execute the action of a user follows another user
     *
     * @param followTO: the follow Transfer Object
     * @return the follow Transfer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public FollowTO executeDoFollow(FollowTO followTO) throws RemoteException;
    
    /**
     * Execute follow notify.
     *
     * @param followTO: the follow Transfer Object
     * @return the follow Transfer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public FollowTO executeFollowNotify(FollowTO followTO) throws RemoteException;
    
    /**
     * Execute the action of a user unfollows another user
     *
     * @param followTO: the follow Transfer Object
     * @return the follow Transfer Object with modifications
     * @throws RemoteException: the remote exception
     */
    public FollowTO executeDoUnFollow(FollowTO followTO) throws RemoteException;
    
    /**
     * Refresh user.
     *
     * @param user: the user
     * @return the user
     * @throws RemoteException: the remote exception
     */
    public User refreshUser(User user) throws RemoteException;
	
}
