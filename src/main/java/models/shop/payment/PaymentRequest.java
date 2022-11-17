package models.shop.payment;

import org.hibernate.validator.constraints.NotBlank;

import models.entity.Product;
import models.entity.User;
import models.user.UserDto;

public class PaymentRequest {

	private Long productNum;

	private Long userNum;

	private Integer count;

	@NotBlank(message = "값이 비어있습니다.")
	private String recipient_name;

	@NotBlank(message = "휴대전화번호를 입력하세요.")
	private String recipient_mobile;

	@NotBlank(message = "우편번호를 입력하세요.")
	private String zipCode;

	@NotBlank(message = "주소를 입력하세요.")
	private String roadAddress;

	@NotBlank(message = "상세 주소를 입력하세요.")
	private String detailAddress;

	@NotBlank(message = "참고항목을 입력하세요.")
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

	public void setAddress() {
		if (roadAddress == null || zipCode == null || roadAddress == null) {
			throw new RuntimeException("우편번호, 주소, 상세주소, 참고항목을 모두 채워주세요");
		}

		this.address = "(" + zipCode + ")" + roadAddress + " " + detailAddress;

		if (reqAddress != null) {
			this.address += "(" + reqAddress + ")";
		}

	}

	@Override
	public String toString() {
		return "PaymentRequest [productNum=" + productNum + ", userNum=" + userNum + ", count=" + count
				+ ", recipient_name=" + recipient_name + ", recipient_mobile=" + recipient_mobile + ", zipCode="
				+ zipCode + ", roadAddress=" + roadAddress + ", detailAddress=" + detailAddress + ", reqAddress="
				+ reqAddress + ", address=" + address + ", toString()=" + super.toString() + "]";
	}

}
