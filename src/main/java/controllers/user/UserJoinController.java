package controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.user.JoinRequest;

@Controller
@RequestMapping("/user/join")
public class UserJoinController {
	
	@GetMapping
	public String form(Model model) {
		
		JoinRequest joinRequest = new JoinRequest();
		model.addAttribute("joinRequest", joinRequest);
		
		return "user/join";
		
	}
	
	@PostMapping
	public String process(JoinRequest joinRequest, Errors errors) {
		if(errors.hasErrors()) {
			return "user/join";
		}
		
		return "redirect:/user/login";
	}

}
