package models.seller.edit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDto;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.productReview.ProductReviewDao;
import models.shop.productReview.ProductReviewDto;

@Service
public class SearchService {
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ProductReviewDao productReviewDao;
	
	public List<ProductRequestDto> search(String searchType, String search, int start, int offset) {
		
		List<ProductRequestDto> list = searchDao.searchToName(search, searchType, start, offset);
		
		return list;
	}

	public int total(String search, String searchType) {
		
		return searchDao.getsearchProduct(search, searchType);
		
	}
	
	public List<ProductReviewDto> getReview(Long productNum){
		
		ProductRequestDto productRequestDto = searchDao.getProduct(productNum);
		
		/**
		 * 상품 PK로 해당 상품에 결제된 상품이 있으면 가져오기
		 */
		List<PaymentDto> list = paymentDao.getPaymentNum(productRequestDto);
		
		List<ProductReviewDto> productReviewDto = productReviewDao.getProductReview(list);
		
		return productReviewDto;
	}
	
}
