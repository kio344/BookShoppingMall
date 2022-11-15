package common.auth;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import models.user.UserDto;
import models.user.UserType;

public class UserTypeCheck implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("user");
		if(user != null) {
			if(user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.SELLER) {
				return true;
			}
		}
		
		PrintWriter out = response.getWriter();
		response.sendRedirect(request.getContextPath());
		
		out.print("<script>alert('안돼');</script>");
		
		return false;
	}
	
	

}
