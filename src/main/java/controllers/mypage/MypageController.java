package controllers.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@GetMapping
	public String form(Model model) {
		
		
		model.addAttribute("addCss", new String[] { "/mypage/style" });
		
		return "mypage/index";
	}

}
