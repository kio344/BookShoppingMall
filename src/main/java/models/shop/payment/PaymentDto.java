package models.shop.payment;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import models.common.BaseDto;
import models.entity.Payment;
import models.entity.Product;
import models.entity.User;
import models.shop.product.ProductDto;
import models.user.UserDto;

public class PaymentDto extends BaseDto {

	private Long num;

	private ProductDto product;

	private UserDto user;

	private Integer count;

	private String recipient_name;

	private String recipient_mobile;

	private String address;

	private PaymentProgress progress;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}

	public String getRecipient_mobile() {
		return recipient_mobile;
	}

	public void setRecipient_mobile(String recipient_mobile) {
		this.recipient_mobile = recipient_mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PaymentProgress getProgress() {
		return progress;
	}

	public void setProgress(PaymentProgress progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "PaymentDto [num=" + num + ", product=" + product + ", user=" + user + ", count=" + count
				+ ", recipient_name=" + recipient_name + ", recipient_mobile=" + recipient_mobile + ", address="
				+ address + ", progress=" + progress + "]";
	}

	public static Payment toEntity(PaymentDto dto) {
		Payment entity = new Payment();
		entity.setAddress(dto.getAddress());
		entity.setCount(dto.getCount());
		entity.setProduct(ProductDto.toEntity(dto.getProduct()));
		entity.setProgress(dto.getProgress());
		entity.setRecipient_mobile(dto.getRecipient_mobile());
		entity.setRecipient_name(dto.getRecipient_name());

		return entity;
	}

	public static PaymentDto toDto(Payment entity) {
		PaymentDto dto = new PaymentDto();
		dto.setNum(entity.getNum());
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());
		dto.setAddress(entity.getAddress());
		dto.setCount(entity.getCount());
		dto.setProduct(ProductDto.toDto(entity.getProduct()));
		dto.setProgress(entity.getProgress());
		dto.setRecipient_mobile(entity.getRecipient_mobile());
		dto.setRecipient_name(entity.getRecipient_name());
		dto.setUser(UserDto.toDto(entity.getUser()));

		return dto;
	}

	public static PaymentDto toDto(PaymentRequest request) {
		PaymentDto dto = new PaymentDto();
		dto.setAddress(request.getAddress());
		dto.setCount(request.getCount());

		ProductDto productDto = new ProductDto();
		productDto.setNum(request.getProductNum());
		dto.setProduct(productDto);

		UserDto userDto = new UserDto();
		userDto.setMemNo(request.getUserNum());
		dto.setUser(userDto);

		dto.setProgress(PaymentProgress.PAYMENT_BEFORE); // 기본값 PAYMENT_BEFORE
		dto.setRecipient_mobile(request.getRecipient_mobile());
		dto.setRecipient_name(request.getRecipient_name());

		return dto;
	}

}
