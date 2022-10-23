package models.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;
import models.board.BoardDataDao;
import models.board.BoardDataDto;

@Service
public class BoardListService {
	
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private AdminBoardDao adminBoardDao;
	
	public List<BoardDataDto> gets(String boardId) {
		
		AdminBoardDto boardConfig = adminBoardDao.searchToId(boardId);
		
		List<BoardDataDto> boards = boardDataDao.gets(boardId, 1, boardConfig.getPageCount());
		
		
		return boards;
	}

}
