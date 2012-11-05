package control;

import java.rmi.RemoteException;

import base.Compute;

import model.SearchTO;

public class CtrlSearch {

	public SearchTO doSearch(SearchTO searchTO, Compute compute){
		SearchTO obj = null;
		if ((searchTO != null) && (compute != null))
    	{
			try {
				obj = compute.executeSearch(searchTO);
			} catch (RemoteException e){
				System.out.println("Exception: " + e.toString());
			}
    	}
		return obj;
	}
		
		
		
}


