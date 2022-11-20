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

		if (errors.hasErrors()) {
			return null;
		}

		String memId = loginRequest.getMemId();
		UserDto user = userDao.check(memId);

		if (user == null) {
			throw new UserNotFoundException();
		}

		boolean pwMatched = BCrypt.checkpw(loginRequest.getMemPw(), user.getMemPw());

		if (!pwMatched) {
			throw new PasswordExpiredException();
		}

		return user;
	}

	/**
	 * 결제에 필요한 토큰값 가져오기
	 * @param memNo
	 * @return
	 */
	public String getUserTocken(Long memNo) {

		UserDto user = userDao.get(memNo);

		String befor=user.getMemNo()+user.getMemId();
		
		String tocken=BCrypt.hashpw(befor, BCrypt.gensalt(10));
		
		return tocken;
		
	}
	
	public UserDto getUser(Long memNo) {
		return userDao.get(memNo);
	}

}
