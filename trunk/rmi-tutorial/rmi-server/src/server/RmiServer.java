package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import model.FollowTO;
import model.LoginTO;
import model.RegisterTO;
import model.SearchTO;
import model.TuiteTO;
import base.PolicyFileLocator;
import base.RemoteObserver;
import base.RmiService;
import control.EditProfileImpl;
import control.LoginImpl;
import control.RegisterImpl;
import control.SearchImpl;
import control.TuitarImpl;

public class RmiServer extends Observable implements RmiService {
	
	 private class WrappedObserver implements Observer, Serializable {

	        private static final long serialVersionUID = 1L;

	        private RemoteObserver ro = null;

	        public WrappedObserver(RemoteObserver ro) {
	            this.ro = ro;
	        }

	        @Override
	        public void update(Observable o, Object arg) {
	            try {
	                ro.update(o.toString(), arg);
	            } catch (RemoteException e) {
	                System.out.println("Remote exception removing observer:" + this);
	                System.err.println(e.toString());
	                o.deleteObserver(this);
	            }
	        }
	    }

	    @Override
	 public void addObserver(RemoteObserver o) throws RemoteException {
	        WrappedObserver mo = new WrappedObserver(o);
	        addObserver(mo);
	        System.out.println("Added observer:" + mo);
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
            doCustomRmiHandling();
	    	//thread.start();
	    }
	    public static void main(String[] args) {
	    	new RmiServer();
	    }
	    public void doCustomRmiHandling() {
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

	    public void sendMessage(String texto) throws RemoteException{
	    	setChanged();
	    	notifyObservers(texto);
	    }
	    
	    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException {
	    	return new LoginImpl().doLogin(loginTO);
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
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FollowTO executeDoUnFollow(FollowTO followTO) throws RemoteException {
			return null;
		}
	    
}
