package models.shop.productReview;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import groovyjarjarantlr4.v4.parse.ANTLRParser.ebnf_return;
import models.entity.Payment;
import models.entity.ProductReview;

@Component
public class ProductReviewDao {

	@Autowired
	private EntityManager em;

	public ProductReviewDto get(Long reviewNum) {

		ProductReview entity = em.find(ProductReview.class, reviewNum);

		return ProductReviewDto.toDto(entity);
	}

	public ProductReviewDto insert(ProductReviewDto reviewDto) {


		
		ProductReview entity = ProductReviewDto.toEntity(reviewDto);

		Payment payment=em.find(Payment.class, reviewDto.getPayment().getNum());
		
		entity.setPayment(payment);
		
		em.persist(entity);

		em.flush();

		return ProductReviewDto.toDto(entity);
	}
	
	public ProductReviewDto getForPayment(Long payment) {
		
		String sql="SELECT pr FROM ProductReview pr WHERE pr.payment=:payment";
				
		
		TypedQuery<ProductReview> query=em.createQuery(sql,ProductReview.class);
		
		query.setParameter("payment", payment);
		
		ProductReviewDto reviewDto=ProductReviewDto.toDto(query.getSingleResult());
		
		return reviewDto;
		
	}
}
