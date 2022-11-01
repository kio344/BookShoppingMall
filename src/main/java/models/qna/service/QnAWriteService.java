package models.qna.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.qna.QnADao;
import models.qna.QnADto;
import models.qna.QnARequest;
import models.user.UserDto;
import models.user.UserType;

@Service
public class QnAWriteService {
	
	@Autowired
	private QnADao dao;
	@Autowired
	private HttpSession session;
	
	public QnADto register(QnARequest request, Errors errors) {
		if(errors.hasErrors()) {
			return null;
		}
		
		QnADto param = new QnADto();
		param.setAdmin(request.isAdmin());
		param.setAnswered(false);
		param.setPoster(request.getPoster());
		param.setQuestion(request.getQuestion());
		param.setSubject(request.getSubject());
		
		UserDto user = (UserDto)session.getAttribute("user");
		param.setUser(user);
		if(user.getUserType() == UserType.ADMIN) {
			param.setAdmin(true);
		} else {
			param.setAdmin(false);
		}
		
		QnADto dto = dao.register(param);
		
		
		return dto;
	}

}
