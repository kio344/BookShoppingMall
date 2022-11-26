package common.Util;

import javax.servlet.http.HttpSession;

import models.user.UserDto;
import models.user.UserType;

public class JmsUtil {

	public static UserDto getLoginUser(HttpSession session) {
		
		UserDto loginUser=(UserDto)session.getAttribute("user");
		
		return loginUser;
		
	}
	
	public static void autoLogin(HttpSession session) {
		/** 로그인 귀찮을때 S */
		UserDto user = (UserDto) session.getAttribute("user");

		if (user == null) {
			user = new UserDto();
			user.setMemId("5563a");
			user.setEmail("jmspon33@gmail.com");
			user.setMemNo(109L);
			user.setMobile("01075175563");
			user.setMemNm("정민상");
			user.setFakeName("KING");
			user.setUserType(UserType.USER);

			session.setAttribute("user", user);
		}
		/** 로그인 귀찮을때 E */
	}
	
	public static void autoLoginMinho(HttpSession session) {
		/** 로그인 귀찮을때 S */
		UserDto user = (UserDto) session.getAttribute("user");
		
		if (user == null) {
			user = new UserDto();
			user.setMemId("kimm3654");
			user.setEmail("kimm3654@naver.com");
			user.setMemNo(25L);
			user.setMobile("01085526202");
			user.setMemNm("김민호");
			user.setFakeName("키오");
			user.setUserType(UserType.SELLER);
			
			session.setAttribute("user", user);
		}
		/** 로그인 귀찮을때 E */
	}

	
}
