package models.admin.product;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.seller.product.ProductDto;

@Component
public class AdminProductDao {

	@Autowired
	private EntityManager em;
	
	public List<ProductDto> getProducts() {
		
		TypedQuery<ProductRequest> entity = em.createQuery("SELECT p FROM Product p", ProductRequest.class);
		
		List<ProductDto> list = entity.getResultStream().map(ProductDto::toDto).toList();
		
		return list;
	}
}
