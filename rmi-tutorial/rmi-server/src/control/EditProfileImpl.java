package control;

import model.RegisterTO;
import model.User;

public class EditProfileImpl {

	public RegisterTO Edit(RegisterTO registerTO){
		//TODO implementar corretamen este método
		User user = registerTO.getUser();
		//String Email = regsterTO.getUserEmail();
		//String LoginName = registerTO.getUserLoginName();
		//String RealName = registerTO.getUserRealName();
		//String Password = registerTO.getUserPassword();
		
		//if(Email!=null)
			//user.setEmail(Email);
		
		//if(LoginName!=null)
		//	user.setLoginName(LoginName);
		
		//if(RealName!=null)
		//	user.setRealName(RealName);
		
		//registerTO.setUser(user);
		
		//if(Password!=null)
		//Seta o password do cara no bnco de dados	
		
		return registerTO;
		
	}

}
