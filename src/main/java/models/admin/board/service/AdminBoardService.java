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

		

		System.out.println(adminBoardRequest.getBoardId());

		AdminBoardDto dto = null;

		dto = adminBoardDao.get(adminBoardRequest.getBoardId());
		if (dto != null) {
			if (adminBoardRequest.getBoardId().equals(dto.getBoardId()) || adminBoardRequest.getBoardName().equals(dto.getBoardName())|| adminBoardRequest.getPageCount() == 0) {
				return "";
			}
		}

		return "";

	}
}
