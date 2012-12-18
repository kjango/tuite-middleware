package client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import base.RemoteNotify;
import base.RmiService;
import base.Util;

public class RmiClient extends UnicastRemoteObject implements RemoteNotify {
    
	protected RmiClient() throws RemoteException {
        super();
    }
    
    private static final long serialVersionUID = 1L;
    public static RmiService remoteService;
   
    public static void main(String[] args) {
        try {
        	remoteService = Util.getRemoteService();
            //RmiClient client = new RmiClient();  // utilizar a Classe atual
            //remoteService.addObserver(client);   // add observer para ser notificado quando ouver alterações
            
            //remoteService.sendMessage("teste de texto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("RmiClient: " + updateMsg);
    }
}
