package dao;

	import java.sql.Connection;
	import java.sql.DriverManager;

	public class Connections {
	    public static Connection getConnection(){
	          Connection con = null;
	          try {
	               Class.forName("org.postgresql.Driver");
	               String url = "jdbc:postgresql://localhost:5432/tuite";
	               String user = "postgres";
	               String password = "postgres";
	               con = DriverManager.getConnection(url, user, password);
	          } catch (Exception e){
	              e.printStackTrace();
	          }
	          return con;
	       }
	}
