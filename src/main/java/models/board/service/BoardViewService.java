package models.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.board.BoardDataDao;
import models.board.BoardDataDto;

@Service
public class BoardViewService {
	
	@Autowired
	private BoardDataDao boardDataDao;
	
	public BoardDataDto view(Long id) {
		
		BoardDataDto board = boardDataDao.get(id);
		
		return board;
	}

}
