package controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.entity.Product;
import models.shop.ProductDao;
import models.shop.ProductDto;

@RestController
@RequestMapping("/shop")
public class ShopRestController {
	
	@Autowired
	ProductDao dao;
	
	@GetMapping("/test")
	public void test() {
		
		ProductDto param=new ProductDto();
		
		
		dao.addProduct(param);
		
		
	}
	
}
