package control;

import java.io.FileInputStream;
import java.rmi.RemoteException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.RegisterTO;
import base.Compute;
import base.Util;

public class CtrlRegister {

	public RegisterTO doRegistry(RegisterTO registerTO, Compute compute) {
		RegisterTO res = null;

		if ((registerTO != null) && (compute != null)) {
			try {
				String t = Util.GeraMD5(registerTO.getUserPassword());
				registerTO.setUserPassword(t);
				res = compute.executeRegistry(registerTO);
				// compute.sendMessage("registra");
			} catch (RemoteException e) {
				System.out.println("Message: " + registerTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		
		return res;
	}

	public RegisterTO doEditProfile(RegisterTO registerTO, Compute compute) {
		RegisterTO res = null;

		if ((registerTO != null) && (compute != null)) {
			try {
				String password_MD5 = registerTO.getUserPassword();
				if(password_MD5 != null)
					password_MD5 = Util.GeraMD5(password_MD5);
				
				registerTO.setUserPassword(password_MD5);
				res = compute.executeEditProfile(registerTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + registerTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}

		return res;
	}

}
