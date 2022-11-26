package models.shop.productReview;

import models.common.BaseDto;
import models.entity.Payment;
import models.entity.ProductReview;
import models.shop.payment.PaymentDto;
import models.shop.product.ProductDto;
import models.user.UserDto;

public class ProductReviewDto extends BaseDto{

	private Long num;
	
	
	
	private String content;		//리뷰내용
	
	private double score;		//별점
	
	private PaymentDto payment;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	
	
	@Override
	public String toString() {
		return "ProductReviewDto [num=" + num + ", content=" + content + ", score=" + score + ", payment=" + payment
				+ "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public static ProductReview toEntity(ProductReviewDto dto) {
		ProductReview entity=new ProductReview();
		entity.setContent(dto.getContent());
		entity.setScore(dto.getScore());
		
		
		return entity;
	}
	
	public static ProductReviewDto toDto(ProductReview entity) {
		if (entity==null) {
			return null;
		}
		ProductReviewDto dto=new ProductReviewDto();
		dto.setPayment(PaymentDto.toDto(entity.getPayment()));
		dto.setContent(entity.getContent());
		dto.setScore(entity.getScore());
		dto.setNum(entity.getNum());
		dto.setModDt(entity.getModDt());
		dto.setRegDt(entity.getRegDt());
		
		return dto;
	}
	

	
}
