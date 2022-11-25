package models.shop.payment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Payment;
import models.entity.Product;
import models.entity.User;
import models.seller.product.Progress;
import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.user.UserDao;

@Component
public class PaymentDao {
	@Autowired
	private EntityManager em;
	

	public PaymentDto AddPayment(PaymentDto dto) {
		Payment entity = PaymentDto.toEntity(dto);
		entity.setProduct(em.find(Product.class, dto.getProduct().getNum()));

		entity.setUser(em.find(User.class, dto.getUser().getMemNo()));

		em.persist(entity);
		em.flush();

		return get(entity.getNum());
	}

	/**
	 * 진행상태(Payment.progress) 업데이트, 제품 재고 판매량 업데이트
	 * @param num
	 * @param progress
	 * @param orderId
	 * @return
	 */
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
	
	public List<PaymentDto> getsUserPayment(Long num,PaymentProgress progress) {

		User user=em.find(User.class, num);
		
		String sql="SELECT p FROM Payment p WHERE p.user=:user AND p.progress=:progress";
		
		TypedQuery<Payment> query=em.createQuery(sql, Payment.class);
		query.setParameter("user", user);
		
		
		if (progress != null) {
			query.setParameter("progress", progress);
		}else {
			query.setParameter("progress", "");
		}
		
		
		List<PaymentDto> result=query.getResultList().stream().map(t -> PaymentDto.toDto(t)).toList();
		
		return result;
		
	}
}
