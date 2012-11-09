package client;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import base.PolicyFileLocator;
import base.RemoteObserver;
import base.RmiService;

public class RmiClient extends UnicastRemoteObject implements RemoteObserver {
    protected RmiClient() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        //if (System.getSecurityManager() == null)
        //    System.setSecurityManager(new RMISecurityManager());
        try {

            System.setProperty("java.rmi.server.codebase", RmiClient.class
                    .getProtectionDomain().getCodeSource().getLocation().toString());

                System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

                if(System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }

        	
        	
        	RmiService remoteService = (RmiService) Naming
                    .lookup("//localhost:1099/RmiService");
            RmiClient client = new RmiClient();
            remoteService.addObserver(client);
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
