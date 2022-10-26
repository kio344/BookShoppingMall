package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.seller.product.Progress;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int num;
	@Column(unique = true)
	private String bookName;
	@Column
	private String publisher;
	@Column
	private String seller;
	@Column
	private String category;
	@Column
	private int price;
	@Column
	private int count;
	@Column
	@Enumerated(EnumType.STRING)
	private Progress progress = Progress.Examine;

	public int getNum() { 
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Progress getProgress() {
		return progress;
	}
	
	public void setProgress(Progress progress) {
		
		this.progress = progress;
	}
	
}
