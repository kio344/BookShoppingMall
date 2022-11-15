package models.seller.edit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDto;

@Service
public class SearchService {
	
	@Autowired
	private SearchDao dao;
	
	public List<ProductRequestDto> search(String searchType, String search) {
		
		List<ProductRequestDto> list = dao.searchToName(search, searchType);
		
		return list;
	}
	
}
