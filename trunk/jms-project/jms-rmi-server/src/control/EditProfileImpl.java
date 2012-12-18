package control;

import dao.EditDao;
import dao.UserDao;
import model.RegisterTO;
import model.User;


/**
 * The Class EditProfileImpl.
 */
public class EditProfileImpl {

	/**
	 * Calls the editDAO to perform the user's profile edition.
	 *
	 * @param registerTO the register transfer object
	 * @return the register to
	 */
	public RegisterTO Edit(RegisterTO registerTO){
		
		EditDao editDao = new EditDao();
		return editDao.updateUser(registerTO);
		
	}

}
