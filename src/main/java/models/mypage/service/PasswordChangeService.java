package models.mypage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.mypage.PasswordRequest;
import models.mypage.exception.PasswordChangeReException;
import models.mypage.exception.PasswordCheckException;
import models.user.UserDao;
import models.user.UserDto;

@Service
public class PasswordChangeService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private HttpSession session;
	
	public void changePassword(PasswordRequest req, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		
		UserDto dto = (UserDto) session.getAttribute("user");
		String passwordChange = req.getChangePassword();
		String passwordChangeRe = req.getChangePasswordRe();
		
		dto = dao.check(dto.getMemId());
		
		boolean password = BCrypt.checkpw(req.getPassword(), dto.getMemPw());
		
		if(!password) {
			throw new PasswordCheckException();
		}
		
		if(!(passwordChange.equals(passwordChangeRe))) {
			throw new PasswordChangeReException();
		}
		
		
		passwordChange = BCrypt.hashpw(passwordChange, BCrypt.gensalt(10));
		dto.setMemPw(passwordChange);
		
		dao.password(dto);
		
	}
}
