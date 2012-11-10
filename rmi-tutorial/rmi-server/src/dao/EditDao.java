package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginTO;
import model.RegisterTO;
import model.Tuite;
import model.User;

public class EditDao {
	
	public RegisterTO updateUser(RegisterTO registerTO){
		if(registerTO.isModifiedLogin())
			System.out.println("TRUE!!!!");
		else
			System.out.println("FALSE!!!!");
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

//			String sql = "UPDATE tb_users (email, real_name) " +
//					"values (?, ?)"
//					+ "WHERE id = '" + registerTO.getUser().getId() + "'";
			String sql = "UPDATE tb_users SET email = ? , real_name = ?, protected_tweet = ?" 
					+ "WHERE id = ?";

			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setString(1, registerTO.getUser().getEmail());
				stmt.setString(2, registerTO.getUser().getRealName());
				stmt.setBoolean(3, registerTO.getUser().isProtectedTuite());
				stmt.setLong(4, registerTO.getUser().getId());
				
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
//				sql = "UPDATE tb_login (login, user_password) " +
//						"values (?, ?)"
//						+ "WHERE id_user = '" + registerTO.getUser().getId() + "'";
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
//				sql = "UPDATE tb_login (login) " +
//						"values (?)"
//						+ "WHERE id_user = '" + registerTO.getUser().getId() + "'";
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
				
//				//Buscando usuário alterado com SUCESSO
//				UserDao userDao = new UserDao();
//				LoginTO loginTO = new LoginTO(registerTO.getUser().getLoginName());
//				User user = null;
//
//				user = userDao.returnUser(loginTO, true);
//				registerTO.setUser(user);
//				return registerTO;
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

}
