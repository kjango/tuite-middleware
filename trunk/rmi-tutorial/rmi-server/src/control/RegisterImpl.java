package control;

import model.RegisterTO;

public class RegisterImpl {

	public boolean doRegister(RegisterTO registerTO){
		//TODO implementar essa classe
		boolean res = false;
		
		System.err.println("Monga");
		System.out.println(registerTO.getUserRealName() + "foi registrado...  ou não");
		
		return res;
	}
}
