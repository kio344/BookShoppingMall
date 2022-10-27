package models.comment.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.comment.CommentDao;
import models.comment.CommentDto;
import models.user.UserDto;

@Service
public class ReplyWriteService {

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;

	public BoardDataDto regist(Long id) {
		
		CommentDto com = commentDao.get(id);
		String comments = request.getParameter("comments");
		
		UserDto user = (UserDto)session.getAttribute("user");
		BoardDataDto board = boardDataDao.searchGid(com.getGid());
		
		CommentDto param = new CommentDto();
		param.setBoard(board);
		param.setUser(user);
		param.setPoster(user.getFakeName());
		param.setMatchComment(id);
		param.setComments(comments);
		param.setFirstComment(false);
		param.setGid(com.getGid());
		
		commentDao.replyInsert(param);
		
		return board;
	}
	
}
