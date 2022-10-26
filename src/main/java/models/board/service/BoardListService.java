package models.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import common.page.Pagination;
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
	
	public List<BoardDataDto> gets(String boardId, Model model, int page) {
		
		AdminBoardDto boardConfig = adminBoardDao.searchToId(boardId);
		
		List<BoardDataDto> boards = boardDataDao.gets(boardId, page, boardConfig.getPageCount());
		
		Long _total = boardDataDao.total(boardId);
		int total = _total.intValue();
		Pagination pagination = new Pagination(page, total, 5, boardConfig.getPageCount());
		System.out.println(pagination);
		model.addAttribute("pagination", pagination);
		model.addAttribute("boardId", boardId);
		
		
		return boards;
	}

}
