package models.shop.productReview;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	
	/**
	 * 상품 리뷰 쓰기 이미 존재한다면 업데이트
	 * @author 5563a
	 * @param reviewDto
	 * @return
	 */
	public ProductReviewDto insertOrUpdate(ProductReviewDto reviewDto) {

		Payment payment=null;
		
		try {
			String sql = "SELECT pr FROM ProductReview pr WHERE pr.payment=:payment";

			payment = em.find(Payment.class, reviewDto.getPayment().getNum());

			TypedQuery<ProductReview> query = em.createQuery(sql, ProductReview.class);

			query.setParameter("payment", payment);

			ProductReview entity = query.getSingleResult();

			entity.setContent(reviewDto.getContent());
			entity.setScore(reviewDto.getScore());

			em.persist(entity);

			em.flush();

			return ProductReviewDto.toDto(entity);

		} catch (NoResultException e) {

			ProductReview productReview = new ProductReview();

			productReview.setPayment(payment);

			productReview.setScore(reviewDto.getScore());

			productReview.setContent(reviewDto.getContent());

			em.persist(productReview);

			em.flush();

			return ProductReviewDto.toDto(productReview);

		}



	}

	/**
	 * 결제정보로 리뷰 가져오기
	 * @author 5563a
	 * @param paymentNum
	 * @return
	 */
	public ProductReviewDto getForPayment(Long paymentNum) {

		String sql = "SELECT pr FROM ProductReview pr WHERE pr.payment=:payment";

		Payment payment = em.find(Payment.class, paymentNum);

		TypedQuery<ProductReview> query = em.createQuery(sql, ProductReview.class);

		query.setParameter("payment", payment);

		ProductReviewDto reviewDto = ProductReviewDto.toDto(query.getSingleResult());

		return reviewDto;

	}
	
	public ProductReview getsForProduct(Long )
}
