package models.seller.product;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Product;
import models.entity.ProductRequest;
import models.entity.User;
import models.shop.ProductDto;

@Component(value = "productRequestDao")
public class ProductRequestDao {
		
	@Autowired
	private EntityManager em;
	
	public ProductRequestDto get(Long num) {
		
		ProductRequest entity = em.find(ProductRequest.class, num);
		
		return ProductRequestDto.toDto(entity);
	}
	
	public void save(ProductRequestDto dto){
		
		ProductRequest entity = ProductRequestDto.toEntity(dto);
		User user = em.find(User.class, dto.getSeller().getMemNo());
		entity.setSeller(user);
		
		em.persist(entity);
		
		em.flush();
		
	}
	
	public void argee(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		entity.setProgress(dto.getProgress());
		
		em.persist(entity);
		em.flush();
		
	}
	
	public void remove(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		em.remove(entity);
		
		em.flush();
		
	}
	
}
