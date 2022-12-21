package models.shop.service;

import static common.Util.JmsUtil.getLoginUser;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDto;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;
import models.shop.product.ProductDao;
import models.user.UserDto;

/**
 * 결제 서비스
 * @author 5563a
 *
 */
@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private HttpSession session;

	/**
	 * 결제 정보 추가
	 * 
	 * @param paymentRequest
	 * @return
	 */
	public PaymentDto paymentProcess(PaymentRequest paymentRequest) {

		UserDto user=getLoginUser(session);
		
		PaymentDto paymentDto = PaymentDto.toDto(paymentRequest,user.getMemNo());

		return paymentDao.AddPayment(paymentDto);

	}

	/**
	 * 진행상태(Payment:progress) 업데이트, 제품 재고(Product:count) 판매량(Product:salesRate) 업데이트
	 * 
	 * @param paymentNum
	 * @param progress
	 * @param orderId
	 * @return
	 */
	public PaymentDto updateProgress(Long paymentNum, PaymentProgress progress, String orderId) {

		PaymentDto payment = paymentDao.updateProgress(paymentNum, progress, orderId);

		productDao.buyProduct(payment.getProduct().getNum(), payment.getCount());

		return payment;
	}

	/**
	 * 결제정보 업데이트 처리
	 * @param paymentNum
	 * @param progress
	 * @return
	 */
	public PaymentDto updateProgress(Long paymentNum, PaymentProgress progress) {

		PaymentDto payment = paymentDao.updateProgress(paymentNum, progress);

		return payment;
	}

	public PaymentDto removePayment(Long paymentNum) {
		return paymentDao.remove(paymentNum);
	}

	/**
	 * 결제를 위한 paymentRequest 주입 로그인 상태 체크 productNum 유효값 검증
	 * 
	 * @param productNum
	 * @return
	 */
	public PaymentRequest paymentSetting(Long productNum) {

		PaymentRequest request = new PaymentRequest();

		/** 상품 유효값 검증 S */
		ProductRequestDto product = shopService.getProduct(productNum);

		if (product == null) {
			throw new RuntimeException("잘못된 접근 입니다.");
		}

		/** 상품 유효값 검증 E */

		
		

		
		return request;
	}

	/**
	 * 상품 구매 시 판매량(Product.salesRate) ++ 재고(Product.count) -- 처리
	 * 
	 * @param num
	 * @return
	 */
	public ProductRequestDto productBuyPs(Long num, int count) {

		return productDao.buyProduct(num, count);

	}

	
	/**
	 * 유저No 를 통해 구매 내역 가져오기
	 * 
	 * @param userNo
	 * @return
	 */
	public List<PaymentDto> gets(Long userNo, PaymentProgress progress) {

		return paymentDao.getsUserPayment(userNo, progress);

	}
	
	/**
	 * 유저No 를 통해 구매 내역 가져오기
	 * 
	 * @param userNo
	 * @return
	 */
	public List<PaymentDto> gets(Long userNo) {

		return paymentDao.getsUserPayment(userNo);

	}
	
	/**
	 * 유저No 를 통해 구매 내역 가져오기 + 페이지 네이션
	 * 
	 * @param userNo
	 * @return
	 */
	public List<PaymentDto> gets(Long userNo, int start, int offset) {

		return paymentDao.getsUserPayment(userNo, start, offset);

	}
	
	/**
	 * 유저No 를 통해 구매 내역 항목 수 가져오기
	 * 
	 * @param userNo
	 * @return
	 */
	public long getsC(Long userNo) {

		return paymentDao.getsUserPaymentC(userNo);

	}

}
