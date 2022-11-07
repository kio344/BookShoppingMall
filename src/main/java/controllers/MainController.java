package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	
	
	@GetMapping("")
	public String form(Model model) {
		
		model.addAttribute("addCss", new String[] {"/main/mainPage"});
		
		return "index";
	}
	
}
