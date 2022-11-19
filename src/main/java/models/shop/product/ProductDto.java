package models.shop.product;

import java.util.ArrayList;
import java.util.List;

import models.common.BaseDto;
import models.entity.Product;
import models.seller.product.Progress;
import models.shop.productReview.ProductReviewDto;
import models.user.UserDto;

public class ProductDto extends BaseDto {

	private Long num;

	private UserDto seller;

	private String serialnum;

	private String bookName;

	private String writer;

	private Long price;

	private String category;

	private String publisher;

	private int count;

	private Long imageNum;
	
	private Long salesRate;

	private List<ProductReviewDto> review = new ArrayList<>();
	
	private Progress progress = Progress.Examine; // testing

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

	public List<ProductReviewDto> getReview() {
		return review;
	}

	public void setReview(List<ProductReviewDto> review) {
		this.review = review;
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
				+ ", count=" + count + ", imageNum=" + imageNum + ", salesRate=" + salesRate + ", review=" + review
				+ "]";
	}

	public static Product toEntity(ProductDto dto) {
		if (dto == null) {
			return null;
		}

		Product entity = new Product();
		entity.setBookName(dto.getBookName());
		entity.setCategory(dto.getCategory());
		entity.setCount(dto.getCount());

		entity.setPrice(dto.getPrice());
		entity.setPublisher(dto.getPublisher());
		entity.setSeller(UserDto.toEntity(dto.getSeller()));
		entity.setSerialnum(dto.getSerialnum());
		entity.setWriter(dto.getWriter());
		entity.setImageNum(dto.getImageNum());
		entity.setSalesRate(dto.getSalesRate());
		
		return entity;

	}

	public static ProductDto toDto(Product entity) {
		if (entity == null) {
			return null;
		}

		ProductDto dto = new ProductDto();
		dto.setBookName(entity.getBookName());
		dto.setCategory(entity.getCategory());
		dto.setCount(entity.getCount());
		dto.setModDt(entity.getModDt());
		dto.setRegDt(entity.getRegDt());
		dto.setNum(entity.getNum());
		dto.setPrice(entity.getPrice());
		dto.setPublisher(entity.getPublisher());
		dto.setSeller(UserDto.toDto(entity.getSeller()));
		dto.setSerialnum(entity.getSerialnum());
		dto.setWriter(entity.getWriter());
		dto.setImageNum(entity.getImageNum());
		dto.setSalesRate(entity.getSalesRate());
		
		return dto;

	}

}
