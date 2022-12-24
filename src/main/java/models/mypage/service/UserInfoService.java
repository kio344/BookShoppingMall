package models.mypage.service;

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
	private UserDao dao;
	
	@Autowired
	private HttpSession session;
	
	public void change(UserRequest userRequest, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(request.getParameter("postcode"));
		sb.append("/");
		sb.append(request.getParameter("addr"));
		sb.append("/");
		sb.append(request.getParameter("extraaddr"));
		sb.append("/");
		sb.append(request.getParameter("adress"));
		
		userRequest.setAdress(sb.toString());
		
		UserDto dto = dao.update(userRequest);
		session.removeAttribute("user");
		session.setAttribute("user", dto);
	}
	
}
