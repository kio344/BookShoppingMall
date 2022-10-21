package models.board.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;
import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.board.BoardDataRequest;
import models.file.FileInfoDao;
import models.user.UserDto;

@Service
public class BoardWriteService {
	
	@Autowired
	private AdminBoardDao adminBoardDao;
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private FileInfoDao fileInfoDao;
	

	public BoardDataDto register(BoardDataRequest request, Errors errors, HttpSession session) {
		
		if(errors.hasErrors()) {
			return null;
		}
		
		UserDto user = (UserDto)session.getAttribute("user");
		AdminBoardDto adminBoard = adminBoardDao.searchToId(request.getBoardId());
		
		BoardDataDto param = new BoardDataDto();
		param.setBoard(adminBoard);
		param.setContents(request.getContents());
		param.setGid(request.getGid());
		param.setPoster(request.getPoster());
		param.setSubject(request.getSubject());
		param.setUser(user);
		
		BoardDataDto board = boardDataDao.register(param);
		fileInfoDao.doneFinish(request.getGid());
		
		
		return board;
	}
	
}
