package models.seller.product;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Product;

@Component
public class ProductDao {
		
	@Autowired
	private EntityManager em;
	

	
	public void save(ProductDto dto){
		Product entity = ProductDto.toEntity(dto);
		
			em.persist(entity);
			em.flush();
		
	}
}
