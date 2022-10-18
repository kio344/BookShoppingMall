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

	public String write(AdminBoardRequest adminBoardRequest, Errors errors) {

		AdminBoardDto dto = adminBoardDao.get(adminBoardRequest.getBoardId());
		
		if (dto == null) {
				dto = AdminBoardRequest.toDto(adminBoardRequest);
				
		}else {
			return null;
		}
		
		adminBoardDao.save(dto);
		
		return "";

	}
}
