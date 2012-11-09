package server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import base.PolicyFileLocator;
import base.RemoteObserver;
import base.RmiService;
import base.RmiServiceStarter;

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
	    	//super (RmiServer.class);
	    	
	        System.setProperty("java.rmi.server.codebase", RmiServer.class
	                .getProtectionDomain().getCodeSource().getLocation().toString());

	            System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

	            if(System.getSecurityManager() == null) {
	                System.setSecurityManager(new SecurityManager());
	            }
	            doCustomRmiHandling();
	    	thread.start();
	    }

	    public static void main(String[] args) {
	        
	    	new RmiServer();
	    	
	    	/*
	    	if (System.getSecurityManager() == null)
	            System.setSecurityManager(new RMISecurityManager());
	        try {

	        	//Registry rmiRegistry = LocateRegistry.createRegistry(999);
	            //RmiService rmiService = (RmiService) UnicastRemoteObject.exportObject(new RmiServer(), 999);
	            //Registry rmiRegistry = LocateRegistry.getRegistry();
	            //rmiRegistry.bind("RmiService", rmiService);
           
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        */
	    }
	
	    //@Override
	    public void doCustomRmiHandling() {
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
}
