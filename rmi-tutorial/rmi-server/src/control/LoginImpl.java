//Implementação do objeto remoto para fazer Login
package control;

import java.util.ArrayList;
import java.util.Date;

import model.LoginTO;
import model.Tuite;
import model.User;

public class LoginImpl {

	public LoginImpl(){
		
	}
	
	/**
	 * 
	 */
	public LoginTO doLogin(LoginTO loginTO)  {
		//TODO implementar o corpo desse código (aqui chega o login e a senha)

		User user = null;

		/**
		 * TESTES
		 */
		//try {
		//BufferedImage photo;
		ArrayList<Tuite> alTuites = new ArrayList<Tuite>();
		ArrayList<Tuite> alTuites1 = new ArrayList<Tuite>();
		ArrayList<User> alUser = new ArrayList<User>();
		
		//photo = ImageIO.read(new File("img1.jpg"));
		user = new User(1, "user2@tests.com", "Seguidor De Testes", //null, 
				new Date(), false, alUser, alTuites, null, null);
		User user2 = new User(0, "user@tests.com", "Hombre De Testes", //photo, 
				new Date(), false, null, null, alTuites1, null);
		User user3 = new User(2, "user3@tests.com", "Terceiro De Testes", //null, 
				new Date(), false, null, null, null, null);
		Tuite tuite1 = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user2);
		Tuite tuite2 = new Tuite(0, "OMG estou tuitando.", new Date(), user3);

		user.addFollowing(user2);
		user.addTuite(tuite2);

		for (int i = 0; i < 12; i++) {
			user2.addMyTuite(tuite1);
			user.addTuite(tuite1);
		}
		
		user.addTuite(tuite2);
		
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		loginTO.setValidated(true);
		loginTO.setErrorMessage("Success!!");
		loginTO.setUser(user);
		return loginTO;
	}
	
}
