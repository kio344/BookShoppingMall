package models.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.user.UserDao;
import models.user.UserDto;
import models.user.UserRequest;

@Service
public class UserInfoService {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private HttpSession session;
	
	public void change(UserRequest req) {
		
		req.setBirthDay(request.getParameter("birthDay"));
		
		UserDto dto = dao.update(req);
		
		session.setAttribute("user", dto);
		
	}
	
}
