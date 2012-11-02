package control;

import model.RegisterTO;

public class RegisterImpl {

	public RegisterTO doRegister(RegisterTO registerTO){
		//TODO implementar essa classe
		RegisterTO res = null;
		
		System.out.println(registerTO.getUserRealName() + "foi registrado...  ou não");
		if (registerTO.getUserRealName().equals("OK")){
//			res = true;
		}
		
		return res;
	}
}
