package models.board.service;

import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import common.error.CommonException;
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
		BoardDataDto param = new BoardDataDto();
		if(request.getBoardId() != null) {
			AdminBoardDto adminBoard = adminBoardDao.searchToId(request.getBoardId());
			param.setBoard(adminBoard);
		}
		param.setPrivate(request.isPrivate());
		if(request.isPrivate()) {
			String pass = request.getPrivatePassword();
			if(pass == null || pass.isBlank()) {
				throw new CommonException("비밀글 전용 비밀번호를 입력해주세요.", "privatePassword");
			}
			pass = BCrypt.hashpw(pass, BCrypt.gensalt(12));
			param.setPrivatePassword(pass);
		}
		param.setContents(request.getContents());
		param.setGid(request.getGid());
		param.setPoster(request.getPoster());
		param.setSubject(request.getSubject());
		param.setUser(user);
		
		if(request.getId() != null) {
			param.setId(request.getId());
		}
		
		BoardDataDto board = boardDataDao.register(param);
		fileInfoDao.doneFinish(request.getGid());
		
		
		return board;
	}
	
}
