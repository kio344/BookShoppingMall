package models.user.service;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.mysql.cj.exceptions.PasswordExpiredException;

import models.user.LoginRequest;
import models.user.UserDao;
import models.user.UserDto;
import models.user.exception.UserNotFoundException;

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
			throw new UserNotFoundException();
		}
		
		boolean pwMatched = BCrypt.checkpw(loginRequest.getMemPw(), user.getMemPw());
		
		if(!pwMatched) {
			throw new PasswordExpiredException();
		}
		
		return user;
	}
	
	
}
