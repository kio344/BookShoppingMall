package controllers.qna;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.qna.QnADto;
import models.qna.QnARequest;
import models.qna.service.QnAWriteService;
import models.user.UserDto;
import models.user.UserType;

@Controller
@RequestMapping("/QnA")
public class QnAWriteController {
	
	@Autowired
	private QnAWriteService service;

	@GetMapping("/write")
	public String form(Model model, HttpSession session) {
		QnARequest qnARequest = new QnARequest();
		UserDto user = (UserDto)session.getAttribute("user");
		qnARequest.setPoster(user.getMemId());
		if(user.getUserType() == UserType.ADMIN) {
			qnARequest.setAdmin(true);
		} else {
			qnARequest.setAdmin(false);
		}
		
		model.addAttribute("qnARequest", qnARequest);
		model.addAttribute("addCss", new String[] { "/qna/write" });
		
		return "qna/write";
	}
	
	@PostMapping("/write")
	public String process(@Valid QnARequest request, Errors errors, Model model) {
		model.addAttribute("addCss", new String[] { "/qna/write" });
		
		QnADto qna = service.register(request, errors);
		
		if(errors.hasErrors()) {
			return "qna/write";
		}
		
		return "redirect:/QnA/view/" + qna.getId();
	}
}
