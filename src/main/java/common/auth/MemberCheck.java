package common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import models.user.UserDto;

public class MemberCheck implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("user");
		if(user != null) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/user/login");
		
		return false;
	}

}
