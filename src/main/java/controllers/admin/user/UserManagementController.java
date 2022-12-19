package controllers.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/")
public class UserManagementController {
	
	
	@GetMapping("user")
	public String userManager(Model model) {
		
		
		
		return "admin/user/user";
	}
	
//	@PostMapping("")
//	public String userInfoCng(@RequestParam UserInfo userinfo) {
//		
//		
//		
//	}
	
}
