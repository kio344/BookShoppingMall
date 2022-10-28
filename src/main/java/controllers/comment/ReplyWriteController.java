package controllers.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDto;
import models.comment.service.ReplyWriteService;

@Controller
@RequestMapping("/board/reply")
public class ReplyWriteController {
	
	@Autowired
	private ReplyWriteService replyWriteService;

	@PostMapping("/write/{id}")
	public String process(@PathVariable(name = "id") Long id) {
		
		BoardDataDto board = replyWriteService.regist(id);
		
		
		return "redirect:/board/view/" + board.getId();
	}
	
}
