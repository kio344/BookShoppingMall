package models.shop.productReview;

/**
 * 리뷰등록 요청
 * 
 * @author 5563a
 *
 */
public class ProductReviewRequest {
	private Long product;
	private String content;
	private double score;
	private Long payment;
	
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
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
	public Long getPayment() {
		return payment;
	}
	public void setPaymentNum(Long payment) {
		this.payment = payment;
	}
	
	
	public void setPayment(Long payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "ProductReviewRequest [product=" + product + ", content=" + content + ", score=" + score + ", payment="
				+ payment + "]";
	}
	
}
