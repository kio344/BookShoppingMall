package models.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentDao paymentDao;
	
	public PaymentDto paymentProcess(PaymentRequest request) {
		
		PaymentDto paymentDto=PaymentDto.toDto(request);
		
		return paymentDao.AddPayment(paymentDto);
		
	}
	
	
	public PaymentDto updateProgress(Long paymentNum,PaymentProgress progress) {
		return paymentDao.updateProgress(paymentNum, progress);
	}
	
	public PaymentDto removePayment(Long paymentNum) {
		return paymentDao.remove(paymentNum);
	}
}
