package controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopProductController {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/product/{productnum}")
	public String product(@PathVariable(name = "productnum") long productnum,Model model) {

		ProductDto product=shopService.getProduct(productnum);
		
		String[] category=product.getCategory().split("/");
		
		List<ProductDto> sameProduct=shopService.getSearchProducts(0, 5, category[1], "category");
		
		model.addAttribute("addCss", new String[] {"/shop/product"});
		model.addAttribute("product",product);
		model.addAttribute("sameProduct",sameProduct);
		
		return "shop/product";
	}
}
