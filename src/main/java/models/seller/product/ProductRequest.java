package models.seller.product;


public class ProductRequest{
	
	private Long num; // 이거 기준으로 폴더 나누기.
	private String seller;
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
