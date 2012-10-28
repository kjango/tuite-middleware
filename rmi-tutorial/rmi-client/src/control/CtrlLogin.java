package control;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import model.User;
import base.Compute;
import base.RmiStarter;
import client.Client;

public class CtrlLogin extends RmiStarter {
	
    public CtrlLogin(String user, String password) {
    	super(LoginTO.class);
    	//this.CtrlLoginTO = new LoginTO(user, password);
    	/**
    	 * Falta terminar o construtor... doCustomRmiHandling est� iniciando antes, pq esta classe extende RMIStarter 
    	 * e portanto user e password n�o est�o sendo passados para o servidor
    	 */

    }

    public LoginTO CtrlLoginTO;
    
    @Override
    public void doCustomRmiHandling() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            Compute compute = (Compute)registry.lookup(Compute.SERVICE_NAME);

            //if (CtrlLoginTO != null)
            //{
            	CtrlLoginTO = compute.executeLogin(new LoginTO("teste", "teste"));
            	
            	 User user = CtrlLoginTO.getUser();
            	 System.out.println("User Name: " + user.getRealName() + "\nEmail: " + user.getEmail());
            	 System.out.println("Message: " + CtrlLoginTO.getErrorMessage());
            //} 
        


        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client();
    }

}
