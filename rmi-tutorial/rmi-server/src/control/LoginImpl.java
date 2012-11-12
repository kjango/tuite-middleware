//Implementação do objeto remoto para fazer Login
package control;

import model.LoginTO;
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
			loginTO.setUser(user);
			loginTO.setValidated(true);
		} else {
			loginTO.setValidated(false);
			loginTO.setErrorMessage("Error!! - User Not Allowed");
		}
		
		return loginTO;
	}
	
		

}
