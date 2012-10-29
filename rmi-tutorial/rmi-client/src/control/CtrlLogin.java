package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import model.LoginTO;
import model.User;
import base.Compute;
import base.RmiStarter;
import client.Client;

/**
 * Classe que possui os controles de login, extende RmiStarter para que sua localização seja explicitada para acesso do RMI
 */
public class CtrlLogin extends RmiStarter {

	/**
	 * Objeto de acesso ao RMI
	 */
    private Compute compute;
	
    public CtrlLogin() {
    	super(LoginTO.class);
    }

    /**
     * Método que acessa as informações do usuario no Login
     * @param loginTO
     * @return
     */
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
    
    /**
     * Implementação do método do RMIStarter para acesso aos métodos remotos
     */
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
