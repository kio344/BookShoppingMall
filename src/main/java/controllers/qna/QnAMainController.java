package controllers.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.qna.QnADto;
import models.qna.service.QnAListService;

@Controller
@RequestMapping("/QnA")
public class QnAMainController {
	
	@Autowired
	private QnAListService listService;

	@GetMapping
	public String form(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
		
		model.addAttribute("addCss", new String[] { "/qna/style" });
		
		List<QnADto> qnas = listService.list(page, model);
		model.addAttribute("qnas", qnas);
		
		return "qna/index";
	}
	
}
