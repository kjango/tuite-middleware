package control;

import java.rmi.RemoteException;

import model.LoginTO;
import model.User;
import base.Compute;
import base.Util;

/**
 * Classe que possui os controles de login, extende RmiStarter para que sua localização seja explicitada para acesso do RMI
 */
public class CtrlLogin {


    /**
     * Método que acessa as informações do usuario no Login
     * @param loginTO
     * @param compute
     * @return
     */
    public User doLogin(LoginTO loginTO, Compute compute){
    	User user = null;
    	if ((loginTO != null) && (compute != null))
    	{
			try {
				loginTO.setUserPassword(Util.GeraMD5(loginTO.getUserPassword()));
				loginTO.setCompute(compute);
				loginTO = compute.executeLogin(loginTO);
	   	 		user = loginTO.getUser();
				//System.out.println("User Name: " + user.getRealName() + "\nEmail: " + user.getEmail());
	 			//System.out.println("Message: " + loginTO.getErrorMessage());
	   	 		compute.sendMessage("macaca");
			} catch (RemoteException e){
				System.out.println("Message: " + loginTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
    	return user;
    }



}
