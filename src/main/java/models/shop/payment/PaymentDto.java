package models.shop.payment;

import models.common.BaseDto;
import models.entity.Payment;
import models.seller.product.ProductRequestDto;
import models.user.UserDto;

public class PaymentDto extends BaseDto {

	private Long num;

	private ProductRequestDto product;

	private UserDto user;

	private Integer count;

	private String recipient_name;

	private String recipient_mobile;

	private String address;

	private PaymentProgress progress;

	private String tossOrderId;


	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public ProductRequestDto getProduct() {
		return product;
	}

	public void setProduct(ProductRequestDto product) {
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

	public String getTossOrderId() {
		return tossOrderId;
	}

	public void setTossOrderId(String tossOrderId) {
		this.tossOrderId = tossOrderId;
	}




	@Override
	public String toString() {
		return "PaymentDto [num=" + num + ", product=" + product + ", user=" + user + ", count=" + count
				+ ", recipient_name=" + recipient_name + ", recipient_mobile=" + recipient_mobile + ", address="
				+ address + ", progress=" + progress + ", tossOrderId=" + tossOrderId + "]";
	}

	public static Payment toEntity(PaymentDto dto) {
		Payment entity = new Payment();
		entity.setAddress(dto.getAddress());
		entity.setCount(dto.getCount());
		entity.setProduct(ProductRequestDto.toEntity(dto.getProduct()));
		entity.setProgress(dto.getProgress());
		entity.setRecipient_mobile(dto.getRecipient_mobile());
		entity.setRecipient_name(dto.getRecipient_name());
		entity.setTossOrderId(dto.getTossOrderId());

		
		return entity;
	}

	public static PaymentDto toDto(Payment entity) {
		PaymentDto dto = new PaymentDto();
		dto.setNum(entity.getNum());
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());
		dto.setAddress(entity.getAddress());
		dto.setCount(entity.getCount());
		dto.setProduct(ProductRequestDto.toDto(entity.getProduct()));
		dto.setProgress(entity.getProgress());
		dto.setRecipient_mobile(entity.getRecipient_mobile());
		dto.setRecipient_name(entity.getRecipient_name());
		dto.setUser(UserDto.toDto(entity.getUser()));
		dto.setTossOrderId(entity.getTossOrderId());

		
		return dto;
	}

	public static PaymentDto toDto(PaymentRequest request) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setAddress(request.getAddress());
		paymentDto.setCount(request.getCount());

		ProductRequestDto productDto = new ProductRequestDto();
		productDto.setNum(request.getProductNum());
		paymentDto.setProduct(productDto);

		UserDto userDto = new UserDto();
		userDto.setMemNo(request.getUserNum());
		paymentDto.setUser(userDto);

		paymentDto.setProgress(PaymentProgress.PAYMENT_BEFORE); // 기본값 PAYMENT_BEFORE
		paymentDto.setRecipient_mobile(request.getRecipient_mobile());
		paymentDto.setRecipient_name(request.getRecipient_name());

		return paymentDto;
	}

}
