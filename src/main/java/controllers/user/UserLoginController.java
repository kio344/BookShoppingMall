package controllers.user;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.user.LoginRequest;
import models.user.UserDto;
import models.user.service.LoginService;

@Controller
@RequestMapping("/user/login")
public class UserLoginController {

	@Autowired
	private LoginService service;
	
	@GetMapping
	public String form(Model model) {
		
		LoginRequest loginRequest = new LoginRequest();
		
		model.addAttribute("loginRequest", loginRequest);
		
		return "user/login";
		
	}
	
	@PostMapping
	public String process(@Valid LoginRequest loginRequest, Errors errors, HttpSession session) {
		
	 	UserDto user = service.login(loginRequest, errors);
		
	 	session.setAttribute("user", user);
	 	
	 	if(errors.hasErrors()) {
	 		return "user/login";
	 	}
	 	
		return "redirect:/";
			
	}
	
	
}
