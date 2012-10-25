package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	public boolean LoginUser(String user)
	{
		boolean autenticated = false;

		Connection con = Connections.getConnection();
		String sql = "SELECT login FROM users where login = '" + user + "'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
            	String username = rs.getString("login");
            	
            	if (username.startsWith(user))
            	{
            		autenticated = true;
            	}
            }
            rs.close();
            stmt.close();
            
		} catch (SQLException e) {
			System.out.println("Sql Error: " + e.toString());
		}	
	
		return autenticated;
	}

}
