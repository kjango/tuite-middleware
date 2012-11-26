package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.SearchTO;
import base.RemoteObserver;
import base.RmiService;
import base.Util;

public class CtrlSearch extends UnicastRemoteObject implements RemoteObserver{

	public CtrlSearch() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1958822906117516333L;
	public static RmiService remoteService;

	public SearchTO doSearch(SearchTO searchTO){
		if (searchTO != null) {
			try {
				remoteService = Util.getRemoteService();
				return remoteService.executeSearch(searchTO);
				
//				res = compute.executeRegistry(registerTO);
			} catch (RemoteException e) {
				System.out.println("Message: " + searchTO.getErrorMessage()
						+ "\nException: " + e.toString());
			}
		}
		return null;
	}

	@Override
	public void update(Object observable, Object updateMsg)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
		
		
		
}


