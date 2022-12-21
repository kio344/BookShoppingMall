package models.shop.service;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import models.shop.payment.PaymentRequest;
import models.user.UserDto;
import models.user.service.LoginService;

/**
 * 결제 데이터 검증
 * 
 * @author 5563a
 *
 */
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
		
		if (errors.hasErrors()) {
			
			throw new RuntimeException("값을 전부 채워주세요");
			
		}
		
		
		
	}

}
