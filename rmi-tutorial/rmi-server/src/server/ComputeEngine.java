package server;

import java.rmi.RemoteException;

import model.LoginTO;
import base.Compute;
import base.Task;
import control.LoginImpl;


public class ComputeEngine
    implements Compute {

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        System.out.println("got compute task: " + t);
        return t.execute();
    }

    public LoginTO executeLogin(LoginTO loginTO) throws RemoteException {
    	return new LoginImpl().doLogin(loginTO);
    }
}
