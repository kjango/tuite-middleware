package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;

import model.BaseTO;
import model.FollowTO;
import model.LoginTO;
import model.NotifyTO;
import model.RegisterTO;
import model.SearchTO;
import model.TuiteTO;
import model.User;
import base.EnumRemoteObject;
import base.PolicyFileLocator;
import base.RmiService;
import base.Util;
import control.EditProfileImpl;
import control.FollowImpl;
import control.LoginImpl;
import control.RegisterImpl;
import control.SearchImpl;
import control.TuitarImpl;

public class RmiServer extends Observable implements RmiService {
	
	private ArrayList<User> listLogins;
	
	
	private JmsServerTopic jmsTopicLogin;
	private JmsServerTopic jmsTopicTuites;
	private JmsServerTopic jmsTopicFollow;
	private static boolean initialized = false;
	 
	    public RmiServer() {
            if (!initialized){
            	doCustomRmiHandling();	
            } else {
            	startJmsService();
            }
	    }
	    
	    public static void main(String[] args) {
	    		new RmiServer();	
	    }
	    
	    public void doCustomRmiHandling() {
	        	initialized = true;	
	    	
	    		System.setProperty("java.rmi.server.codebase", RmiServer.class
	                .getProtectionDomain().getCodeSource().getLocation().toString());

	            System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

	            if(System.getSecurityManager() == null) {
	                System.setSecurityManager(new SecurityManager());
	            }
	    	
	    	try {
	            Registry rmiRegistry = LocateRegistry.createRegistry(1099);
	            RmiService rmiService = (RmiService) UnicastRemoteObject.exportObject(new RmiServer(), 1099);
	            //Registry rmiRegistry = LocateRegistry.getRegistry();
	            rmiRegistry.bind("RmiService", rmiService);      
           
	            System.out.println("RMI Server is ready for use...");
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }

	    }

	    private void startJmsService(){
	    	if (jmsTopicLogin == null)
	    	{this.jmsTopicLogin = new JmsServerTopic(Util.FACTORYDEFAULT, Util.TOPICLOGIN, "SERVER", this);}

	    	if (jmsTopicFollow == null)
	    	{this.jmsTopicFollow = new JmsServerTopic(Util.FACTORYDEFAULT, Util.TOPICFOLLOW, "SERVER", this);}
	    	
	    	if (jmsTopicTuites == null)
	    	{this.jmsTopicTuites = new JmsServerTopic(Util.FACTORYDEFAULT, Util.TOPICTUITE, "SERVER", this);}
    	
	    }
  
	    public void sendMessage(BaseTO baseTO, EnumRemoteObject ero, String Default) throws RemoteException{
	    		switch (ero) {
				case LOGIN:
					for (int i = 0; i < listLogins.size(); i ++){
						String userToSend = listLogins.get(i).getLoginName();
						jmsTopicLogin.writeMessage(baseTO, ero, userToSend);
					}
					break;
				case FOLLOW:
					String userToBeFollowed = ((FollowTO)baseTO).getFollowed().getLoginName();
					
					if (((FollowTO)baseTO).getFollowed().isProtectedTuite()){
						for (int i=0; i<listLogins.size(); i++){
							String userOnLineToBeNotify = listLogins.get(i).getLoginName();
							
							if (userOnLineToBeNotify.equals(userToBeFollowed)){
								jmsTopicFollow.writeMessage(new BaseTO(), ero, userOnLineToBeNotify);
							}
						}
					}
					break;
				case TUITE:
					User userSource = (User)baseTO;
					
					for (int i = 0; i < userSource.getFollowers().size(); i++){
						String userToBeNofity = userSource.getFollowers().get(i).getLoginName();
						
						for (int j = 0; j < listLogins.size(); j++){
							String userOnLineToBeNotify = listLogins.get(j).getLoginName();
							
							if (userOnLineToBeNotify.equals(userToBeNofity)){
								jmsTopicTuites.writeMessage(new BaseTO(), ero, userOnLineToBeNotify);	
							}
						}
					}
					break;
				case NOTIFY:
					User userToBeNofity = (User)((NotifyTO)baseTO).getObjectBaseDestination();
					
					for (int i = 0; i < listLogins.size(); i++){
						String userOnLineToBeNotify = listLogins.get(i).getLoginName();
						
						if (userOnLineToBeNotify.equals(userToBeNofity.getLoginName())){
							jmsTopicTuites.writeMessage(new BaseTO(), ero, userOnLineToBeNotify);	
						}
					}
					break;
				default:
					break;
	    		}
	    }
	    
   	    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException {
	    	if (listLogins == null){
	    		listLogins = new ArrayList<User>();
	    	}

	    	if (isLoggedUser(loginTO.getUserLogin()) != null){
	    		loginTO.setValidated(false);
	    		loginTO.setErrorMessage("User Logged, you cannot login again!");
	    		return loginTO;
	    	} else {
	    		LoginTO newloginTo = new LoginImpl().doLogin(loginTO);
	    		listLogins.add(newloginTo.getUser());
	    		//notification by JMS
	    		try {
	    			sendMessage(newloginTo, EnumRemoteObject.LOGIN, newloginTo.getUserLogin());
	    		} catch (Exception ex){
	    			System.err.println(ex.toString());
	    		}
	    		System.out.println("New user login: " + newloginTo.getUserLogin());  
	    		return newloginTo;
	    	}
	    }
	    
	    public boolean executeLogoff(User user) throws RemoteException {
	    	User userLogged = isLoggedUser(user.getLoginName());
	    	
	    	if (userLogged != null){
	    		listLogins.remove(userLogged);
	    		System.out.println("user logoff: " + user.getLoginName());
	    		//clearObservers(userLogged);
	    		return true;
	    	}
	    	return false;
	    }
	    
   	    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException{
	    	return new RegisterImpl().doRegister(registerTO);
	    }

		@Override
		public TuiteTO executeTuite(TuiteTO t) throws RemoteException {
			TuiteTO tuiteTO = new TuitarImpl().writeTuite(t); 
			return tuiteTO;
		}

		@Override
		public RegisterTO executeEditProfile(RegisterTO registerTO)throws RemoteException {
			return new EditProfileImpl().Edit(registerTO);
		}

		@Override
		public SearchTO executeSearch(SearchTO t) throws RemoteException {
			return new SearchImpl().Search(t);
			
		}

		@Override
		public FollowTO executeDoFollow(FollowTO followTO) throws RemoteException {
			FollowTO newFollowTO = new FollowImpl().doFollow(followTO);
			return newFollowTO;
		}

		@Override
		public FollowTO executeDoUnFollow(FollowTO followTO) throws RemoteException {
			return new FollowImpl().doUnFollow(followTO);
		}
		
		@Override
		public FollowTO executeFollowNotify(FollowTO followTO) throws RemoteException {
			 return new FollowImpl().doNotifyFollow(followTO);
		}
	    
		@Override
		public User refreshUser(User user) throws RemoteException{
			return new LoginImpl().returnUser(user);
		}
				
	    private User isLoggedUser(String user){
	    	for (int i=0 ; i < listLogins.size(); i++){
    			User userLogged = listLogins.get(i);
    			if (userLogged != null) {
					if (userLogged.getLoginName().equals(user)) {
						return userLogged;
					}
				}
    		}
    		return null;
	    }
	    
	    
		
}
