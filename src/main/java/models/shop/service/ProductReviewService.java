package models.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.shop.payment.PaymentDto;
import models.shop.product.ProductDao;
import models.shop.productReview.ProductReviewDao;
import models.shop.productReview.ProductReviewDto;
import models.shop.productReview.ProductReviewRequest;

/**
 * 상품 리뷰 관련 서비스
 * @author 5563a
 *
 */
@Service
public class ProductReviewService {

	@Autowired
	private ProductReviewDao productReviewDao;
	
	@Autowired
	private ProductDao productDao;
	

	
	/**
	 * 리뷰 작성, 업데이트
	 * 리뷰가 존재하면 업데이트, 존재하지 않으면 작성
	 * 
	 * @param request
	 * @return
	 */
	public ProductReviewDto writeReview(ProductReviewRequest request) {
		
		ProductReviewDto dto=new ProductReviewDto();
		
		PaymentDto paymentDto=new PaymentDto();
		
		paymentDto.setNum(request.getPayment());
				
		dto.setPayment(paymentDto);
		dto.setScore(request.getScore());
		dto.setContent(request.getContent());
		
		ProductReviewDto result =productReviewDao.insertOrUpdate(dto);
		
		productDao.updateProductScore(result.getPayment().getProduct().getNum());
		
		 
		
		return result;
	}
	/**
	 * 리뷰 불러오기
	 * @param payment
	 * @return
	 */
	public ProductReviewDto getReivewForPayment(Long payment) {
		
		return productReviewDao.getForPayment(payment);
	}
}
