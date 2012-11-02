package control;

import java.rmi.RemoteException;

import base.Compute;
import model.Tuite;

public class CtrlTuite {
	
	
	
	public Tuite doTuite(Tuite t, Compute compute){
		Tuite tuite = null;
		if ((t != null) && (compute != null))
    	{
			try {
				tuite = compute.executeTuite(t);
			} catch (RemoteException e){
				System.out.println("Exception: " + e.toString());
			}
    	}
		
		
		return tuite;
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