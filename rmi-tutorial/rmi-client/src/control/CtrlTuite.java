package control;

import java.rmi.RemoteException;

import base.Compute;
import model.Tuite;
import model.TuiteTO;

public class CtrlTuite {
	
	
	
	public TuiteTO doTuite(TuiteTO t, Compute compute){
		Tuite tuite = null;
		if ((t != null) && (compute != null))
    	{
			try {
				t = compute.executeTuite(t);
			} catch (RemoteException e){
				System.out.println("Message: " + t.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
		
		
		return t;
	}
	/**
	 * 
	 * @param t tuite that will be truncated
	 * @return true if the tuite was truncated (over 140 chars)
	 */
	public boolean truncate(Tuite t){
		if (t.getText().length() > 140){
			t.setText(t.getText().substring(0, 140));
			t.setText(t.getText() + "...");
			t.setTruncated(true);
			return true;
		}
		t.setTruncated(false);
		return false;
	}
}
