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
import models.board.SearchData;

@Service
public class BoardListService {
	
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private AdminBoardDao adminBoardDao;
	@Autowired
	private HttpServletRequest request;
	
	public List<BoardDataDto> gets(String boardId, Model model, int page) {
		
		AdminBoardDto boardConfig = adminBoardDao.searchToId(boardId);
		
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		
		
		List<BoardDataDto> boards = boardDataDao.gets(boardId, page, boardConfig.getPageCount(), select, search);
		
		Long _total = boardDataDao.total(boardId);
		int total = _total.intValue();
		
		Pagination pagination = new Pagination(page, total, 5, boardConfig.getPageCount());
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("boardId", boardId);
		model.addAttribute("boardName", boardConfig.getBoardName());
		
		
		return boards;
	}

}
