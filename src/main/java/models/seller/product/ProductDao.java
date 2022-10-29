package models.seller.product;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.entity.User;

@Component
public class ProductDao {
		
	@Autowired
	private EntityManager em;
	
	public void save(ProductDto dto){
		
		ProductRequest entity = ProductDto.toEntity(dto);
		User user = em.find(User.class, dto.getSeller().getMemNo());
		entity.setSeller(user);
		
		em.persist(entity);
		
		em.flush();
		
	}
	
	public void argee(ProductDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		entity.setProgress(dto.getProgress());
		
		em.persist(entity);
		em.flush();
		
	}
	
}
