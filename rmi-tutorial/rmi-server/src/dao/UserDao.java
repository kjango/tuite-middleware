package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginTO;
import model.Tuite;
import model.User;

public class UserDao {

	public static User returnUser(LoginTO loginTO, boolean isAll){
		User user = null;
		
		Connection con = Connections.getConnection();
        String sql = "SELECT DISTINCT tu.id, tu.email, tu.real_name, tu.register_date, tu.protected_tweet, tl.login "
        			+ "FROM tb_users tu JOIN tb_login tl ON tu.id = tl.id_user "
        			+ "WHERE tl.login like '" + loginTO.getUserLogin().trim() + "'";
        try {
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             while(rs.next())
             {
                 user = new User();
                 user.setId(rs.getInt("id"));
                 user.setLoginName(rs.getString("login"));
            	 user.setEmail(rs.getString("email"));
            	 user.setRegisterDate(rs.getDate("register_date"));
            	 user.setProtectedTuite(rs.getBoolean("protected_tweet"));
            	 user.setRealName(rs.getString("real_name"));
             }
             rs.close();
             stmt.close();
        } catch (SQLException e) {
             System.out.println("Erro no SQL");
    }
        if ((isAll) && (user != null)){
        	loginTO.setUser(user);
        	user.setMyTuites(returnTweets(loginTO));
        	user.setFollowing(returnFollowing(loginTO));
        	user.setFollowers(returnFollowers(loginTO));
        	
        }
        
		return user;
	}

	private static ArrayList<Tuite> returnTweets(LoginTO loginTO) {
		ArrayList<Tuite> listTweets = new ArrayList<Tuite>();
		
		 Connection con = Connections.getConnection();
	        String sql = "SELECT tw.id, tw.text, tw.created_at, tw.my_user FROM tb_tweet tw " +
	        		"WHERE tw.my_user = " + loginTO.getUser().getId() + " ORDER BY tw.created_at desc";
	        try {
	             PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery();
	             while(rs.next())
	             {
	                 User user = new User();
	                 user.setId(rs.getInt("my_user"));
	            	 
	            	 Tuite tweet = new Tuite(
	                		 rs.getInt("id"),
	                		 rs.getString("text"),
	                		 rs.getDate("created_at"),
	                		 user );
	            	 
	            	 listTweets.add(tweet);
	             }
	             rs.close();
	             stmt.close();
	        } catch (SQLException e) {
	             System.out.println("Erro no SQL");
	    }
		return listTweets;
	}
	
	//Following = seguindo
	private static ArrayList<User> returnFollowing(LoginTO loginTO) {
		ArrayList<User> listFollowing = new ArrayList<User>();
		
		ArrayList<String> preList = new ArrayList<String>();
		
		 Connection con = Connections.getConnection();
	        String sql = "SELECT rf.id_user, rf.id_follow, tbl.login FROM rl_follow rf JOIN tb_login tl ON tl.id_user = rf.id_user "
	        		+ "JOIN tb_login tbl ON tbl.id_user = rf.id_follow WHERE tl.id_user = " + loginTO.getUser().getId();
	        
	        try {
	             PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery();
	             while(rs.next())
	             {
	            	 preList.add(rs.getString("login"));
	             }
	             rs.close();
	             stmt.close();
	        } catch (SQLException e) {
	             System.out.println("Erro no SQL");
	    }
	        
	        for (int i=0; i < preList.size(); i++){
	        	LoginTO loginTOFollowing = new LoginTO(preList.get(i), null);
	        	listFollowing.add(returnUser(loginTOFollowing, false));
	        }
	        
		return listFollowing;
	}

	private static ArrayList<User> returnFollowers(LoginTO loginTO) {
		ArrayList<User> listFollowers = new ArrayList<User>();
		
		ArrayList<String> preList = new ArrayList<String>();
		
		 Connection con = Connections.getConnection();
	        String sql = "SELECT DISTINCT (login) FROM tb_login WHERE id_user = (select rf.id_user FROM rl_follow rf "
        				+ "JOIN tb_login tl ON tl.id_user = rf.id_follow WHERE rf.id_follow = " + loginTO.getUser().getId() + ")";
	        
	        try {
	             PreparedStatement stmt = con.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery();
	             while(rs.next())
	             {
	            	 preList.add(rs.getString("login"));
	             }
	             rs.close();
	             stmt.close();
	        } catch (SQLException e) {
	             System.out.println("Erro no SQL");
	    }
	        
	        for (int i=0; i < preList.size(); i++){
	        	LoginTO loginTOFollower = new LoginTO(preList.get(i), null);
	        	listFollowers.add(returnUser(loginTOFollower, false));
	        }
	        
		return listFollowers;
	}
}
