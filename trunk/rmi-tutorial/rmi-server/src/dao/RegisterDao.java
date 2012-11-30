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

import model.RegisterTO;

/**
 * The Class RegisterDao.
 */
public class RegisterDao {

	/**
	 * Insert a new user into the databse.
	 *
	 * @param registerTO the register transfer object.
	 * @return the register to
	 */
	public static RegisterTO insertUser(RegisterTO registerTO){
	
		if (!LoginDao.verifyLogin(registerTO.getUser().getLoginName()))
		{
			Connection con = Connections.getConnection();
		      String sql = "INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet, photo) " +
		    		  	   "values (nextval('seq_users'), ?, ?, ?, ?, ?)";
		      try {
		           PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		        
		           stmt.setString(1, registerTO.getUser().getEmail());
		           stmt.setString(2, registerTO.getUser().getRealName());
		           
		           java.sql.Date dataSql = new java.sql.Date(registerTO.getUser().getRegisterDate().getTime());
		           stmt.setDate(3, dataSql);
		           
		           stmt.setBoolean(4, registerTO.getUser().isProtectedTuite());
		           
		           ImageIcon photo = new ImageIcon("noImg.jpg");
		           stmt.setBytes(5, processImage(photo));
		           
		           stmt.executeUpdate();
		           
		           ResultSet rs = stmt.getGeneratedKeys();
		           if (rs.next()){
		        	   registerTO.getUser().setId(rs.getInt("id"));
		        	   }
		           stmt.close();
		           con.close();
		      } catch (SQLException e){
		           System.out.println("Erro no SQL");
		           e.printStackTrace();
		           registerTO.setRegistered(false);
		           registerTO.setErrorMessage("SQL Error!");
		           return registerTO;
		      }
		      
		      if (registerTO.getUser().getId() != 0){
		    	  con = null;
		    	  sql = null;
		    	  con = Connections.getConnection();
			      sql = "INSERT INTO tb_login (id_user, login, user_password) " +
		    		  	   "values (?, ?, ?)";
		    	  
		    	  try {
			           PreparedStatement stmt = con.prepareStatement(sql);
			        
			           stmt.setInt(1, (int)registerTO.getUser().getId());
			           stmt.setString(2, registerTO.getUser().getLoginName());
			           stmt.setString(3, registerTO.getUserPassword());
			           
			           stmt.executeUpdate();
			           
			           ResultSet rs = stmt.getGeneratedKeys();
			           if (rs.next()){
			        	   registerTO.getUser().setId(rs.getInt("id"));
			           }
			           
			           stmt.close();
			           con.close();
			      } catch (SQLException e){
			           System.out.println("Erro no SQL");
			           e.printStackTrace();
			           registerTO.setRegistered(false);
			           registerTO.setErrorMessage("SQL Error!");
			           return registerTO;
			      }
		      }
		} else {
			registerTO.setErrorMessage("Username already in use!");
			registerTO.setRegistered(false);
			return registerTO;
		}
	      
		registerTO.setRegistered(true);    
	    return registerTO;
	}
	
	
	/**
	 * Process the user's image.
	 *
	 * @param objImageIcon: the user's photo
	 * @return the image in byte array format
	 */
	private static byte[] processImage(ImageIcon objImageIcon){

		//ImageIcon objImageIcon = registerTO.getUser().getPhoto();
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
