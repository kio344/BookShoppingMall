package controllers.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.comment.CommentDao;
import models.comment.CommentDto;

@Controller
public class CommentDeleteController {
	
	@Autowired
	private CommentDao commentDao;

	@RequestMapping("/board/comment/delete/{id}")
	public String process(@PathVariable(name = "id") Long id) {
		
		CommentDto comment = commentDao.delete(id);
		
		return "redirect:/board/view/" + comment.getBoard().getId();
	}
	
}
