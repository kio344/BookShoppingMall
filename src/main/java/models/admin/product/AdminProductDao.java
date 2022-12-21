package models.admin.product;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.seller.product.ProductRequestDto;

@Component
public class AdminProductDao {

	@Autowired
	private EntityManager em;
	
	/**
	 * 모든 상품 가져오기
	 * @author kimminho
	 * @return List<ProductRequestDto>
	 */
	public List<ProductRequestDto> getProducts() {
		
		TypedQuery<ProductRequest> entity = em.createQuery("SELECT r FROM ProductRequest r", ProductRequest.class);
		
		List<ProductRequestDto> list = entity.getResultStream().map(ProductRequestDto::toDto).toList();
		
		return list;
	}
}
