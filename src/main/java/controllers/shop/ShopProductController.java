package controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import models.entity.ProductRequest;
import models.entity.ProductReview;
import models.seller.product.ProductRequestDto;
import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.shop.productReview.ProductReviewDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopProductController {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/product/{productnum}")
	public String product(@PathVariable(name = "productnum") long productnum,Model model) {

		ProductRequestDto product=shopService.getProduct(productnum);
		
		product.setScore(Math.round(product.getScore()));
		
		String[] category=product.getCategory().split("/");
		
		List<ProductRequestDto> sameProduct=shopService.getSearchProducts(0, 5, category[1], "category");
		List<ProductReviewDto> productReview=shopService.getProductReview(productnum);
		
		System.out.println(product);
		
		model.addAttribute("addCss", new String[] {"/shop/product"});
		model.addAttribute("addJs",new String[] {"/shop/product","/common/kakaoShare"});
		model.addAttribute("product",product);
		model.addAttribute("sameProduct",sameProduct);
		model.addAttribute("productReview", productReview);
		
		return "shop/product";
	}
}
