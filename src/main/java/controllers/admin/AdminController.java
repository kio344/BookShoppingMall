package controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/admin")
	public String adminPage(Model model) {
		
		model.addAttribute("addCss", new String[] { "/board/admin/admin" });
		
		return "admin/admin";
		
	}
	
}
