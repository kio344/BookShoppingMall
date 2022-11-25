package models.shop.userCategory;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.seller.product.ProductRequestDto;
import models.shop.product.ProductDto;
import models.shop.service.ShopService;
import models.shop.service.UserCategoryService;
import models.user.UserDto;

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
		// TODO Auto-generated method stub
		String reqPath = request.getServletPath();
		String[] path = reqPath.split("/");

		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		System.out.println(Arrays.toString(path));

		ProductRequestDto product = shopService.getProduct(Long.parseLong(path[path.length - 1]));

		String category = product.getCategory();
		
		UserDto user=(UserDto)session.getAttribute("user"); 
		
		if (user==null) {
			return true;
		}
		
		UserCategoryDto myCategory=userCategoryService.get(user.getMemNo());
		
		if (myCategory==null) {
			userCategoryService.register(user.getMemNo());
		}

		userCategoryService.userCategoryUpdate(user.getMemNo(), category);
		

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
