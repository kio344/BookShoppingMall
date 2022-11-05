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

import common.error.CommonException;
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
		model.addAttribute("addCss", new String[] { "/member/login" });
		return "user/login";
		
	}
	
	@PostMapping
	public String process(@Valid LoginRequest loginRequest, Errors errors, HttpSession session, Model model) {
		
		model.addAttribute("addCss", new String[] { "/member/login" });
		
		try {
			UserDto user = service.login(loginRequest, errors);
			
			System.out.println();
			
			session.setAttribute("user", user);
			
		} catch (CommonException e) {
			errors.rejectValue(e.getField(), e.getMessage());
		}
	 	if(errors.hasErrors()) {
	 		return "user/login";
	 	}
	 	
		return "redirect:/";
			
	}
	
	
}
