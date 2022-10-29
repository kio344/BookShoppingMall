package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductReview extends BaseEntity {
	
	@Id @GeneratedValue
	private Long num;
	
	@JoinColumn(name="product")
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;	//상품
	
	@JoinColumn(name = "user")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;		//작성한 유저
	
	@Column(length = 1000)
	private String content;	//리뷰내용
	
	@Column
	private double score;	//별점

}
