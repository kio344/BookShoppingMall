package models.seller.product;

import models.common.BaseDto;
import models.entity.ProductRequest;
import models.shop.product.ProductDto;
import models.user.UserDto;

public class ProductRequestDto extends BaseDto {

	private Long num;
	private UserDto seller;
	private String serialnum;
	private String bookName;
	private String writer;
	private Long price;
	private String category;
	private String publisher;
	private int count;
	private Long images;
	private Progress progress = Progress.Examine;
	private Long SalesRate;
	private Double score;
	
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public UserDto getSeller() {
		return seller;
	}

	public void setSeller(UserDto seller) {
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
	
	public Long getImages() {
		return images;
	}

	public void setImages(Long images) {
		this.images = images;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	
	public Long getSalesRate() {
		return SalesRate;
	}
	
	public void setSalesRate(Long salesRate) {
		SalesRate = salesRate;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	public double getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return "ProductRequestDto [num=" + num + ", seller=" + seller + ", serialnum=" + serialnum + ", bookName="
				+ bookName + ", writer=" + writer + ", price=" + price + ", category=" + category + ", publisher="
				+ publisher + ", count=" + count + ", images=" + images + ", progress=" + progress + ", SalesRate="
				+ SalesRate + ", score=" + score + "]";
	}

	public static ProductRequestDto toDto(ProductRequest entity) {
		ProductRequestDto dto = new ProductRequestDto();

		if (entity == null) return null;

		dto.setNum(entity.getNum());
		dto.setSeller(UserDto.toDto(entity.getSeller()));
		dto.setSerialnum(entity.getSerialnum());
		dto.setBookName(entity.getBookName());
		dto.setWriter(entity.getWriter());
		dto.setPrice(entity.getPrice());
		dto.setCategory(entity.getCategory());
		dto.setPublisher(entity.getPublisher());
		dto.setCount(entity.getCount());
		dto.setProgress(entity.getProgress());
		dto.setSalesRate(entity.getSalesRate());
		
		if (entity.getScore()==null) {
			dto.setScore(5);
		}else {
			dto.setScore(entity.getScore());
		}
		
		
		
		return dto;
	}

	public static ProductRequest toEntity(ProductRequestDto dto) {
		ProductRequest entity = new ProductRequest();

		if (dto == null) return null;

		entity.setNum(dto.getNum());
		entity.setSeller(UserDto.toEntity(dto.getSeller()));
		entity.setSerialnum(dto.getSerialnum());
		entity.setBookName(dto.getBookName());
		entity.setWriter(dto.getWriter());
		entity.setPrice(dto.getPrice());
		entity.setCategory(dto.getCategory());
		entity.setPublisher(dto.getPublisher());
		entity.setCount(dto.getCount());
		entity.setProgress(dto.getProgress());
		entity.setSalesRate(dto.getSalesRate());
		entity.setScore(dto.getScore());
		
		return entity;
	}

	public static ProductDto toRequest(ProductRequestDto dto) {
		if (dto == null) return null;

		ProductDto req = new ProductDto();

		req.setBookName(dto.getBookName());
		req.setCategory(dto.getCategory());
		req.setCount(dto.getCount());
		req.setModDt(dto.getModDt());
		req.setRegDt(dto.getRegDt());
		req.setNum(dto.getNum());
		req.setPrice(dto.getPrice());
		req.setPublisher(dto.getPublisher());
		req.setSeller(dto.getSeller());
		req.setSerialnum(dto.getSerialnum());
		req.setWriter(dto.getWriter());
		req.setProgress(dto.getProgress());
		

		
		System.out.println(req);

		return req;
	}

}
