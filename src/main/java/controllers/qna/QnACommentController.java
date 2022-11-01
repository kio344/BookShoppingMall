package controllers.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.qna.QnADto;
import models.qna.service.QnACommentService;

@Controller
@RequestMapping("/QnA")
public class QnACommentController {
	
	@Autowired
	private QnACommentService service;

	@PostMapping("/comment/{id}")
	public String process(@PathVariable(name = "id") Long id, QnADto dto) {
		
		service.comment(id, dto);
		
		return "redirect:/QnA/view/" + id;
	}
}
