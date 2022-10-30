package models.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product extends BaseEntity {

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
	private Long imageNum;

	@Column
	private Long salesRate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<ProductReview> review = new ArrayList<>();

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	

	public Long getImageNum() {
		return imageNum;
	}

	public void setImageNum(Long imageNum) {
		this.imageNum = imageNum;
	}

	public Long getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Long salesRate) {
		this.salesRate = salesRate;
	}

	public List<ProductReview> getReview() {
		return review;
	}

	public void setReview(List<ProductReview> review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Product [num=" + num + ", seller=" + seller + ", serialnum=" + serialnum + ", bookName=" + bookName
				+ ", writer=" + writer + ", price=" + price + ", category=" + category + ", publisher=" + publisher
				+ ", count=" + count + ", imageNum=" + imageNum + ", salesRate=" + salesRate + ", review=" + review
				+ ", toString()=" + super.toString() + "]";
	}

}