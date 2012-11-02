package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.LoginTO;
import model.RegisterTO;
import model.Tuite;
import base.Compute;
import base.Task;
import control.LoginImpl;
import control.RegisterImpl;
import control.TuitarImpl;


public class ComputeEngine
    implements Compute {

	private ArrayList<Compute> ConectedUsers;
	
    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        System.out.println("got compute task: " + t);
        return t.execute();
    }

    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException {
    	if (ConectedUsers == null) {
    		ConectedUsers = new ArrayList<Compute>();
    		ConectedUsers.add(loginTO.getCompute());
    	} else {
    		ConectedUsers.add(loginTO.getCompute());
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
    public RegisterTO executeRegistry(RegisterTO registerTO) throws RemoteException{
    	
    	return new RegisterImpl().doRegister(registerTO);
    }

	@Override
	public Tuite executeTuite(Tuite t) throws RemoteException {
		return new TuitarImpl().Tuitar(t);
	}
}
