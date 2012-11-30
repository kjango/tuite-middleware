package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.Connections;
import dao.TuiteDao;
import dao.UserDao;
import model.LoginTO;
import model.RegisterTO;
import model.Tuite;
import model.TuiteTO;
import model.User;


/**
 * The Class TuitarImpl.
 */
public class TuitarImpl {

	/**
	 * Calls the TuiteDao to save an user's tweet into the database.
	 *
	 * @param tuiteTO the tuite to
	 * @return the tuite to
	 */
	public TuiteTO writeTuite(TuiteTO tuiteTO){
		tuiteTO = new TuiteDao().Tuitar(tuiteTO);
		return tuiteTO;
	}
		

}
