package control;

import model.SearchTO;

public class SearchImpl {

	public SearchTO Search(SearchTO t){
		//TODO o texto a ser procurado chega aqui no server, deve ser emcapsulado no TO as informações pra volta
		
		if(t.getTipoBusca() == 1) //tuiteSearch
			System.out.println("TuiteSearch");
		
		if(t.getTipoBusca() == 2) //useSearch
			System.out.println("UserSearch");
		
		return t;
	}
}
