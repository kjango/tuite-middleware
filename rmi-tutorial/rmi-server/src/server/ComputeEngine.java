package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.FollowTO;
import model.LoginTO;
import model.RegisterTO;
import model.SearchTO;
import model.Tuite;
import model.TuiteTO;
import base.Compute;
import base.Task;
import control.EditProfileImpl;
import control.FollowImpl;
import control.LoginImpl;
import control.RegisterImpl;
import control.SearchImpl;
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
	public TuiteTO executeTuite(TuiteTO t) throws RemoteException {
		return new TuitarImpl().Tuitar(t);
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
}
