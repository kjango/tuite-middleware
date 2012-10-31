package control;

import java.rmi.RemoteException;

import model.RegisterTO;
import base.Compute;


public class CtrlRegister{

	public boolean doRegistry(RegisterTO registerTO, Compute compute){
		boolean res = false;

    	if ((registerTO != null) && (compute != null))
    	{
			try {
				res = compute.executeRegistry(registerTO);
//	   	 		compute.sendMessage("registra");
			} catch (RemoteException e){
				System.out.println("Message: " + registerTO.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
		
		return res;
	}
}
