package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import model.Tuite;
import model.User;

public class CtrlLogin {

	public User login(String name, char[] password){
		//Codigo para enviar login e senha por rmi para o servidor
		
		/*String url = new String("rmi://localhost/Login");
		try{
		InterfaceLogin interfaceL = (InterfaceLogin)  Naming.lookup(url);
		interfaceL.doLogin(name, password);
		} catch (RemoteException e){
			e.printStackTrace();
		} catch (NotBoundException e){
			e.printStackTrace();
		} catch (MalformedURLException e){
			e.printStackTrace();
		}*/
		
				
		
		
		User user = null;

		if (true){
			//			user = new User(0, null, null, null, null, false, null, null, null, null);

			/*
			 * Só para testes
			*/ 
			BufferedImage photo;
			try {
				ArrayList<Tuite> alTuites = new ArrayList<Tuite>();
				ArrayList<Tuite> alTuites1 = new ArrayList<Tuite>();
				ArrayList<User> alUser = new ArrayList<User>();
				photo = ImageIO.read(new File("img1.jpg"));
				user = new User(1, "user2@tests.com", "Seguidor De Testes", null, new Date(), false, alUser, alTuites, null, null);
				User user2 = new User(0, "user@tests.com", "Hombre De Testes", photo, new Date(), false, null, null, alTuites1, null);
				User user3 = new User(2, "user3@tests.com", "Terceiro De Testes", null, new Date(), false, null, null, null, null);
				Tuite tuite1 = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user2);
				Tuite tuite2 = new Tuite(0, "OMG estou tuitando.", new Date(), user3);

				user.addFollowing(user2);
				user.addTuite(tuite2);

				for (int i = 0; i < 12; i++) {
					user2.addMyTuite(tuite1);
					user.addTuite(tuite1);
				}
				
				user.addTuite(tuite2);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 

			/*
			 * Fim Testes
			 */
		}
		return user;
		
	}
}
