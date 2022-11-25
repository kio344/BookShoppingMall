package models.seller.edit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDto;

@Service
public class SearchService {
	
	@Autowired
	private SearchDao dao;
	
	public List<ProductRequestDto> search(String searchType, String search, int start, int offset) {
		
		List<ProductRequestDto> list = dao.searchToName(search, searchType, start, offset);
		
		return list;
	}

	public int total(String search, String searchType) {
		
		return dao.getsearchProduct(search, searchType);
		
	}
	
}
