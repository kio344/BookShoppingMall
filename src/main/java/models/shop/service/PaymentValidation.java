package models.shop.service;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import models.shop.payment.PaymentRequest;
import models.user.UserDto;
import models.user.service.LoginService;

@Component
public class PaymentValidation implements Validator{

	@Autowired
	private LoginService loginService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return PaymentRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		PaymentRequest paymentRequest=(PaymentRequest) target;
		
		if (errors.hasErrors()) {
			
			throw new RuntimeException("값을 전부 채워주세요");
			
		}
		
		
		UserDto user = loginService.getUser(paymentRequest.getUserNum());

		if (user == null) {
			throw new RuntimeException("데이터 변조가 감지되었습니다.");
		}

		String userNo_Id = user.getMemNo() + user.getMemId();

		String tocken = paymentRequest.getUserKey();


		boolean check = BCrypt.checkpw(userNo_Id, tocken);

		if (!check) {
			throw new RuntimeException("데이터 변조가 감지되었습니다.");
		}
		
	}

}
