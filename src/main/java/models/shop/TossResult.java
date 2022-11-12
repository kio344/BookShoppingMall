package models.shop;

public class TossResult {
	private String status;
	private String payToken;
	private String orderNo;
	private String payMethod;
	private String amount;
	private String discountedAmount;
	private String paidAmount;
	private String paidTs;
	private String transactionId;
	private String cardCompanyCode;
	private String cardAuthorizationNo;
	private String spreadOut;
	private String noInterest;
	private String cardMethodType;
	private String cardNumber;
	private String salesCheckLinkUrl;

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDiscountedAmount() {
		return discountedAmount;
	}

	public void setDiscountedAmount(String discountedAmount) {
		this.discountedAmount = discountedAmount;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaidTs() {
		return paidTs;
	}

	public void setPaidTs(String paidTs) {
		this.paidTs = paidTs;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCardCompanyCode() {
		return cardCompanyCode;
	}

	public void setCardCompanyCode(String cardCompanyCode) {
		this.cardCompanyCode = cardCompanyCode;
	}

	public String getCardAuthorizationNo() {
		return cardAuthorizationNo;
	}

	public void setCardAuthorizationNo(String cardAuthorizationNo) {
		this.cardAuthorizationNo = cardAuthorizationNo;
	}

	public String getSpreadOut() {
		return spreadOut;
	}

	public void setSpreadOut(String spreadOut) {
		this.spreadOut = spreadOut;
	}

	public String getNoInterest() {
		return noInterest;
	}

	public void setNoInterest(String noInterest) {
		this.noInterest = noInterest;
	}

	public String getCardMethodType() {
		return cardMethodType;
	}

	public void setCardMethodType(String cardMethodType) {
		this.cardMethodType = cardMethodType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSalesCheckLinkUrl() {
		return salesCheckLinkUrl;
	}

	public void setSalesCheckLinkUrl(String salesCheckLinkUrl) {
		this.salesCheckLinkUrl = salesCheckLinkUrl;
	}

	@Override
	public String toString() {
		return "ToassResult [status=" + status + ", payToken=" + payToken + ", orderNo=" + orderNo + ", payMethod="
				+ payMethod + ", amount=" + amount + ", discountedAmount=" + discountedAmount + ", paidAmount="
				+ paidAmount + ", paidTs=" + paidTs + ", transactionId=" + transactionId + ", cardCompanyCode="
				+ cardCompanyCode + ", cardAuthorizationNo=" + cardAuthorizationNo + ", spreadOut=" + spreadOut
				+ ", noInterest=" + noInterest + ", cardMethodType=" + cardMethodType + ", cardNumber=" + cardNumber
				+ ", salesCheckLinkUrl=" + salesCheckLinkUrl + "]";
	}

}
