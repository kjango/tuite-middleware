//Implementação do objeto remoto para fazer Login
package control;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImpl extends UnicastRemoteObject implements InterfaceLogin, Serializable{

	public LoginImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5188638482852544904L;

	@Override
	public void doLogin(String name, char[] password) throws RemoteException {
		//TODO implementar o corpo desse código (aqui chega o login e a senha)
		System.out.println(name);
		System.out.println(password);
		
	}
	
}
