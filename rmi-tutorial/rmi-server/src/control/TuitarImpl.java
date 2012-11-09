package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Connections;
import dao.UserDao;
import model.Tuite;
import model.TuiteTO;
import model.User;

public class TuitarImpl {

	public TuiteTO Tuitar(TuiteTO tuiteTO){
		//TODO aqui chega o tuite, aqui deve implementar o processamento do tuite
		Connection con;
		String sql;
		Tuite t = tuiteTO.getTuite();
//		String sql = "INSERT INTO tb_users (id, email, real_name, register_date, protected_tweet) " +
//  		  	   "values (nextval('seq_users'), ?, ?, ?, ?)";
//		
		//Inserindo Tuite no banco
		  if (t.getMyUser().getId() != 0){
	    	  con = Connections.getConnection();
		      sql = "INSERT INTO tb_tweet (id, text, created_at, my_user) " +
	    		  	   "values (nextval('seq_tweets'), ?, ?, ?)";	    	  
	    	  try {
		           PreparedStatement stmt = con.prepareStatement(sql);
		        
		           stmt.setString(1, t.getText());
		           stmt.setDate(1, (Date) t.getCreatedAt());
		           stmt.setInt(3, (int) t.getMyUser().getId());
		           
		           stmt.executeUpdate();		           
//		           ResultSet rs = stmt.getGeneratedKeys();
//		           if (rs.next()){
//		        	   registerTO.getUser().setId(rs.getInt("id"));
//		           }
		           stmt.close();
		           con.close();
		      } catch (SQLException e){
		           System.out.println("Erro no SQL");
		           e.printStackTrace();
		           tuiteTO.setErrorMessage("Erro no SQL");
		           return tuiteTO;
		      }
	      }
		  
		  UserDao userDao = new UserDao();
		  User user = null;
		  return null;
		  
//		  user = userDao.
//				  return tuiteTO;
//		//Início Teste
//			System.out.println("Estou aqui no server:  "+ t.getText());
//		//Fim Teste
//		
	}

}
