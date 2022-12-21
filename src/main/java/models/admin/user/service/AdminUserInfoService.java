package models.admin.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.user.UserDao;
import models.user.UserDto;
import models.user.UserType;

@Service
public class AdminUserInfoService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 회원 정보 수정 후 DB에 저장
	 * @author kimminho
	 * @param request
	 */
	public void edit(HttpServletRequest request) {
		String [] userInfos = request.getParameterValues("memNo");
		UserDto dto = new UserDto();
		if(userInfos == null) {
			throw new RuntimeException("회원을 선택해 주세요.");
		}
		
		for(String users : userInfos) {
			dto = userDao.get(Long.parseLong(users));
			dto.setMemNm(request.getParameter("memNm_" + users));
			dto.setFakeName(request.getParameter("fakeName_" + users));
			if(request.getParameter("userType_" + users).equals(UserType.ADMIN.toString())) {
				dto.setUserType(UserType.ADMIN);
			}else if(request.getParameter("userType_" + users).equals(UserType.SELLER.toString())) {
				dto.setUserType(UserType.SELLER);
			}else {
				dto.setUserType(UserType.USER);
			}
			dto.setAdress(request.getParameter("adress_" + users));
			
			userDao.edit(dto);
		}
		
	}

	/**
	 * 회원 정보 삭제
	 * @author kimminho
	 * @param request
	 */
	public void remove(HttpServletRequest request) {

		String [] userInfos = request.getParameterValues("memNo");
		UserDto dto = new UserDto();
		if(userInfos == null) {
			throw new RuntimeException("회원을 선택해 주세요.");
		}
		
		for(String users : userInfos) {
			dto = userDao.get(Long.parseLong(users));
			
			userDao.remove(dto);
			
		}
	}

}
