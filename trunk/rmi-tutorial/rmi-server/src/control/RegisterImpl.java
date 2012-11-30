package control;

import model.RegisterTO;
import dao.RegisterDao;

/**
 * The Class RegisterImpl.
 */
public class RegisterImpl {

	/**
	 * Calls the RegisterDao to perform the user's registration.
	 *
	 * @param registerTO the register transfer object
	 * @return the register to
	 */
	public RegisterTO doRegister(RegisterTO registerTO){
		RegisterTO res = RegisterDao.insertUser(registerTO);
		
		//System.out.println(registerTO.getUser().getRealName() + "foi registrado...  ou não");
		//if (registerTO.getUser().getRealName().equals("OK")){ res = true; }
		
		return res;
	}
}
