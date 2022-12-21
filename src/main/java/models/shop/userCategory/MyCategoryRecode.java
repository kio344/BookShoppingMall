package models.shop.userCategory;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.seller.product.ProductRequestDto;
import models.shop.service.ShopService;
import models.shop.service.UserCategoryService;
import models.user.UserDto;

/**
 * 상품페이지 이동 시 해당 상품의 카테고리를 기록함
 * 
 * 유저별 도서추천 서비스 관련
 * 
 * @author 5563a
 *
 */
public class MyCategoryRecode implements HandlerInterceptor {

	@Autowired
	private ShopService shopService;

	@Autowired
	private UserCategoryService userCategoryService;

	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/** 상품정보 가져오기 S */
		String reqPath = request.getServletPath();
		String[] path = reqPath.split("/");

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		ProductRequestDto product = shopService.getProduct(Long.parseLong(path[path.length - 1]));

		/** 상품정보 가져오기 E */
		
		// 상품 카테고리
		String category = product.getCategory();

		/** 로그인 상태시 카테고리 저장 S */
		UserDto user = (UserDto) session.getAttribute("user");
		
		//비회원 접속 시 종료
		if (user == null) {
			return true;
		}

		//기록된 카테고리 가져오기
		UserCategoryDto myCategory = userCategoryService.get(user.getMemNo());

		//기존 카테고리 기록이 없을경우
		if (myCategory == null) {
			userCategoryService.register(user.getMemNo());
		}

		//현재 보고있는 상품의 카테고리 추가
		userCategoryService.userCategoryUpdate(user.getMemNo(), category);
		/** 로그인 상태시 카테고리 저장 E */


		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
