package models.mypage.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.user.UserDao;
import models.user.UserDto;

@Service
public class PasswordChangeService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private HttpSession session;
	
	public void changePassword(HttpServletRequest req) {
		UserDto dto = (UserDto) session.getAttribute("user");
		String passwordChange = req.getParameter("passwordChange");
		String passwordChangeRe = req.getParameter("passwordChangeRe");
		
		dto = dao.check(dto.getMemId());
		boolean password = BCrypt.checkpw(req.getParameter("password"), dto.getMemPw());
		if(!password) {
			return;
		}
		
		if(!(passwordChange.equals(passwordChangeRe))) {
			return;
		}
		
		passwordChange = BCrypt.hashpw(passwordChange, BCrypt.gensalt(10));
		dto.setMemPw(passwordChange);
		
		dao.password(dto);
		
		
	}
}
