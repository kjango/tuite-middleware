package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import model.Tuite;
import model.User;

public class CtrlLogin {
	
	public User login(String name, char[] password){
		//TODO tudo aqui xD
		User user = null;
		
		if (true){
//			user = new User(0, null, null, null, null, false, null, null, null, null);
			
			/*
			 * Só para testes
			 */
			BufferedImage photo;
			try {
				ArrayList<Tuite> alTuites = new ArrayList<Tuite>();
				photo = ImageIO.read(new File("img1.jpg"));
				user = new User(0, "user@tests.com", "Hombre De Testes", photo, new Date(), false, null, alTuites, null, null);
				Tuite tuite1 = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user);
				
				for (int i = 0; i < 12; i++) {
					user.addTuite(tuite1);
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			/*
			 * Fim Testes
			 */
		}
		return user;
	}
}
