package controllers.seller.product;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.product.ProductRequest;

@Controller
@RequestMapping("/seller")
public class ProductController {

	@GetMapping("/sellerProduct")
	public String product(Model model) {
		ProductRequest productRequest = new ProductRequest();
		model.addAttribute("productRequest", productRequest);
		
		return "seller/sellerProduct";
	}

	@PostMapping("/productRequest")
	public String productRequest(@Valid ProductRequest request, Errors errors, Model model) {
		
		
		
		return "seller/sellerProduct";
	}
}
