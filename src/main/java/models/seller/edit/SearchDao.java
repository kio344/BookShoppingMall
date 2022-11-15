package models.seller.edit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.seller.product.ProductRequestDto;

@Component
public class SearchDao {
	
	@Autowired
	private EntityManager em;
	
	public List<ProductRequestDto> searchToName(String search, String searchType) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT p FROM ProductRequest p WHERE ");
		
		if(searchType.equals("bookName")) {
			sb.append("bookName LIKE CONCAT('%', :bookName, '%')");
		}else {
			sb.append("writer LIKE CONCAT('%', :writer, '%')");
		}
		
		TypedQuery<ProductRequest> entity = em.createQuery(sb.toString(), ProductRequest.class);
		
		if(searchType.equals("bookName")) {
			entity.setParameter("bookName", search);
		}else {
			entity.setParameter("wrtier", search);
		}
		
		List<ProductRequestDto> list = entity.getResultStream().map(ProductRequestDto::toDto).toList();
		
		return list;
	}
}	
