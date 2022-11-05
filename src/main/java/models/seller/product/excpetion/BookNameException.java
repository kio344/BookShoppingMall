package models.seller.product.excpetion;

import common.error.CommonException;

public class BookNameException extends CommonException{
	
	public BookNameException() {
		super("productRequest", "bookName");
	}

}
