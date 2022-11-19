package models.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.shop.product.ProductDao;
import models.shop.product.ProductDto;

@Service
public class ShopService {
	@Autowired
	private ProductDao productDao;
	
	/**
	 * 최신 상품들 가져오기
	 * @author 5563a
	 * @param start		페이지네이션
	 * @param offset	페이지네이션
	 * @return
	 */
	public List<ProductDto> getProducts(int start,int offset){
		return productDao.gets(start, offset);
				
	}
	
	/**
	 * 베스트셀러 가져오기 
	 * @author 5563a
	 * @param start		페이지네이션
	 * @param offset	페이지네이션
	 * @return
	 */
	public List<ProductDto> getBestSeller(int start,int offset){
		
		return productDao.getbestSeller(start, offset);
	}
	
	/**
	 * 상품 가져오기
	 * @author 5563a
	 * @param num Product.num
	 * @return
	 */
	public ProductDto getProduct(Long num) {
		
		return productDao.get(num);
		
	}
	
	/**
	 * 검색한 상품 가져오기
	 * @author 5563a
	 * @param start			페이지네이션
	 * @param offset		페이지네이션
	 * @param searchValue	검색값
	 * @param SearchType	검색종류 (책이름, 지은이)
	 * @return List<ProductDto>
	 */
	public List<ProductDto> getSearchProducts(int start, int offset,String searchValue,String SearchType){
		
		return productDao.getSearchProduct(start, offset, searchValue, SearchType);
				
				
	}
	
	/**
	 * 검색한 상품 개수
	 * @param searchValue	검색값
	 * @param SearchType	검색종류 (책이름, 지은이)
	 * @return
	 */
	public int getSearchProductsCount(String searchValue,String SearchType){
		
		return productDao.getSearchProductCount(searchValue, SearchType);
				
				
	}
	
}
