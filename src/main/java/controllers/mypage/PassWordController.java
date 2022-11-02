package controllers.mypage;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.mypage.service.PasswordChangeService;
import models.user.UserDto;

@Controller
@RequestMapping("/mypage")
public class PassWordController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PasswordChangeService changeService;
	
	@GetMapping("/changePass")
	public String password(Model model) {
		UserDto dto = (UserDto) session.getAttribute("user");
		
		model.addAttribute("user", dto);
		return "mypage/password";
	}
	
	@PostMapping("/passwordChange")
	public String changePassword(HttpServletRequest request) {
		
		changeService.changePassword(request);
		
		return "redirect:/mypage";
	}
}
