package models.admin.board.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;
import models.admin.board.AdminBoardRequest;

@Service
public class AdminBoardService {

	@Autowired
	private AdminBoardDao adminBoardDao;

	/** 게시판 등록 S */
	public String write(AdminBoardRequest adminBoardRequest, Errors errors) {

		AdminBoardDto dto = adminBoardDao.searchToId(adminBoardRequest.getBoardId());
		
		if (dto == null) {
				dto = AdminBoardRequest.toDto(adminBoardRequest);
		}else {
			errors.rejectValue("boardId", "AdminBoard.titleError", "값을 찾을 수 없습니다.");
			return "";
		}
		
		/**
			if((dto = adminBoardDao.searchToName(adminBoardRequest.getBoardName())) != null) {
				errors.rejectValue("boardName", "AdminBoard.board", "값을 찾을 수 없습니다.");
			}
		 */
		
		adminBoardDao.save(dto);
		
		return "";
	}
	/** 게시판 등록 E */
	
}
