package control;

import model.RegisterTO;

public class RegisterImpl {

	public boolean doRegister(RegisterTO registerTO){
		//TODO implementar essa classe
		boolean res = false;
		
		System.out.println(registerTO.getUserRealName() + "foi registrado...  ou não");
		if (registerTO.getUserRealName().equals("OK")){
			res = true;
		}
		
		return res;
	}
}
