package controllers.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.comment.CommentDto;
import models.comment.service.CommentUpdateService;

@Controller
@RequestMapping("/board/comment")
public class CommentUpdateController {
	
	@Autowired
	private CommentUpdateService service;

	@PostMapping("/update/{id}")
	public String process(@PathVariable(name = "id") Long id) {
		
		CommentDto comment = service.update(id);
		
		return "redirect:/board/view/" + comment.getBoard().getId();
	}
	
}
