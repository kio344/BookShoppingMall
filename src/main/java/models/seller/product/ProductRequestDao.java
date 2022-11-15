package models.seller.product;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.entity.User;

@Component(value = "productRequestDao")
public class ProductRequestDao {
		
	@Autowired
	private EntityManager em;
	
	public ProductRequestDto get(Long num) {
		
		ProductRequest entity = em.find(ProductRequest.class, num);
		
		return ProductRequestDto.toDto(entity);
	}
	
	public ProductRequestDto get(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getBookName());
		
		return ProductRequestDto.toDto(entity);
	}
	
	public ProductRequestDto save(ProductRequestDto dto){
		
		ProductRequest entity = ProductRequestDto.toEntity(dto);
		User user = em.find(User.class, dto.getSeller().getMemNo());
		
		entity.setNum(dto.getNum());
		entity.setSeller(user);
		entity.setProgress(Progress.Examine);
		
		em.persist(entity);
		
		em.flush();
		
		return ProductRequestDto.toDto(entity);
	}
	
	public void argee(ProductRequestDto dto) {
		System.out.println(dto);
		
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
