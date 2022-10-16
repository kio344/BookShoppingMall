package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.user.JoinRequest;
import models.user.service.JoinService;

@Controller
@RequestMapping("/user/join")
public class UserJoinController {
	@Autowired
	private JoinService service;
	
	@GetMapping
	public String form(Model model) {
		
		JoinRequest joinRequest = new JoinRequest();
		model.addAttribute("joinRequest", joinRequest);
		
		return "user/join";
		
	}
	
	@PostMapping
	public String process(@Valid JoinRequest joinRequest, Errors errors) {
		
		if(errors.hasErrors()) {
			return "user/join";
		}
		
		service.join(joinRequest, errors);
		
		return "redirect:/user/login";
	}

}
