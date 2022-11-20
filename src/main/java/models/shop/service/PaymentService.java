package models.shop.service;

import javax.servlet.http.HttpSession;
import static common.Util.JmsUtil.*;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;
import models.shop.product.ProductDto;
import models.user.UserDto;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private ShopService shopService;

	@Autowired
	private HttpSession session;

	public PaymentDto paymentProcess(PaymentRequest paymentRequest) {

		
		PaymentDto paymentDto = PaymentDto.toDto(paymentRequest);

		return paymentDao.AddPayment(paymentDto);

	}

	public PaymentDto updateProgress(Long paymentNum, PaymentProgress progress,String orderId) {
		
		return paymentDao.updateProgress(paymentNum, progress,orderId);
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

		ProductDto product = shopService.getProduct(productNum);

		if (product == null) {
			throw new RuntimeException("잘못된 접근 입니다.");
		}

		UserDto user = getLoginUser(session);

		if (user == null) {
			throw new RuntimeException("로그인 후 이용 가능한 서비스입니다.");
		}

		request.setProductNum(product.getNum());

		request.setUserNum(user.getMemNo());

		
		String userKey=user.getMemNo()+user.getMemId();
		System.out.println(userKey);
		request.setUserKey(BCrypt.hashpw(userKey, BCrypt.gensalt(10)));
		
		
		
		return request;
	}
}
