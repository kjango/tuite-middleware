//Implementação do objeto remoto para fazer Login
package control;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;

import model.LoginTO;
import model.Tuite;
import model.User;
import dao.LoginDao;
import dao.UserDao;

public class LoginImpl {

	public LoginImpl(){
		
	}
	
	/**
	 * Faz a consulta do Login no BD
	 * Temporariamente está "mockado"
	 */
	public LoginTO doLogin(LoginTO loginTO)  {
		
		/**
		 * Verifica se o usuario pode logar no sistema
		 */
		if (LoginDao.canLogUser(loginTO))
		//if(true)
		{
			/**
			 * Em caso positivo, carrega o perfil dele
			 */
			User user = UserDao.returnUser(loginTO, true);
			
			/**
			 * TESTES
			 
			ImageIcon photo;
			ArrayList<Tuite> alTuites = new ArrayList<Tuite>();
			ArrayList<Tuite> alTuites1 = new ArrayList<Tuite>();
			ArrayList<User> alUser = new ArrayList<User>();
			
			photo = new ImageIcon("img1.jpg");
			user = new User(1, "user2@tests.com", "Seguidor De Testes", "lala", photo, 
					new Date(), false, alUser, alTuites, null, null);
			User user2 = new User(0, "user@tests.com", "Hombre De Testes", "lele", null, 
					new Date(), false, null, null, alTuites1, null);
			User user3 = new User(2, "user3@tests.com", "Terceiro De Testes", "lili", null, 
					new Date(), false, null, null, null, null);
			Tuite tuite1 = new Tuite(0, "Cacete, este é um tuiteste.Cacete, este é um tuiteste.Cacete, este é um tuiteste.Cacete, este é um tuiteste.Cacete, este é um tuiteste.12345", new Date(), user2);
			Tuite tuite2 = new Tuite(0, "OMG estou tuitando.", new Date(), user3);

			user.addFollowing(user2);
			user.addTuite(tuite2);

			for (int i = 0; i < 12; i++) {
				user2.addMyTuite(tuite1);
				user.addTuite(tuite1);
			}
			
			user.addTuite(tuite2);
			loginTO.setUser(user);
			
			loginTO.setValidated(true);
			loginTO.setErrorMessage("Success!! - User Logged");
			
		} else {
			loginTO.setValidated(false);
			loginTO.setErrorMessage("Error!! - User Not Allowed");
		}
			 */
		}
		
		return loginTO;
	}
	
		

}
