package server;

import java.rmi.RemoteException;

import base.Compute;
import base.Task;


public class ComputeEngine
    implements Compute {

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        System.out.println("got compute task: " + t);
        return t.execute();
    }
    
	public String decirHola() throws RemoteException{
		return "Hola a todos!";
	}
}
