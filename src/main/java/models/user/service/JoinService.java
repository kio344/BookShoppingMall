package models.user.service;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.user.JoinRequest;
import models.user.UserDao;
import models.user.UserDto;

@Service
public class JoinService {

	@Autowired
	private UserDao userDao;

	public void join(JoinRequest joinRequest, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		// 아이디 중복 체크
		String memId = joinRequest.getMemId();
		UserDto user = userDao.check(memId);

		if (user != null) {
			throw new RuntimeException("중복된 ID가 존재합니다.");
		}

		// 비밀번호, 비밀번호 확인 일치 체크
		String memPw = joinRequest.getMemPw();
		String memPwRe = joinRequest.getMemPwRe();

		if (!memPw.equals(memPwRe)) {
			throw new RuntimeException("입력한 비밀번호가 일치하지 않습니다.");
		}
		
		//전화 번호 양식 체크
		String mobile = joinRequest.getMobile();
		mobile = mobile.replaceAll("\\D", "");
		boolean isMatch = mobile.matches("01[016]\\d{3,4}\\d{4}");
		if (!isMatch) { // 패턴이 일치하지 않는 경우
			throw new RuntimeException("전화번호 형식에 맞지 않습니다.");
		}
		
		String hash = BCrypt.hashpw(memPw, BCrypt.gensalt(10));
		
		UserDto param = new UserDto();
		param.setMemId(memId);
		param.setMemPw(hash);
		param.setMemNm(joinRequest.getMemNm());
		param.setFakeName(joinRequest.getFakeName());
		param.setMobile(joinRequest.getMobile());
		param.setEmail(joinRequest.getEmail());
		param.setAdress(joinRequest.getAdress());
		param.setBirthDay(joinRequest.getBirthDay());
		param.setGender(joinRequest.getGender());
		
		userDao.check(param);

	}

}
