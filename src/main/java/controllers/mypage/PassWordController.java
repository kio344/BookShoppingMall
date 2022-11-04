package controllers.mypage;

import javax.servlet.http.HttpServletRequest;
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
import models.mypage.PasswordRequest;
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
		model.addAttribute("passwordRequest", new PasswordRequest());
		model.addAttribute("addCss", new String[] { "/mypage/style" });
		return "mypage/password";
	}
	
	@PostMapping("/passwordChange")
	public String changePassword(@Valid PasswordRequest req, Errors errors, Model model) {
		model.addAttribute("addCss", new String[] { "/mypage/style" });
		
		try{
		changeService.changePassword(req, errors);
		}catch (CommonException e) {
			String field = e.getField();
			if(field == null){
				errors.reject(e.getMessage());
			}else {
				errors.rejectValue(field, e.getMessage());
			}
		}
		
		if(errors.hasErrors()) {
			return "mypage/password";
		}
		
		return "redirect:/mypage";
	}
}
