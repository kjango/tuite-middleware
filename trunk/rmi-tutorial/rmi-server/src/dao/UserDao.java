package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import model.FollowTO;
import model.LoginTO;
import model.SearchTO;
import model.Tuite;
import model.User;

public class UserDao {

	public static User returnUser(LoginTO loginTO, boolean isAll) {
		User user = null;

		Connection con = Connections.getConnection();
//		String sql = "SELECT DISTINCT tu.id, tu.email, tu.real_name, tu.register_date, tu.protected_tweet, tl.login "
//				+ "FROM tb_users tu JOIN tb_login tl ON tu.id = tl.id_user "
//				+ "WHERE tl.login like '" + loginTO.getUserLogin().trim() + "'";
		
		String sql = "SELECT DISTINCT tu.id, tu.email, tu.real_name, tu.register_date, tu.protected_tweet, tu.photo, tl.login "
				+ "FROM tb_users tu JOIN tb_login tl ON tu.id = tl.id_user "
				+ "WHERE tl.login like '" + loginTO.getUserLogin().trim() + "'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setRegisterDate(rs.getDate("register_date"));
				user.setProtectedTuite(rs.getBoolean("protected_tweet"));
				user.setRealName(rs.getString("real_name"));
				ImageIcon photo = new ImageIcon(rs.getBytes("photo"));
				user.setPhoto(photo);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL returnUser");
		}
		if ((isAll) && (user != null)) {
			loginTO.setUser(user);
			user.setMyTuites(returnTweets(loginTO));
			user.setFollowing(returnFollowing(loginTO));
			user.setFollowers(returnFollowers(loginTO));
			user.setTuites(returnAllTweets(loginTO));
		}

		return user;
	}

	private static ArrayList<Tuite> returnTweets(LoginTO loginTO) {
		ArrayList<Tuite> listTweets = new ArrayList<Tuite>();

		Connection con = Connections.getConnection();
		String sql = "SELECT tw.id, tw.text, tw.created_at, tw.my_user FROM tb_tweet tw "
				+ "WHERE tw.my_user = "
				+ loginTO.getUser().getId()
				+ " ORDER BY tw.created_at desc";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// User user = new User();
				// user.setId(rs.getInt("my_user"));
				User user = loginTO.getUser();

				Tuite tweet = new Tuite(rs.getInt("id"), rs.getString("text"),
						rs.getTimestamp("created_at"), user);

				listTweets.add(tweet);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL returnTweets");
		}
		return listTweets;
	}

	// Following = seguindo
	private static ArrayList<User> returnFollowing(LoginTO loginTO) {
		ArrayList<User> listFollowing = new ArrayList<User>();

		ArrayList<String> preList = new ArrayList<String>();

		Connection con = Connections.getConnection();
		String sql = "SELECT rf.id_user, rf.id_follow, tbl.login FROM rl_follow rf "
				+ "JOIN tb_login tl ON tl.id_user = rf.id_user "
				+ "JOIN tb_login tbl ON tbl.id_user = rf.id_follow WHERE tl.id_user = "
				+ loginTO.getUser().getId();

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				preList.add(rs.getString("login"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL returnFollowing");
		}

		for (int i = 0; i < preList.size(); i++) {
			LoginTO loginTOFollowing = new LoginTO(preList.get(i), null);
			listFollowing.add(returnUser(loginTOFollowing, false));
		}

		return listFollowing;
	}

	private static ArrayList<User> returnFollowers(LoginTO loginTO) {
		ArrayList<User> listFollowers = new ArrayList<User>();

		ArrayList<String> preList = new ArrayList<String>();

		Connection con = Connections.getConnection();
		String sql = "SELECT rf.id_user, tl.login FROM rl_follow rf JOIN tb_login tl ON tl.id_user = rf.id_user WHERE rf.id_follow = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, loginTO.getUser().getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				preList.add(rs.getString("login"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL returnFollowers");
		}

		for (int i = 0; i < preList.size(); i++) {
			LoginTO loginTOFollower = new LoginTO(preList.get(i), null);
			listFollowers.add(returnUser(loginTOFollower, false));
		}

		return listFollowers;
	}

	private static ArrayList<Tuite> returnAllTweets(LoginTO loginTO) {
		ArrayList<Tuite> listAllTweets = new ArrayList<Tuite>();

		Connection con = Connections.getConnection();
		// String sql =
		// "SELECT * FROM tb_tweet tt join tb_login tl on tt.my_user = tl.id_user join tb_users tu on tt.my_user = tu.id "
		// +
		// "WHERE tt.my_user = "
		// + loginTO.getUser().getId()
		// + " OR my_user = (SELECT id_follow FROM rl_follow WHERE id_user = "
		// + loginTO.getUser().getId() + ") ORDER BY created_at DESC";

		String sql = "SELECT tt.my_user, tt.id, tt.text, tt.created_at, tl.login, tu.real_name, tu.photo, tu.email FROM tb_tweet tt "
				+ "join tb_login tl on tt.my_user = tl.id_user "
				+ "join tb_users tu on tt.my_user = tu.id "
				+ "left join rl_follow rf on rf.id_follow  = tl.id_user "
				+ "WHERE (tt.my_user = ?  OR rf.id_user = ?) "
				+ "ORDER BY created_at ";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, loginTO.getUser().getId());
			stmt.setLong(2, loginTO.getUser().getId());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("my_user"));
				user.setEmail(rs.getString("email"));
				user.setLoginName(rs.getString("login"));
				user.setRealName(rs.getString("real_name"));
				
				ImageIcon photo = new ImageIcon(rs.getBytes("photo"));
				user.setPhoto(photo);
				// user.setPhoto(rs.getBinaryStream("photo"));

				Tuite tweet = new Tuite(rs.getInt("id"), rs.getString("text"),
						rs.getTimestamp("created_at"), user);

				listAllTweets.add(tweet);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL returnAllTweets");
		}
		return listAllTweets;
	}

	public static FollowTO addFollower(FollowTO followTO) {

		Connection con = null;
		String sql = null;
		con = Connections.getConnection();
		sql = "INSERT INTO rl_follow (id_user, id_follow, notify) "
				+ "values (?, ?, ?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1, followTO.getFollower().getId());
			stmt.setLong(2, followTO.getFollowed().getId());
			stmt.setBoolean(3, followTO.getFollowed().isProtectedTuite());

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL addFollower");
			e.printStackTrace();
			followTO.setSuccess(false);
			followTO.setErrorMessage("SQL Error!");
			return followTO;
		}

		LoginTO loginTO = new LoginTO(followTO.getFollower().getLoginName());

		followTO.setSuccess(true);
		followTO.setFollower(returnUser(loginTO, true));

		return followTO;
	}

	public static FollowTO removeFollower(FollowTO followTO) {

		Connection con = null;
		String sql = null;
		con = Connections.getConnection();
		sql = "DELETE FROM rl_follow WHERE id_user = ? AND id_follow = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1, followTO.getFollower().getId());
			stmt.setLong(2, followTO.getFollowed().getId());

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL removeFollower");
			e.printStackTrace();
			followTO.setSuccess(false);
			followTO.setErrorMessage("SQL Error!");
			return followTO;
		}
		LoginTO loginTO = new LoginTO(followTO.getFollower().getLoginName());

		followTO.setSuccess(true);
		followTO.setFollower(returnUser(loginTO, true));

		return followTO;
	}

	public static FollowTO updateNotifyFollow(FollowTO followTO) {

		Connection con = null;
		String sql = null;
		con = Connections.getConnection();
		sql = "UPDATE rl_follow SET notify = ? WHERE id_user = ? AND id_follow = ?";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setBoolean(1, followTO.isNotifyFollower());
			stmt.setLong(2, followTO.getFollower().getId());
			stmt.setLong(3, followTO.getFollowed().getId());

			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Erro no SQL updateNotifyFollow");
			e.printStackTrace();
			followTO.setSuccess(false);
			followTO.setErrorMessage("SQL Error!");
			return followTO;
		}
		LoginTO loginTO = new LoginTO(followTO.getFollower().getLoginName());

		followTO.setSuccess(true);
		followTO.setFollower(returnUser(loginTO, true));

		return followTO;
	}
	
	public SearchTO searchPeople(SearchTO searchTO) {

		Connection con = Connections.getConnection();
		String sql = "SELECT tu.id, tl.login FROM tb_users tu JOIN tb_login tl ON tl.id_user = tu.id WHERE tl.login LIKE '%" + searchTO.getText() + "%' " +
				"OR tu.real_name LIKE '%" +  searchTO.getText() + "%'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LoginTO aux = new LoginTO(rs.getString("login"));
				User usr = returnUser(aux, false);
				searchTO.addResultUser(usr);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erro no searchPeople");
		}

		return searchTO;
	}
}
