package controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.error.CommonException;
import models.user.JoinRequest;
import models.user.service.JoinService;

@Controller
@RequestMapping("/user/join")
public class UserJoinController {
	@Autowired
	private JoinService service;
	
	@GetMapping
	public String form(@RequestParam(name = "kakaoId", required = false) Long kakaoId, Model model) {
		
		JoinRequest joinRequest = new JoinRequest();
		joinRequest.setKakaoId(kakaoId);
		model.addAttribute("joinRequest", joinRequest);
		model.addAttribute("addCss", new String[] { "/member/join" });
		return "user/join";
	}
	
	@PostMapping
	public String process(@Valid JoinRequest joinRequest, Errors errors, Model model, HttpServletRequest req) {
		
		model.addAttribute("addCss", new String[] { "/member/join" });
		
		try {
			service.join(joinRequest, errors, req);
		} catch (CommonException e) {
			String field = e.getField();
			if (field == null) {
				errors.reject(e.getMessage());
			} else {
				errors.rejectValue(field, e.getMessage());
			}
		}
		
		if(errors.hasErrors()) {
			return "user/join";
		}
		
		return "redirect:/user/login";
	}

}
