package controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@GetMapping("/index")
	public String shop(Model model) {
		String[] addJs=new String[] {"shop/index"};
		model.addAttribute("addJs",addJs);
		
		return "shop/shop";
	}
	

	
	
}
