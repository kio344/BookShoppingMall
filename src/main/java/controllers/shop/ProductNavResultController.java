package controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.product.ProductRequestDto;
import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop/productNav")
public class ProductNavResultController {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping
	public String result(String cat1,String cat2,String cat3,Model model) {
		
		System.out.println(cat1);
		System.out.println(cat2);
		System.out.println(cat3);
		
		String searchVal="";
		if (!(cat1==null||cat1.isBlank())) {
			searchVal+=cat1;
		}
		
		if (!(cat2==null||cat2.isBlank())) {
			searchVal+="/"+cat2;
		}
		
		if (!(cat3==null||cat3.isBlank())) {
			searchVal+="/"+cat3;
		}
		
		List<ProductRequestDto> productList = shopService.getSearchProducts(0, 9999, searchVal, "category");
		
		model.addAttribute("productList",productList);
		
		return "shop/productNavResult";
	}
}
