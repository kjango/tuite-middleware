package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

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
import base.RemoteObserver;
import base.RmiService;
import control.EditProfileImpl;
import control.FollowImpl;
import control.LoginImpl;
import control.RegisterImpl;
import control.SearchImpl;
import control.TuitarImpl;

public class RmiServer extends Observable implements RmiService {
	
	private ArrayList<User> listLogins;
	
	private ArrayList<WrappedObserver> ObservableLogins;
	private ArrayList<WrappedObserver> ObservableTuites;
	private ArrayList<WrappedObserver> ObservableFollow;
	
	 private class WrappedObserver implements Observer, Serializable {

	        private static final long serialVersionUID = 1L;
	        private RemoteObserver ro = null;
	        private EnumRemoteObject ero;
	        private BaseTO baseTO;

	        public WrappedObserver(RemoteObserver ro, EnumRemoteObject ero, BaseTO baseTO) {
	            this.ro = ro;
	            this.ero = ero;
	            this.baseTO = baseTO;
	        }

	        public BaseTO getBaseTO(){
	        	return baseTO;
	        }
	        
	        @Override
	        public void update(Observable o, Object arg) {
	            try {
	                ro.update(o, arg);
	            } catch (RemoteException e) {
	                System.out.println("Remote exception removing observer:" + this);
	                System.err.println(e.toString());
	                o.deleteObserver(this);
	            }
	        }
	    }

	 private static boolean initialized = false;
	 
	    @Override
	 public void addObserver(RemoteObserver o, EnumRemoteObject ero, BaseTO baseTO) throws RemoteException {
	    	WrappedObserver mo = new WrappedObserver(o, ero, baseTO);
	        
	    	switch (ero) {
			case LOGIN:
				if (ObservableLogins == null){
					ObservableLogins = new ArrayList<RmiServer.WrappedObserver>();
				}
				ObservableLogins.add(mo);
				break;
			case FOLLOW:
				if (ObservableFollow == null){
					ObservableFollow = new ArrayList<RmiServer.WrappedObserver>();
				}
				ObservableFollow.add(mo);
				break;
			case TUITE:
				if (ObservableTuites == null){
					ObservableTuites = new ArrayList<RmiServer.WrappedObserver>();
				}
				ObservableTuites.add(mo);
				break;
			default:
				break;
    		}
	    }

  	    Thread thread = new Thread() {
	        @Override
	        public void run() {
	            while (true) {
	                try {
	                    Thread.sleep(5 * 1000);
	                } catch (InterruptedException e) {
	                    // ignore
	                }
	                setChanged();
	                notifyObservers(new Date());
	            }
	        };
	    };
	    
	    public RmiServer() {
            if (!initialized){
            	doCustomRmiHandling();	
            }
	    	//thread.start();
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

	    public void sendMessage(BaseTO baseTO, EnumRemoteObject ero, String Default) throws RemoteException{
	    		switch (ero) {
				case LOGIN:
						for (int i = 0; i < ObservableLogins.size(); i++){
							addObserver(ObservableLogins.get(i));
						}
				    	setChanged();
				    	notifyObservers(Default);
				    	deleteObservers();
					break;
				case FOLLOW:
					User userToBeFollowed = ((FollowTO)baseTO).getFollowed();
					
					for (int i=0; i < ObservableFollow.size(); i++){
						WrappedObserver wp = ObservableFollow.get(i);
						User userLocal = (User)wp.getBaseTO();
						if (userLocal.getLoginName().equals(userToBeFollowed.getLoginName())){
							addObserver(wp);
						}
					}
			    	setChanged();
			    	notifyObservers((FollowTO)baseTO);
			    	deleteObservers();
					break;
				case TUITE:
					User userSource = (User)baseTO;
					
					for (int i = 0; i < userSource.getFollowers().size(); i++){
						User userFollower = userSource.getFollowers().get(i);
						
						for (int j = 0; j < ObservableTuites.size(); j++){
							WrappedObserver wp = ObservableTuites.get(j);
							User userDestiny = (User)wp.getBaseTO();
							if (userDestiny.getLoginName().equals(userFollower.getLoginName())){
								addObserver(wp);	
							}
						}
					}
			    	setChanged();
			    	notifyObservers(Default);
			    	deleteObservers();
					break;
				case NOTIFY:
					User userTONotify = (User)((NotifyTO)baseTO).getObjectBaseDestination();
						for (int j = 0; j < ObservableFollow.size(); j++){
							WrappedObserver wp = ObservableFollow.get(j);
							User userDestiny = (User)wp.getBaseTO();
							if (userDestiny.getLoginName().equals(userTONotify.getLoginName())){
								addObserver(wp);	
							}
						}
			    	setChanged();
			    	notifyObservers(Default);
			    	deleteObservers();
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
	    		System.out.println("New user login: " + newloginTo.getUserLogin());  
	    		return newloginTo;
	    	}
	    }
	    
	    public boolean executeLogoff(User user) throws RemoteException {
	    	User userLogged = isLoggedUser(user.getLoginName());
	    	
	    	if (userLogged != null){
	    		listLogins.remove(userLogged);
	    		System.out.println("user logoff: " + user.getLoginName());
	    		clearObservers(userLogged);
	    		return true;
	    	}
	    	return false;
	    }
	    
   	    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException{
	    	return new RegisterImpl().doRegister(registerTO);
	    }

		@Override
		public TuiteTO executeTuite(TuiteTO t) throws RemoteException {
			return new TuitarImpl().writeTuite(t);
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
			return new FollowImpl().doFollow(followTO);
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
	    
	    private boolean clearObservers(User user){
	    	
	    	for (int i=0; i < ObservableLogins.size(); i++){
	    		User userObserver = ((LoginTO)ObservableLogins.get(i).getBaseTO()).getUser();
	    		if (user.getLoginName().equals(userObserver.getLoginName()))	    				{
	    			ObservableLogins.remove(i);
	    		}
	    	}
	    	for (int i=0; i < ObservableTuites.size(); i++){
	    		User userObserver = (User)ObservableTuites.get(i).getBaseTO();
	    		if (user.getLoginName().equals(userObserver.getLoginName()))	    				{
	    			ObservableTuites.remove(i);
	    		}
	    	}
	    	for (int i=0; i < ObservableFollow.size(); i++){
	    		User userObserver =((User)ObservableFollow.get(i).getBaseTO());
	    		if (user.getLoginName().equals(userObserver.getLoginName()))	    				{
	    			ObservableFollow.remove(i);
	    		}
	    	}
	    	return false;
	    }
		
}
