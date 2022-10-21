package models.admin.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class AdminBoardDeleteService {
	
	public void delete(HttpServletRequest request) {
		
		/** 유효성 검사 S */
		if(request.getParameter("boardId") == null || request.getParameter("boardId").isBlank()) {
			/** 오류 출력 */
		}
		/** 유효성 검사 E */
		
		
		
	}
	
}
