package controllers.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.admin.user.UserInfoDao;
import models.user.UserDto;

@Controller
@RequestMapping("admin/")
public class UserManagementController {
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@GetMapping("user")
	public String userManager(Model model) {
		UserDto dto = userInfoDao.gets();
		
		model.addAttribute("userInfo", dto);
		System.out.println(dto);
		return "admin/user/user";
	}
	
//	@PostMapping("")
//	public String userInfoCng(@RequestParam UserInfo userinfo) {
//		
//		
//		
//	}
	
}
