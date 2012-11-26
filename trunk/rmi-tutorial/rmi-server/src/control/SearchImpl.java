package control;

import model.SearchTO;
import dao.TuiteDao;
import dao.UserDao;

public class SearchImpl {

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
