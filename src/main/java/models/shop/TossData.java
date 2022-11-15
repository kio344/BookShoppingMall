package models.shop;

public class TossData {
	private String code;
	private String status;
	private String payToken;
	private String checkoutPage;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayToken() {
		return payToken;
	}
	public void setPayToken(String payToken) {
		this.payToken = payToken;
	}
	public String getCheckoutPage() {
		return checkoutPage;
	}
	public void setCheckoutPage(String checkoutPage) {
		this.checkoutPage = checkoutPage;
	}
	@Override
	public String toString() {
		return "tossData [code=" + code + ", status=" + status + ", payToken=" + payToken + ", checkoutPage="
				+ checkoutPage + "]";
	}
	
	
}
