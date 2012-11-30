package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import view.MainScreen;

import model.Tuite;
import model.TuiteTO;
import model.User;
import base.EnumRemoteObject;
import base.RemoteObserver;
import base.RmiService;
import base.Util;


/**
 * The Class CtrlTuite.
 */
public class CtrlTuite extends UnicastRemoteObject implements RemoteObserver {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The remote interface. */
	public static RmiService remoteService;
	
	/** The ero. */
	private EnumRemoteObject ero = EnumRemoteObject.TUITE;
	
	/** The main screen. */
	private view.MainScreen main;

	/**
	 * Instantiates a new ctrl tuite.
	 *
	 * @param main: the mainscreen
	 * @throws RemoteException: the remote exception
	 */
	public CtrlTuite(MainScreen main) throws RemoteException {
        super();
        this.main = main;
        
		try {
			remoteService = Util.getRemoteService();
			remoteService.addObserver(this, this.ero, main.getUser());
		} catch (RemoteException e){
			System.out.println("Message: " + e.toString());
		}
    }
	
	/**
	 * Sends the user's twee to the server.
	 *
	 * @param t: the tuite transfer object
	 * @return the tuite to
	 */
	public TuiteTO doTuite(TuiteTO t){
		if (t != null)
    	{
			try {
				remoteService = Util.getRemoteService();
				t = remoteService.executeTuite(t);
				remoteService.sendMessage(main.getUser(), this.ero, "CtrlTuite: " + t.getTuite().getText());
			} catch (RemoteException e){
				System.out.println("Message: " + t.getErrorMessage() + "\nException: " + e.toString());
			}
    	}
		
		
		return t;
	}
	
	/**
	 * Truncate.
	 *
	 * @param tuiteTO the tuite to
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
	
    /* (non-Javadoc)
     * @see base.RemoteObserver#update(java.lang.Object, java.lang.Object)
     */
    @Override
    public void update(Object observable, Object updateMsg)
            throws RemoteException {
    	System.out.println("CtrlTuite: " + updateMsg);
    	this.main.updateUser();
    }
	

}
