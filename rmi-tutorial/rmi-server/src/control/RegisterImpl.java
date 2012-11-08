package control;

import dao.RegisterDao;
import base.Util;
import model.RegisterTO;

public class RegisterImpl {

	public RegisterTO doRegister(RegisterTO registerTO){
		//TODO implementar essa classe
		
		RegisterTO res = null;
		
		res = new RegisterTO("joao@gmail.com", "João Gilmar", "joao", Util.GeraMD5("joao"), false);
		
		RegisterDao.insertUser(res);
		
		
		System.out.println(registerTO.getUser().getRealName() + "foi registrado...  ou não");
		if (registerTO.getUser().getRealName().equals("OK")){
//			res = true;
		}
		
		return res;
	}
}
