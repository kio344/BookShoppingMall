package controllers.shop;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.user.UserDto;

@RestController
@RequestMapping("/shop")
public class ShopRestController {
	
	@Autowired
	ProductDao dao;
	

	
	@GetMapping("/test2")
	public void test2(HttpSession session) {
		
		System.out.println(dao.get("책이름"));
		
		
	}
	
}
