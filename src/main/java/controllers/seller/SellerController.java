package controllers.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerController {
	
	@RequestMapping("/seller")
	public String seller() {
		
		return "seller/seller";
	}
}
