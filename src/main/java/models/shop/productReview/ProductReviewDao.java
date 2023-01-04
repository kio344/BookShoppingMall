package models.shop.productReview;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Payment;
import models.entity.ProductRequest;
import models.entity.ProductReview;
import models.shop.payment.PaymentDto;

/**
 * 상품리뷰 DB
 * @author 5563a
 *
 */


@Component
public class ProductReviewDao {

	@Autowired
	private EntityManager em;

	/**
	 * 리뷰 가져오기
	 * @param reviewNum
	 * @return
	 */
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
	/**
	 * 
	 * @author kimminho
	 * @param list
	 * @return
	 */
	public List<ProductReviewDto> getProductReview(List<PaymentDto> list) {
		PaymentDto dto = null;
		
		List<ProductReviewDto> productList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			dto = list.get(i);
		String sql = "SELECT r FROM ProductReview r WHERE r.payment=:payment_num";
		
		TypedQuery<ProductReview> query = em.createQuery(sql, ProductReview.class);
		Payment entity = em.find(Payment.class, dto.getNum());
		
		query.setParameter("payment_num", entity);
		
		productList.add(ProductReviewDto.toDto(query.getSingleResult()));
		
		}
		return productList;
	}
	
	/**
	 * 해당 제품의 리뷰
	 * 
	 * @param productNum
	 * @return 해당 제품의 리뷰
	 */
	public List<ProductReviewDto> getsForProduct(Long productNum) {
		
		ProductRequest product=em.find(ProductRequest.class, productNum);
		
		String sql = "SELECT pr FROM ProductReview pr WHERE pr.payment.product=:product ";
		
		TypedQuery<ProductReview> query=em.createQuery(sql,ProductReview.class);
		
		query.setParameter("product", product);
		
		List<ProductReviewDto> result=query.getResultStream().map(t -> ProductReviewDto.toDto(t) ).toList();
		
		
		
		return result;
	}
}
