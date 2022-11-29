package models.seller.product;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Payment;
import models.entity.ProductRequest;
import models.entity.User;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;

@Component(value = "productRequestDao")
public class ProductRequestDao {
		
	@Autowired
	private EntityManager em;
	
	/**
	 * @author kimminho
	 * @param num
	 * @return list 
	 * 
	 * ProductRequest 디비에 있는 정보들 가져오기
	 */
	public ProductRequestDto get(Long num) {
		
		ProductRequest entity = em.find(ProductRequest.class, num);
		
		return ProductRequestDto.toDto(entity);
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * @return list
	 * 
	 * ProductRequest 디비에 있는 정보들 가져오기
	 */
	public ProductRequestDto get(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		return ProductRequestDto.toDto(entity);
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * 
	 * 관리자가 승인 하면 상품 승인 처리 후 디비 수정
	 */
	public void updateAdmin(ProductRequestDto dto){
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		entity.setPrice(dto.getPrice());
		entity.setCount(dto.getCount());
		
		entity.setProgress(Progress.Agree);
		
		em.persist(entity);
		
		em.flush();
		
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * 판매자가 가격, 수량을 수정하면 디비 수정
	 */
	public void update(ProductRequestDto dto){
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		entity.setPrice(dto.getPrice());
		entity.setCount(dto.getCount());
		
		em.persist(entity);
		
		em.flush();
		
	}
		
	/**
	 * @author kimminho
	 * @param dto
	 * @param num
	 * payment DB에 상품상태 배송중으로 변경 후, count -1 하기.
	 */
	public void updatePayment(PaymentDto dto, Long num) {
		Payment entity = em.find(Payment.class, num);
		
		entity.setProgress(PaymentProgress.SHIPPING);
		entity.setCount(dto.getCount() - 1);
		
		em.persist(entity);
		em.flush();
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * @param num
	 * payment DB에 상품 취소 처리.
	 */
	public void cencel(PaymentDto dto, Long num) {
		Payment entity = em.find(Payment.class, num);
		
		entity.setProgress(PaymentProgress.CANCEL);
		
		em.persist(entity);
		em.flush();
		
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * @return
	 * 
	 * 판매자가 상품 신청하면 미승인 상태로 디비에 저장
	 */
	public ProductRequestDto save(ProductRequestDto dto){
		
		ProductRequest entity = ProductRequestDto.toEntity(dto);
		User user = em.find(User.class, dto.getSeller().getMemNo());
		
		entity.setNum(dto.getNum());
		entity.setSeller(user);
		entity.setProgress(Progress.Examine);
		
		em.persist(entity);
		
		em.flush();
		
		return ProductRequestDto.toDto(entity);
	}
	
	/**
	 * @author kimminho
	 * @param dto
	 * 
	 * 관리자가 상품 수락하면 바뀐 progress가 디비에 저장 
	 */
	public void argee(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		entity.setProgress(dto.getProgress());
		
		em.persist(entity);
		em.flush();
		
	}
	
	/**
	 * @author kimminho 
	 * @param dto
	 * 
	 * 상품 삭제
	 */
	public void remove(ProductRequestDto dto) {
		
		ProductRequest entity = em.find(ProductRequest.class, dto.getNum());
		
		em.remove(entity);
		
		em.flush();
		
	}
	
}
