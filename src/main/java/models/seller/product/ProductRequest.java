package models.seller.product;

import org.hibernate.validator.constraints.NotBlank;

public class ProductRequest{
	
	private Long num; // 이거 기준으로 폴더 나누기.
	private String seller;
	@NotBlank(message = "시리얼 번호를 입력해 주세요.")
	private String serialnum;
	@NotBlank(message = "책이름을 입력해 주세요.")
	private String bookName;
	@NotBlank(message = "글쓴이를 입력해 주세요.")
	private String writer;
	@NotBlank(message = "가격을 입력해 주세요.")
	private Long price;
	@NotBlank(message = "카테고리를 입력해 주세요.")
	private String category;
	@NotBlank(message = "출판사를 입력해 주세요.")
	private String publisher;
	@NotBlank(message = "수량을 입력해 주세요.")
	private int count;
	private Progress progress = Progress.Examine;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
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
		return "ProductRequest [num=" + num + ", seller=" + seller + ", serialnum=" + serialnum + ", bookName="
				+ bookName + ", writer=" + writer + ", price=" + price + ", category=" + category + ", publisher="
				+ publisher + ", count=" + count + ", progress=" + progress + "]";
	}

	

}
