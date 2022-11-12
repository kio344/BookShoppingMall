package controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import models.shop.ProductDao;
import models.shop.ProductDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopProductController {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/product/{productnum}")
	public String product(@PathVariable(name = "productnum") long productnum,Model model) {

		ProductDto product=shopService.getProduct(productnum);
		
		model.addAttribute("addCss", new String[] {"/shop/product"});
		model.addAttribute("product",product);
		
		return "shop/product";
	}
}
