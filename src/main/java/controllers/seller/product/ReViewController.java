package controllers.seller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/review")
public class ReViewController {
	
	
	@GetMapping
	public String review() {
		
		return "/seller/review";
	}
}
