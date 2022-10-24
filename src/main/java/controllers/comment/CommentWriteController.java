package controllers.comment;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDto;
import models.comment.service.CommentWriteService;

@Controller
@RequestMapping("/board/comment")
public class CommentWriteController {
	
	@Autowired
	private CommentWriteService commentWriteService;

	@PostMapping("/write")
	public String process(Model model, HttpServletRequest request) {
		
		BoardDataDto board = commentWriteService.regist(request);
		
		
		return "redirect:/board/view/" + board.getId();
	}
	
}
