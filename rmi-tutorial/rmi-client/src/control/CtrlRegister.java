package control;

import java.io.FileInputStream;
import java.rmi.RemoteException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.RegisterTO;
import base.Compute;

public class CtrlRegister {

	public RegisterTO doRegistry(RegisterTO registerTO, Compute compute) {
		RegisterTO res = null;

		if ((registerTO != null) && (compute != null)) {
			try {
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
				res = compute.executeEditProfile(registerTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + registerTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}

		return res;
	}

}
