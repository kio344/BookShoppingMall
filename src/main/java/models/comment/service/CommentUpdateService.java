package models.comment.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.comment.CommentDao;
import models.comment.CommentDto;

@Service
public class CommentUpdateService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private HttpServletRequest request;
	
	public CommentDto update(Long id) {
		
		CommentDto param = new CommentDto();
		param.setId(id);
		param.setComments(request.getParameter("comments"));
		
		CommentDto comment = commentDao.update(param);
		
		
		return comment;
	}
	
}
