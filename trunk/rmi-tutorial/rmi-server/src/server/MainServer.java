package server;

import java.rmi.Naming;

import control.LoginImpl;


public class MainServer {
	public static void main (String[] args) throws Exception{
		LoginImpl loginImpl = new LoginImpl();
		Naming.rebind("Login", loginImpl);
		System.out.println("Servidor ativo, objeto de nome \"Login\" pronto para ser usado");
	}
}
