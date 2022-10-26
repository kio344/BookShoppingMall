package common.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.user.UserDto;
import models.user.UserType;

public class BoardPrivateCheck implements HandlerInterceptor {
	
	@Autowired
	private BoardDataDao boardDataDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		String _id = request.getRequestURI();
		
		String[] ids = _id.split("/");
		String a = ids[ids.length - 1];
		
		Long id = Long.parseLong(a);
		BoardDataDto board = boardDataDao.get(id);
		
		if(!board.isPrivate()) {
			return true;
		}
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("board" + board.getId())) {
				return true;
			}
		}
		
		if(user.getUserType() == UserType.ADMIN || user.getMemNo() == board.getUser().getMemNo()) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/board/private/" + board.getId());
		
		return false;
	}

}
