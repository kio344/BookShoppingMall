package models.admin.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;

@Service
public class AdminBoardUpdateService {
	
	@Autowired
	private AdminBoardDao adminBoardDao;

	public void update(HttpServletRequest request) {

		/** 유효성 검사 S */
		String[] boardIds = request.getParameterValues("boardId");
		if (boardIds == null) {
			/** 오류 출력 */
		} else {
			for (String id : boardIds) {
				String boardNm = request.getParameter("boardName_" + id);
				if (boardNm == null || boardNm.isBlank()) {
					/** 오류 출력 s */
				}
			}
		}

		/** 유효성 검사 E */

		/** 수정 시작 S */
		for (String id : boardIds) {
			AdminBoardDto dto = new AdminBoardDto();
			int pageCount = 0;
			try {
				pageCount = Integer.parseInt(request.getParameter("pageCount_" + id));
				
			} catch (Exception e) {}
			if (pageCount == 0) {
				dto.setPageCount(20);
			} else {
				dto.setPageCount(pageCount);
			}
			dto.setBoardId(request.getParameter("boardId"));
			dto.setBoardName(request.getParameter("boardName_" + id));
			dto.setIsUse(Integer.parseInt(request.getParameter("isUse_" + id)));
			dto.setCommentUse(Integer.parseInt(request.getParameter("commentUse_" + id)));
			dto.setMemberOnly(Integer.parseInt(request.getParameter("memberOnly_" + id)));
			
			adminBoardDao.updateBoard(dto);
		}
		/** 수정 시작 E */

	}

}
