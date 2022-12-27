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
public class PaymentValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return PaymentRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		PaymentRequest request = (PaymentRequest) target;


		String addressType = request.getAddressType();

		switch (addressType) {
		case "defaultAddress":

			break;

		case "newAddress":

			if (request.getZipCode().isBlank()) {
				errors.rejectValue("zipCode", addressType, "우편번호를 확인해주세요");

			}
			if (request.getRoadAddress().isBlank()) {
				errors.rejectValue("roadAddress", addressType, "주소를 확인해주세요");

			}
			
			if (request.getDetailAddress().isBlank()) {
				errors.rejectValue("detailAddress", addressType, "상세주소를 확인해주세요");

			}

			request.setAddress();

			break;

		default:
			errors.reject("otherErr", null, "데이터변조가 감지됨");
		}

	}

}
