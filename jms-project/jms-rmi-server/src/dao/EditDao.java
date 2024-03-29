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


/**
 * The Class EditDao.
 */
public class EditDao {

	/**
	 * Check the conditions to perform the user's profile update in database.
	 *
	 * @param registerTO the register transfer object
	 * @return the register to
	 */
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

	/**
	 * Update user's profile in database.
	 *
	 * @param registerTO the register transfer object
	 * @return the register to
	 */
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

			//Searching for an user in database
			user = userDao.returnUser(loginTO, true);
			registerTO.setUser(user);
			return registerTO;
		}

		//When the user statuses are no longer protected, we must delete the pending requests
		if(!registerTO.getUser().isProtectedTuite()){
			con = null;
			sql = null;
			con = Connections.getConnection();
			sql = "DELETE FROM rl_follow WHERE id_follow = ? AND notify = TRUE";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setLong(1, registerTO.getUser().getId());

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
		//If the user's password was not changed, update the profile without a new password
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

	/**
	 * Update the register Transfer Object that came from the client side.
	 *
	 * @param registerTO the register transfer object.
	 */
	private void updateTO(RegisterTO registerTO){
		UserDao userDao = new UserDao();
		LoginTO loginTO = new LoginTO(registerTO.getUser().getLoginName());
		User user = null;

		user = userDao.returnUser(loginTO, true);
		registerTO.setUser(user);
	}

	
	/**
	 * Process the user's image.
	 *
	 * @param objImageIcon: the user's photo
	 * @return the image in byte array format
	 */
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
	
	/**
	 * Gets the buffered image.
	 *
	 * @param img: the image
	 * @param transparency: the alpha value of the image
	 * @return the buffered image
	 */
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
