package models.shop.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import models.shop.payment.PaymentRequest;

/**
 * 결제 데이터 검증
 * 
 * @author 5563a
 *
 */
@Component
public class PaymentValidation implements Validator{


	
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
