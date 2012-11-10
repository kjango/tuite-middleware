package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import base.RemoteObserver;
import base.RmiService;

public class RmiClient extends UnicastRemoteObject implements RemoteObserver {
    protected RmiClient() throws RemoteException {
        super();
    }
    public RmiClient(String name) throws RemoteException{
    	this.teste = name;
    }
    
    private static final long serialVersionUID = 1L;
    private static RmiService remoteService;
    
    public String teste;

    public static void main(String[] args) {
        //if (System.getSecurityManager() == null)
          // System.setSecurityManager(new RMISecurityManager());
        try {
        	remoteService = (RmiService) Naming.lookup("//PROG/RmiService");
            RmiClient client = new RmiClient("Usuario Monga");
            remoteService.addObserver(client);
            
            remoteService.sendMessage("Usuario Mama");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	
    	System.out.println("got message:" + updateMsg);
        
    }
}
