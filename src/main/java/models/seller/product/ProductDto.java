package models.seller.product;

import models.entity.Product;

public class ProductDto {

	private int num;
	private String bookName;
	private String publisher;
	private String seller;
	private String category;
	private int price;
	private int count;

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

	@Override
	public String toString() {
		return "ProductDto [num=" + num + ", bookName=" + bookName + ", publisher=" + publisher + ", seller=" + seller
				+ ", category=" + category + ", price=" + price + ", count=" + count + "]";
	}
		
	public static ProductDto toDto(Product entity) {
		ProductDto dto = new ProductDto();
		
		if(entity == null) return null;
		
		dto.setBookName(entity.getBookName());
		dto.setCategory(entity.getCategory());
		dto.setCount(entity.getCount());
		dto.setNum(entity.getNum());
		dto.setPrice(entity.getPrice());
		dto.setPublisher(entity.getPublisher());
		dto.setSeller(entity.getSeller());
		
		return dto;
	}
	
	public static Product toEntity(ProductDto dto) {
		Product entity = new Product();
		if(dto == null) return null;
		
		entity.setBookName(dto.getBookName());
		entity.setCategory(dto.getCategory());
		entity.setCount(dto.getCount());
		entity.setNum(dto.getNum());
		entity.setPrice(dto.getPrice());
		entity.setPublisher(dto.getPublisher());
		entity.setSeller(dto.getSeller());
		
		return entity;

		
	}
	
}







