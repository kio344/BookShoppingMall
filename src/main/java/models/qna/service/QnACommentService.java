package models.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.qna.QnADao;
import models.qna.QnADto;

@Service
public class QnACommentService {
	
	@Autowired
	private QnADao dao;

	public void comment(Long id, QnADto param) {
		
		QnADto dto = dao.get(id);
		dto.setAnswer(param.getAnswer());
		dto.setAdminName(param.getAdminName());
		
		dao.adminAnswer(dto);
		
	}
	
}
