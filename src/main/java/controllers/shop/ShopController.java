package controllers.shop;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Util.JmsUtil;
import models.shop.product.ProductDto;
import models.shop.service.ShopService;
import models.shop.userCategory.UserCategoryDto;
import models.user.UserDto;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService service;

	@GetMapping("/index")
	public String shop(Model model, HttpSession session) {
		
		JmsUtil.autoLogin(session);
		
		String[] addJs = new String[] { "/shop/index" };
		String[] addCss = new String[] { "/shop/index" };
		model.addAttribute("addJs", addJs);
		model.addAttribute("addCss", addCss);

		List<ProductDto> newProductList = service.getProducts(0, 5);
		model.addAttribute("newProductList", newProductList);

		List<ProductDto> bestSellerList = service.getBestSeller(0, 5);
		model.addAttribute("bestSellerList", bestSellerList);

		//
		UserCategoryDto userCategoryDto = new UserCategoryDto();

		UserDto user = (UserDto) session.getAttribute("user");

		//
		return "shop/shop";

	}

}
