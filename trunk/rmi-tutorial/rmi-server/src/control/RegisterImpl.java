package control;

import model.RegisterTO;
import dao.RegisterDao;

public class RegisterImpl {

	public RegisterTO doRegister(RegisterTO registerTO){
		//TODO implementar essa classe
		RegisterTO res = RegisterDao.insertUser(registerTO);
		
		//System.out.println(registerTO.getUser().getRealName() + "foi registrado...  ou não");
		//if (registerTO.getUser().getRealName().equals("OK")){ res = true; }
		
		return res;
	}
}
