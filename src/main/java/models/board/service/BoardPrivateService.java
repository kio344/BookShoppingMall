package models.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.error.CommonException;
import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.board.BoardPrivateRequest;

@Service
public class BoardPrivateService {
	
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private HttpServletResponse response;

	public BoardDataDto sucess(BoardPrivateRequest boardPrivateRequest) {
		
		BoardDataDto board = boardDataDao.get(boardPrivateRequest.getId());
		
		if(!BCrypt.checkpw(boardPrivateRequest.getPassword(), board.getPrivatePassword())) {
			throw new CommonException("비밀번호가 일치하지 않습니다.");
		}
		
		Cookie cookie = new Cookie("board" + board.getId(), "true");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
		
		return board;
	}
	
}
