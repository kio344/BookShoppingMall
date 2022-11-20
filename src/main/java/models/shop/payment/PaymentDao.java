package models.shop.payment;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Payment;
import models.entity.Product;
import models.entity.User;
import models.shop.product.ProductDao;
import models.shop.product.ProductDto;

@Component
public class PaymentDao {
	@Autowired
	private EntityManager em;

	@Autowired
	private ProductDao productDao;

	public PaymentDto AddPayment(PaymentDto dto) {
		Payment entity = PaymentDto.toEntity(dto);
		entity.setProduct(em.find(Product.class, dto.getProduct().getNum()));

		entity.setUser(em.find(User.class, dto.getUser().getMemNo()));

		em.persist(entity);
		em.flush();

		return get(entity.getNum());
	}

	public PaymentDto updateProgress(Long num, PaymentProgress progress,String orderId) {

		Payment entity = em.find(Payment.class, num);

		entity.setProgress(progress);
		entity.setTossOrderId(orderId);

		em.persist(entity);

		em.flush();

		return PaymentDto.toDto(entity);

	}

	public PaymentDto remove(Long num) {
		Payment entity = em.find(Payment.class, num);
		
		em.remove(entity);
		
		return PaymentDto.toDto(entity);
		
	}

	public PaymentDto get(Long num) {

		Payment entity = em.find(Payment.class, num);

		return PaymentDto.toDto(entity);

	}
}
