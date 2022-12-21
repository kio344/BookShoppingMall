package controllers.shop;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Util.JmsUtil;
import models.seller.product.ProductRequestDto;
import models.shop.service.ShopService;
import models.shop.service.UserCategoryService;
import models.user.UserDto;

/**
 * 쇼핑몰 메인
 * @author 5563a
 *
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private UserCategoryService userCategoryService;

	@Autowired
	private ShopService service;

	/**
	 * 쇼핑몰 메인페이지
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String shop(Model model, HttpSession session) {

		String[] addJs = new String[] { "/shop/index" };
		String[] addCss = new String[] { "/shop/index" };
		model.addAttribute("addJs", addJs);
		model.addAttribute("addCss", addCss);

		// 상품 세팅
		ProductSetting(model, session);

		return "shop/shop";

	}

	/**
	 * 진열된 상품 세팅
	 * 
	 * 입고된 상품 베스트셀러 추천 도서(로그인)
	 * 
	 * @param model
	 * @param session
	 */
	private void ProductSetting(Model model, HttpSession session) {

		UserDto user = JmsUtil.getLoginUser(session);

		List<ProductRequestDto> newProductList = service.getProducts(0, 5);
		model.addAttribute("newProductList", newProductList);

		List<ProductRequestDto> bestSellerList = service.getBestSeller(0, 5);
		model.addAttribute("bestSellerList", bestSellerList);

		if (user != null) {
			List<ProductRequestDto> myShopList = userCategoryService.getMyShopProduct(user.getMemNo());
			model.addAttribute("myShopList", myShopList);
		}

	}

}
