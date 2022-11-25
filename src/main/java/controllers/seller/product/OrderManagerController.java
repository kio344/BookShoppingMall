package controllers.seller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.product.ProductRequestDao;

@Controller
@RequestMapping("/seller/product")
public class OrderManagerController {
	
	@Autowired
	private ProductRequestDao dao;
	
	@GetMapping("/orderManager")
	public String productManager(Model model) {
		
		
		
		return "/seller/orderManager";
	}
	
	@PostMapping
	public String test() {
		
		
		
		return "";
	}
	
}
