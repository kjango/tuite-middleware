package dao;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.LoginTO;
import model.RegisterTO;
import model.Tuite;
import model.User;

public class EditDao {

	public RegisterTO updateUser(RegisterTO registerTO){
		if (registerTO.isModifiedLogin() && !LoginDao.verifyLogin(registerTO.getUser().getLoginName()))
			return update(registerTO);
		else if(!registerTO.isModifiedLogin())
			return update(registerTO);
		else{
			registerTO.setErrorMessage("Login already exists!");
			return registerTO;
		}

	}

	public RegisterTO update(RegisterTO registerTO){

		Connection con = Connections.getConnection();
		
		String sql = "UPDATE tb_users SET email = ? , real_name = ?, protected_tweet = ?, photo = ?" 
				+ "WHERE id = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, registerTO.getUser().getEmail());
			stmt.setString(2, registerTO.getUser().getRealName());
			stmt.setBoolean(3, registerTO.getUser().isProtectedTuite());
			stmt.setBytes(4, processImage(registerTO.getUser().getPhoto()));
			stmt.setLong(5, registerTO.getUser().getId());

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e){
			System.out.println("Erro no SQL");
			e.printStackTrace();
			registerTO.setErrorMessage("SQL Error!");

			UserDao userDao = new UserDao();
			LoginTO loginTO = new LoginTO(registerTO.getUser().getLoginName());
			User user = null;

			//Buscando usuário no banco
			user = userDao.returnUser(loginTO, true);
			registerTO.setUser(user);
			return registerTO;
		}

		if(registerTO.getUserPassword() != null){
			con = null;
			sql = null;
			con = Connections.getConnection();
			sql = "UPDATE tb_login SET login = ? , user_password = ?" 
					+ "WHERE id_user = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, registerTO.getUser().getLoginName());
				stmt.setString(2, registerTO.getUserPassword());
				stmt.setLong(3, registerTO.getUser().getId());

				stmt.executeUpdate();

				stmt.close();
				con.close();
			}catch (SQLException e){
				System.out.println("Erro no SQL");
				e.printStackTrace();
				registerTO.setErrorMessage("SQL Error!");

				updateTO(registerTO);
				return registerTO;
			}
		}
		//Se não tiver passord alterado, altera sem password
		else{
			con = null;
			sql = null;
			con = Connections.getConnection();
			
			sql = "UPDATE tb_login SET login = ? " 
					+ "WHERE id_user = ?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, registerTO.getUser().getLoginName());
				stmt.setLong(2, registerTO.getUser().getId());

				stmt.executeUpdate();

				stmt.close();
				con.close();
			} catch (SQLException e){
				System.out.println("Erro no SQL");
				e.printStackTrace();
				registerTO.setErrorMessage("SQL Error!");

				updateTO(registerTO);
				return registerTO;
			}

		}


		updateTO(registerTO);
		registerTO.setRegistered(true);
		return registerTO;
	}

	private void updateTO(RegisterTO registerTO){
		UserDao userDao = new UserDao();
		LoginTO loginTO = new LoginTO(registerTO.getUser().getLoginName());
		User user = null;

		user = userDao.returnUser(loginTO, true);
		registerTO.setUser(user);
	}

	
	private byte[] processImage(ImageIcon objImageIcon){
		Image img = objImageIcon.getImage();
		if (img instanceof RenderedImage == false)
			img = getBufferedImage(img, Transparency.TRANSLUCENT);
		ByteArrayOutputStream byteOS = new ByteArrayOutputStream();

		try {
			ImageIO.write((RenderedImage) img, "jpg", byteOS);
		} catch (IOException e) {

		}
		byte [] array  = byteOS.toByteArray();
		return array;
	}
	
	private static BufferedImage getBufferedImage(Image img, int transparency)
	{
		if (img instanceof BufferedImage)
			return (BufferedImage) img;

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage bi = gc.createCompatibleImage(img.getWidth(null), img.getHeight(null), transparency);

		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return bi;
	}
	
	
}
