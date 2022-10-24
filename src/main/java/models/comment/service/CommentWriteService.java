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
public class CommentWriteService {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private HttpSession session;

	public BoardDataDto regist(HttpServletRequest request) {
		
		String gid = request.getParameter("gid");
		String comments = request.getParameter("comments");
		System.out.println(gid);
		System.out.println(comments);
		UserDto user = (UserDto)session.getAttribute("user");
		BoardDataDto board = boardDataDao.searchGid(gid);
		
		System.out.println(board);
		
		CommentDto param = new CommentDto();
		param.setBoard(board);
		param.setUser(user);
		param.setPoster(user.getFakeName());
		param.setComments(comments);
		param.setFirstComment(true);
		param.setGid(gid);
		
		commentDao.register(param);
		
		return board;
	}
	
}
