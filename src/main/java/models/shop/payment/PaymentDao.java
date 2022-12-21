package models.shop.payment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Payment;
import models.entity.ProductRequest;
import models.entity.User;
import models.seller.product.ProductRequestDto;
import models.seller.product.Progress;

@Component
public class PaymentDao {
	@Autowired
	private EntityManager em;
	

	/**
	 * 결제 정보 추가
	 * 
	 * @param dto
	 * @return
	 */
	public PaymentDto AddPayment(PaymentDto dto) {
		
		ProductRequest product=em.find(ProductRequest.class, dto.getProduct().getNum());
		
		dto.setProduct(ProductRequestDto.toDto(product));
		
		Payment entity = PaymentDto.toEntity(dto);
		entity.setProduct(em.find(ProductRequest.class, dto.getProduct().getNum()));

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
	
	public PaymentDto updateProgress(Long num, PaymentProgress progress) {

		Payment entity = em.find(Payment.class, num);

		entity.setProgress(progress);
		

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
	
	public List<PaymentDto> getsUserPayment(Long num){

		User user=em.find(User.class, num);
		
		String sql="SELECT p FROM Payment p WHERE p.user=:user order by p.num desc";
		
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		query.setParameter("user", user);
		
		
		List<PaymentDto> result=query.getResultList().stream().map(t -> PaymentDto.toDto(t)).toList();
		
		return result;
	}
	
	public List<PaymentDto> getsUserPayment(Long num,int start, int offset){

		User user=em.find(User.class, num);
		
		String sql="SELECT p FROM Payment p WHERE p.user=:user order by p.num desc";
		
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		query.setParameter("user", user);
		
		query.setFirstResult(start);
		query.setMaxResults(offset);
		
		List<PaymentDto> result=query.getResultList().stream().map(t -> PaymentDto.toDto(t)).toList();
		
		return result;
	}
	
	public Long getsUserPaymentC(Long num){

		User user=em.find(User.class, num);
		
		String sql="SELECT COUNT(*) FROM Payment p WHERE p.user=:user";
		
		TypedQuery<Long> query = em.createQuery(sql,Long.class);
		query.setParameter("user", user);

		Long result=query.getSingleResult();
		
		return result;
	}
	
	
	public List<PaymentDto> getsUserPayment(Long num, PaymentProgress progress) {

		User user=em.find(User.class, num);
		
		String sql="SELECT p FROM Payment p WHERE p.user=:user AND p.progress=:progress order by p.num desc";
		
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		query.setParameter("user", user);
		
		
		if (progress != null) {
			query.setParameter("progress", progress);
		}else {
			query.setParameter("progress", progress);
		}
		
		
		List<PaymentDto> result=query.getResultList().stream().map(t -> PaymentDto.toDto(t)).toList();
		
		return result;
		
	}
	
	/**
	 * @author kimminho
	 * @param req
	 * @return
	 * 상품 Dto를 entity로 변환시켜서 결제된 상품번호 가져오기.
	 */
	public List<PaymentDto> getPaymentNum(ProductRequestDto req) {
		ProductRequest entity = ProductRequestDto.toEntity(req);
		
		String sql = "SELECT p FROM Payment p WHERE p.product=:product_num";
		
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		
		query.setParameter("product_num", entity);

		List<PaymentDto> list = query.getResultStream().map(PaymentDto::toDto).toList();
		
		return list;
	}
	
	/**
	 * @author kimminho
	 * @return
	 * 결제 완료된 상품 가져오기
	 */
	public List<PaymentDto> getCompleted() {
		
		String sql = "SELECT p FROM Payment p WHERE p.progress=:progress";
		
		TypedQuery<Payment> query = em.createQuery(sql, Payment.class);
		
		query.setParameter("progress", PaymentProgress.PAYMENT_COMPLET);
		
		List<PaymentDto> list = query.getResultStream().map(PaymentDto::toDto).toList();
		
		return list;
	}
}
