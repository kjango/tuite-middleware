package server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import model.Tuite;
import model.User;

import base.Compute;
import base.Task;
import dao.LoginDao;


public class ComputeEngine
    implements Compute {

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        System.out.println("got compute task: " + t);
        return t.execute();
    }
    
    public boolean UserLogin(String user) throws RemoteException{
    	
    	LoginDao loginDao = new LoginDao();
    	return loginDao.LoginUser(user);
    	
    }
	
    public String decirHola() throws RemoteException{
		return "Hola a todos!";
	}
    
    @Override
	public User doLogin(String name, char[] password) throws RemoteException {
		//TODO implementar o corpo desse código (aqui chega o login e a senha)
		
		User user = null;
		System.out.println(name);
		System.out.println(password);
		
		/**
		 * TESTES
		 */
		try {
		BufferedImage photo;
		ArrayList<Tuite> alTuites = new ArrayList<Tuite>();
		ArrayList<Tuite> alTuites1 = new ArrayList<Tuite>();
		ArrayList<User> alUser = new ArrayList<User>();
		
		photo = ImageIO.read(new File("img1.jpg"));
		user = new User(1, "user2@tests.com", "Seguidor De Testes", null, new Date(), false, alUser, alTuites, null, null);
		User user2 = new User(0, "user@tests.com", "Hombre De Testes", photo, new Date(), false, null, null, alTuites1, null);
		User user3 = new User(2, "user3@tests.com", "Terceiro De Testes", null, new Date(), false, null, null, null, null);
		Tuite tuite1 = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user2);
		Tuite tuite2 = new Tuite(0, "OMG estou tuitando.", new Date(), user3);

		user.addFollowing(user2);
		user.addTuite(tuite2);

		for (int i = 0; i < 12; i++) {
			user2.addMyTuite(tuite1);
			user.addTuite(tuite1);
		}
		
		user.addTuite(tuite2);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}
}
