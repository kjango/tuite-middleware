package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.Util;

import model.LoginTO;
import model.RegisterTO;
import control.RegisterImpl;

public class LoginDao {
	
	public static boolean canLogUser(LoginTO loginTO)
	{
		boolean autenticated = false;
		
		String pass = loginTO.getUserPassword().trim();
		
		Connection con = Connections.getConnection();
		String sql = "SELECT user_password FROM tb_login where login = '" + loginTO.getUserLogin() + "'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
            	String password = rs.getString("user_password").trim();
            	
            	if (password.startsWith(pass))
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

	public static boolean verifyLogin(String login){
		
		boolean exist = false;

		Connection con = Connections.getConnection();
		String sql = "SELECT login FROM tb_login where login = '" + login + "'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
            	String loginBD = rs.getString("login").trim();
            	
            	if (loginBD.startsWith(login))
            	{
            		exist = true;
            	}
            }
            rs.close();
            stmt.close();
            
		} catch (SQLException e) {
			System.out.println("Sql Error: " + e.toString());
			return exist;
		}	
	
		return exist;
	}
	
}
