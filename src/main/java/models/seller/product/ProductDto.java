package models.seller.product;

import models.common.BaseDto;
import models.entity.ProductRequest;
import models.entity.User;

public class ProductDto extends BaseDto{

	private Long num;
	private User seller;
	private String serialnum;
	private String bookName;
	private String writer;
	private Long price;
	private String category;
	private String publisher;
	private int count;
	private Progress progress = Progress.Examine;
	
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

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "ProductDto [num=" + num + ", seller=" + seller + ", serialnum=" + serialnum + ", bookName=" + bookName
				+ ", writer=" + writer + ", price=" + price + ", category=" + category + ", publisher=" + publisher
				+ ", count=" + count + ", progress=" + progress + ", getRegDt()=" + getRegDt() + ", getModDt()="
				+ getModDt() + "]";
	}

	public static ProductDto toDto(ProductRequest entity) {
		ProductDto dto = new ProductDto();
		
		if(entity == null) return null;
		
		dto.setNum(entity.getNum());
		dto.setSeller(entity.getSeller());
		dto.setSerialnum(entity.getSerialnum());
		dto.setBookName(entity.getBookName());
		dto.setWriter(entity.getWriter());
		dto.setPrice(entity.getPrice());
		dto.setCategory(entity.getCategory());
		dto.setPublisher(entity.getPublisher());
		dto.setCount(entity.getCount());
		dto.setProgress(entity.getProgress());
		
		return dto;
	}
	
	public static ProductRequest toEntity(ProductDto dto) {
		ProductRequest entity = new ProductRequest();
		
		if(dto == null) return null;
		
		entity.setNum(dto.getNum());
		entity.setSeller(dto.getSeller());
		entity.setSerialnum(dto.getSerialnum());
		entity.setBookName(dto.getBookName());
		entity.setWriter(dto.getWriter());
		entity.setPrice(dto.getPrice());
		entity.setCategory(dto.getCategory());
		entity.setPublisher(dto.getPublisher());
		entity.setCount(dto.getCount());
		entity.setProgress(dto.getProgress());
		
		return entity;
	}
	
}







