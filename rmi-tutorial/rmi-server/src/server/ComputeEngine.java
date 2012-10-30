package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import model.LoginTO;
import base.Compute;
import base.Task;
import control.LoginImpl;


public class ComputeEngine
    implements Compute {

	private ArrayList<Compute> ConectedUsers;
	
    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        System.out.println("got compute task: " + t);
        return t.execute();
    }

    public LoginTO executeLogin(LoginTO loginTO, Compute cpt) throws RemoteException {
    	if (ConectedUsers == null) {
    		ConectedUsers = new ArrayList<Compute>();
    		ConectedUsers.add(cpt);
    	} else {
    		ConectedUsers.add(cpt);
    	}
    	
    	return new LoginImpl().doLogin(loginTO);
    }
    
    public void showMessage(String test) throws RemoteException {
    	System.out.println("Message: " + test);
    }
    
    public void sendMessage(String test) throws RemoteException {
    	for (int i=0; i < ConectedUsers.size(); i++){
    		Compute cpt = ConectedUsers.get(i);
    		cpt.showMessage(test + "ID: " + cpt.toString());
    	}
    }
}
