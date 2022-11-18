package models.shop.productReview;

import models.common.BaseDto;
import models.shop.product.ProductDto;
import models.user.UserDto;

public class ProductReviewDto extends BaseDto{

	private Long num;
	
	private ProductDto product;	//상품
	
	private UserDto user;		//작성한 유저
	
	private String content;		//리뷰내용
	
	private double score;		//별점

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

	@Override
	public String toString() {
		return "ProductReviewDto [num=" + num + ", product=" + product + ", user=" + user + ", content=" + content
				+ ", score=" + score + ", toString()=" + super.toString() + "]";
	}
	

	
}
