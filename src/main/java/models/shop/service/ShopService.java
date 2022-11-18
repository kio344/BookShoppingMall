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
	
	public List<ProductDto> getProducts(int start,int offset){
		return productDao.gets(start, offset);
				
	}
	
	public List<ProductDto> getBestSeller(int start,int offset){
		
		return productDao.getbestSeller(start, offset);
	}
	
	public ProductDto getProduct(Long num) {
		
		return productDao.get(num);
		
	}
	
}
