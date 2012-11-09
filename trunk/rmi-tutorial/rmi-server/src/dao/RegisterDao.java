package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.RegisterTO;

public class RegisterDao {

	public static RegisterTO insertUser(RegisterTO registerTO){
	
		if (!LoginDao.verifyLogin(registerTO.getUser().getLoginName()))
		{
			Connection con = Connections.getConnection();
		      String sql = "INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet) " +
		    		  	   "values (nextval('seq_users'), ?, ?, ?, ?)";
		      try {
		           PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		        
		           stmt.setString(1, registerTO.getUser().getEmail());
		           stmt.setString(2, registerTO.getUser().getRealName());
		           
		           java.sql.Date dataSql = new java.sql.Date(registerTO.getUser().getRegisterDate().getTime());
		           stmt.setDate(3, dataSql);
		           
		           stmt.setBoolean(4, registerTO.getUser().isProtectedTuite());
		           
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
	      
	        
	    return registerTO;
	}
	
}
