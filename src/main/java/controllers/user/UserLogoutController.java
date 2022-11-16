package controllers.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.user.UserDto;

@Controller
@RequestMapping("/user/logout")
public class UserLogoutController {
	
	@GetMapping
	public String form(HttpSession session) {
		
		UserDto user = (UserDto)session.getAttribute("user");
		
		if(user != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}

}
