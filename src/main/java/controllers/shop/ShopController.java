package controllers.shop;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import models.shop.ProductDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ShopService service;
	
	@GetMapping("/index")
	public String shop(Model model) {
		String[] addJs=new String[] {"/shop/index"};
		String[] addCss=new String[] {"/shop/index"};
		model.addAttribute("addJs",addJs);
		model.addAttribute("addCss",addCss);
		
		List<ProductDto> newProductList=service.getProducts(0,5);
		model.addAttribute("newProductList",newProductList);


		List<ProductDto> bestSellerList=service.getBestSeller(0, 5);
		model.addAttribute("bestSellerList",bestSellerList);
		
		System.out.println(newProductList);
		return "shop/shop";
	}
	

	
	
}
