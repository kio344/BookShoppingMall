package controllers.mypage;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.mypage.service.UserInfoService;
import models.user.UserDto;
import models.user.UserRequest;

@Controller
@RequestMapping("/mypage")
public class UserInfoController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserInfoService service;
	
	@GetMapping("/userInfo")
	public String UserInfo(Model model){
		UserDto user = (UserDto) session.getAttribute("user");
		
		model.addAttribute("user", user);
		
		return "mypage/userInfo";
	}
	
	@PostMapping("/changeInfo")
	public String ChangeInfo(@Valid UserRequest request, Errors errors) {
		
		service.change(request);
		
		return "redirect:/mypage/userInfo";
	}
}
