package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginTO;

public class LoginDao {
	
	public static boolean canLogUser(LoginTO loginTO)
	{
		boolean autenticated = false;

		Connection con = Connections.getConnection();
		String sql = "SELECT user_password FROM tb_login where login = '" + loginTO.getUserLogin() + "'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next())
            {
            	String password = rs.getString("user_password");
            	
            	if (password.startsWith(loginTO.getUserPassword()))
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

	/**
	 * Gera senha codificada em MD5
	 * @param Senha_Plaintext: Formato "puro" da senha
	 * @return: Formato codificado MD5
	 */
    public static String GeraMD5(String Senha_Plaintext)
    {
        try {
            byte[] byteArray = Senha_Plaintext.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(byteArray));
            String senha_MD5 = hash.toString(16);
            return senha_MD5;
        } catch (Exception e){

        }
        return "";
    }
}
