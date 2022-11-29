package models.seller.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDao;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;

@Service
public class SellerOrderService {

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ProductRequestDao requestDao;
	/**
	 * @author kimminho
	 * @param request
	 * 판매자가 고객에게 상품 배송중으로 바꾸면 payment DB에 progress가 SHIPPING으로 변경, count 는 -1하기. 
	 */
	public void shipping(HttpServletRequest request) {
		/** 유효성 검사 S*/
		String [] nums = request.getParameterValues("payment_num");
		if(nums == null) {
			throw new RuntimeException("제품을 선택해 주세요.");
		}
		/** 유효성 검사 E*/
		
		/** 제품 배송 S*/
		for(String num : nums) {
			PaymentDto dto = paymentDao.get(Long.parseLong(num));
			requestDao.updatePayment(dto, Long.parseLong(num));
		}
		/** 제품 배송 E*/
		
	}
	public void cencel(HttpServletRequest request) {
		/** 유효성 검사*/
		String [] nums = request.getParameterValues("payment_num");
		if(nums == null) {
			throw new RuntimeException("제품을 선택해 주세요.");
		}
		/** 유효성 검사 E*/
		
		for(String num : nums) {
			PaymentDto dto = paymentDao.get(Long.parseLong(num));
			requestDao.cencel(dto, Long.parseLong(num));
		}
		
	}

}
