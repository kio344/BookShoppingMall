package controllers.seller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SellerController {
	
	@RequestMapping("/seller")
	public String seller(Model model) {
		
		model.addAttribute("addCss", new String [] {"/seller/sellerMain"});
		
		return "seller/seller";
	}
}
