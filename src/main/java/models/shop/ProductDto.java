package models.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import models.common.BaseDto;
import models.entity.ProductReview;
import models.entity.User;

public class ProductDto extends BaseDto{
	@Id
	@GeneratedValue
	private Long num;
	
	@JoinColumn(name = "seller")
	@ManyToOne(fetch = FetchType.LAZY)
	private User seller;
	
	@Column
	private String serialnum;
	
	@Column(unique = true)
	private String bookName;
	
	@Column
	private String writer;
	
	@Column
	private Long price;
	
	@Column
	private String category;
	
	@Column
	private String publisher;
	
	@Column
	private int count;

	@Column
	private Long salesRate;
	
	@JoinColumn(name = "review")
	@OneToMany(fetch = FetchType.LAZY)
	private List<ProductReview> review=new ArrayList<>();
	
	
}
