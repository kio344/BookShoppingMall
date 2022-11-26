package models.shop.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.Util.JmsUtil;
import models.shop.payment.PaymentDto;
import models.shop.productReview.ProductReviewDao;
import models.shop.productReview.ProductReviewDto;
import models.shop.productReview.ProductReviewRequest;
import models.user.UserDto;

@Service
public class ProductReviewService {

	@Autowired
	private ProductReviewDao productReviewDao;
	
	@Autowired
	private HttpSession session;
	
	public ProductReviewDto writeReview(ProductReviewRequest request) {
		
		
		
		ProductReviewDto dto=new ProductReviewDto();
		
		PaymentDto paymentDto=new PaymentDto();
		paymentDto.setNum(request.getPayment());
				
		dto.setPayment(paymentDto);
		dto.setScore(request.getScore());
		dto.setContent(request.getContent());
		
		
		
		return productReviewDao.insertOrUpdate(dto);
	}
	
	public ProductReviewDto getReivewForPayment(Long payment) {
		
		return productReviewDao.getForPayment(payment);
	}
}
