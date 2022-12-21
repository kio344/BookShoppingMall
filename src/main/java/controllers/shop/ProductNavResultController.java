package controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.product.ProductRequestDto;
import models.shop.service.ShopService;

/**
 * 쇼핑몰 메인페이지 상품 네비게이션 관련
 *  
 * @author 5563a
 *	
 */
@Controller
@RequestMapping("/shop/productNav")
public class ProductNavResultController {

	@Autowired
	private ShopService shopService;

	/**
	 * 
	 * 상품 네비게이션 검색결과
	 * 
	 * @param cat1 네비게이션 항목1
	 * @param cat2 네비게이션 항목2
	 * @param cat3 네비게이션 항목3
	 * @param model
	 * @return
	 */
	@GetMapping
	public String result(String cat1, String cat2, String cat3, Model model) {

		String searchVal = "";
		if (!(cat1 == null || cat1.isBlank())) {
			searchVal += cat1;
		}

		if (!(cat2 == null || cat2.isBlank())) {
			searchVal += "/" + cat2;
		}

		if (!(cat3 == null || cat3.isBlank())) {
			searchVal += "/" + cat3;
		}

		List<ProductRequestDto> productList = shopService.getSearchProducts(0, 9999, searchVal, "category");

		model.addAttribute("productList", productList);

		return "shop/productNavResult";
	}
}
