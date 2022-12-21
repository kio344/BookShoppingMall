package models.admin.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.admin.board.AdminBoardDao;

@Service
public class AdminBoardDeleteService {
	
	@Autowired
	private AdminBoardDao adminBoardDao; 
	
	/**
	 * 게시판 삭제 기능
	 * @author kimminho
	 * @param request
	 */
	public void delete(HttpServletRequest request) {
		
		/** 유효성 검사 S */
		String[] boardIds = request.getParameterValues("boardId");
		if (boardIds == null) {
			throw new RuntimeException("게시판을 선택 하세요.");
		}
		
		/** 유효성 검사 E */
		
		/** 삭제 시작 S */
		for(String id : boardIds) {
			adminBoardDao.deleteBoard(id);
		}
		/** 삭제 시작 E */
		
		
	}
	
}
