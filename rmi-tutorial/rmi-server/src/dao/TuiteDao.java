package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginTO;
import model.SearchTO;
import model.Tuite;
import model.TuiteTO;
import model.User;

public class TuiteDao {

	public TuiteTO Tuitar(TuiteTO tuiteTO) {
		Connection con;
		String sql;
		Tuite t = tuiteTO.getTuite();
		System.out.println("chegay aqui");

		if (t.getMyUser().getId() != 0) {
			con = Connections.getConnection();
			sql = "INSERT INTO tb_tweet (id, text, created_at, my_user) "
					+ "values (nextval('seq_tweets'), ?, ?, ?)";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);

				java.sql.Date dataSql = new java.sql.Date(t.getCreatedAt()
						.getTime());

				stmt.setString(1, t.getText());
				stmt.setDate(2, dataSql);
				stmt.setInt(3, (int) t.getMyUser().getId());

				stmt.executeUpdate();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("SQL Error");
				e.printStackTrace();
				tuiteTO.setErrorMessage("SQL Error");
				return tuiteTO;
			}
		}

		UserDao userDao = new UserDao();
		LoginTO loginTO = new LoginTO(tuiteTO.getTuite().getMyUser()
				.getLoginName());
		User user = null;

		user = userDao.returnUser(loginTO, true);
		if (user != null) {
			t.setMyUser(user);
			tuiteTO.setTuite(t);
		} else
			tuiteTO.setErrorMessage("Tuite owner not found in database");

		return tuiteTO;

	}

	// TODO essa poha
	public SearchTO searchTuite(SearchTO searchTO) {

		Connection con = Connections.getConnection();
		String sql = "SELECT tt.my_user, tt.id, tt.text, tt.created_at, tl.login, tu.real_name, tu.photo, tu.email FROM tb_users tu " 
				+ "JOIN tb_login tl ON tl.id_user = tu.id "
				+ "JOIN tb_tweet tt ON tt.my_user = tl.id_user "
				+ "WHERE tt.text LIKE '%" + searchTO.getText() + "%'";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				User user = new User();
				user.setId(rs.getInt("my_user"));
				user.setEmail(rs.getString("email"));
				user.setLoginName(rs.getString("login"));
				user.setRealName(rs.getString("real_name"));
				// user.setPhoto(rs.getBinaryStream("photo"));

				Tuite tuite = new Tuite(rs.getInt("id"), rs.getString("text"),
						rs.getDate("created_at"), user);

				searchTO.addResultTuite(tuite);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println("Erro no searchTuite");
		}

		return searchTO;
	}
}
