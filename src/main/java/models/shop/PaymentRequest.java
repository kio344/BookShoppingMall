package models.shop;

import models.entity.Product;
import models.entity.User;
import models.user.UserDto;

public class PaymentRequest {
	
	private Long productNum;
	
	private Long userNum;
	
	private Integer count;
	
	private String recipient_name;
	
	private String recipient_mobile;
	
	private String zipCode;
	private String roadAddress;
	private String detailAddress;
	private String reqAddress;
	
	private String address;

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getReqAddress() {
		return reqAddress;
	}

	public void setReqAddress(String reqAddress) {
		this.reqAddress = reqAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PaymentRequest [productNum=" + productNum + ", userNum=" + userNum + ", count=" + count
				+ ", recipient_name=" + recipient_name + ", recipient_mobile=" + recipient_mobile + ", zipCode="
				+ zipCode + ", roadAddress=" + roadAddress + ", detailAddress=" + detailAddress + ", reqAddress="
				+ reqAddress + ", address=" + address + ", toString()=" + super.toString() + "]";
	}
	
	
	

	
	

}
