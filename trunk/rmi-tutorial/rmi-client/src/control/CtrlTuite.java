package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Tuite;
import model.TuiteTO;
import model.User;
import base.EnumRemoteObject;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

public class CtrlTuite extends UnicastRemoteObject implements RemoteObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static RmiService remoteService;
	private EnumRemoteObject ero = EnumRemoteObject.TUITE;
	private User user;

	public CtrlTuite(User user) throws RemoteException {
        super();
        this.user = user;
        
		try {
			remoteService = Util.getRemoteService();
			remoteService.addObserver(this, this.ero, this.user);
		} catch (RemoteException e){
			System.out.println("Message: " + e.toString());
		}
    }
	
	public TuiteTO doTuite(TuiteTO t){
		if (t != null)
    	{
			try {
				remoteService = Util.getRemoteService();
				remoteService.sendMessage(this.user, this.ero, "CtrlTuite: " + t.getTuite().getText());
				t = remoteService.executeTuite(t);
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
	public boolean truncate(TuiteTO tuiteTO){
		Tuite t = tuiteTO.getTuite();
		if (t.getText().length() > 140){
			t.setText(t.getText().substring(0, 140));
			t.setText(t.getText() + "...");
			t.setTruncated(true);
			tuiteTO.setTuite(t);
			return true;
		}
		t.setTruncated(false);
		return false;
	}
	
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlTuite: " + updateMsg);
    }
	

}
