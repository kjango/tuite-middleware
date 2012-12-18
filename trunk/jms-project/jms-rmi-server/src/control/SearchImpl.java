package control;

import model.SearchTO;
import dao.TuiteDao;
import dao.UserDao;

/**
 * The Class SearchImpl.
 */
public class SearchImpl {

	/**
	 * Calls the TuiteDao to perform the tweet search or UserDao to perfrom the user search.
	 *
	 * @param searchTO the search transfer object.
	 * @return the search to
	 */
	public SearchTO Search(SearchTO searchTO){
		
		//tuiteSearch
		if(searchTO.getTipoBusca() == 1){
			
			searchTO = new TuiteDao().searchTuite(searchTO);
			return searchTO;
			
		}else if(searchTO.getTipoBusca() == 2){  //peoplesearch
			
			searchTO = new UserDao().searchPeople(searchTO);
			return searchTO;
			
		}
		
		return searchTO;
	}
}
