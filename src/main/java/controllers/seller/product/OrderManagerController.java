package controllers.seller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/product")
public class OrderManagerController {
	
	@GetMapping("/orderManager")
	public String productManager() {
		
		
		
		return "/seller/orderManager";
	}
	
}
