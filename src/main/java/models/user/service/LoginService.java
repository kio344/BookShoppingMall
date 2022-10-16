package models.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.user.LoginRequest;
import models.user.UserDao;
import models.user.UserDto;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;
	
	public UserDto login(LoginRequest loginRequest, Errors errors) {
	
		if(errors.hasErrors()) {
			return null;
		}
		
		String memId = loginRequest.getMemId();
		UserDto user = userDao.check(memId);

		if (user == null) {
			throw new RuntimeException("존재하지 않는 ID 입니다.");
		}
		
		if(!loginRequest.getMemPw().equals(user.getMemPw())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return user;
	}
	
	
}
