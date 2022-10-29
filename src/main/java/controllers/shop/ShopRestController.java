package controllers.shop;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.entity.Product;
import models.shop.ProductDao;
import models.shop.ProductDto;
import models.user.UserDto;

@RestController
@RequestMapping("/shop")
public class ShopRestController {
	
	@Autowired
	ProductDao dao;
	
	@GetMapping("/test")
	public void test(HttpSession session) {
		
		UserDto user=(UserDto)session.getAttribute("user");
		ProductDto param=new ProductDto();
		param.setNum(180L);
		param.setBookName("책이름122");
		param.setCategory("카테고리");
		param.setCount(13);
		param.setPrice(13400L);
		param.setPublisher("출판사");
		param.setSeller(user);
		param.setSerialnum("시리얼 넘버");
		param.setWriter("지은이");
		
		
		dao.addProduct(param);
		
		
	}
	
	@GetMapping("/test2")
	public void test2(HttpSession session) {
		
		System.out.println(dao.get("책이름"));
		
		
	}
	
}
