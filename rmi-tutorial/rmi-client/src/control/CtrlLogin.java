package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import model.User;
import base.Compute;
import base.RmiStarter;
import client.Client;

public class CtrlLogin extends RmiStarter {
	
    public CtrlLogin() {
    	super(LoginTO.class);
    	//this.CtrlLoginTO = new LoginTO(user, password);
    	/**
    	 * Falta terminar o construtor... doCustomRmiHandling está iniciando antes, pq esta classe extende RMIStarter 
    	 * e portanto user e password não estão sendo passados para o servidor
    	 */
    }

    private Compute compute;

    public User doLogin(LoginTO loginTO){
    	User user = null;
    	if ((loginTO != null) && (compute != null))
    	{
			try {
				loginTO = compute.executeLogin(loginTO);
	   	 		user = loginTO.getUser();
				System.out.println("User Name: " + user.getRealName() + "\nEmail: " + user.getEmail());
	 			System.out.println("Message: " + loginTO.getErrorMessage());	
			} catch (RemoteException e){
				System.out.println("Message: " + loginTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
    	return user;
    }
    
    @Override
    public void doCustomRmiHandling() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            compute = (Compute)registry.lookup(Compute.SERVICE_NAME);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }

}
