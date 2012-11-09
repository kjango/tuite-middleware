package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Connections;
import dao.UserDao;
import model.LoginTO;
import model.Tuite;
import model.TuiteTO;
import model.User;

public class TuitarImpl {

	public TuiteTO Tuitar(TuiteTO tuiteTO){
		//TODO aqui chega o tuite, aqui deve implementar o processamento do tuite
		Connection con;
		String sql;
		Tuite t = tuiteTO.getTuite();
		
		//Inserindo Tuite no banco
		  if (t.getMyUser().getId() != 0){
	    	  con = Connections.getConnection();
		      sql = "INSERT INTO tb_tweet (id, text, created_at, my_user) " +
	    		  	   "values (nextval('seq_tweets'), ?, ?, ?)";	    	  
	    	  try {
		           PreparedStatement stmt = con.prepareStatement(sql);
		           
		           java.sql.Date dataSql = new java.sql.Date(t.getCreatedAt().getTime());
		           
		           stmt.setString(1, t.getText());
		           stmt.setDate(2, dataSql);
		           stmt.setInt(3, (int) t.getMyUser().getId());
		           
		           stmt.executeUpdate();		           
		           stmt.close();
		           con.close();
		      } catch (SQLException e){
		           System.out.println("SQL Error");
		           e.printStackTrace();
		           tuiteTO.setErrorMessage("SQL Error");
		           return tuiteTO;
		      }
	      }
		  
		  UserDao userDao = new UserDao();
		  LoginTO loginTO = new LoginTO(tuiteTO.getTuite().getMyUser().getLoginName());
		  User user = null;
		  
		  //Buscando novo usuario com array novo de tuites
		  user = userDao.returnUser(loginTO, true);
		  if(user != null){
			  t.setMyUser(user);
			  tuiteTO.setTuite(t);
		  }
		  else
			  tuiteTO.setErrorMessage("Tuite owner not found in database");
		  		           
		  return tuiteTO;

	}

}
