package controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.page.Pagination;
import common.page.Pagination_v2;
import models.seller.product.ProductRequestDto;
import models.shop.product.ProductDto;
import models.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopSearchController {

	@Autowired
	private ShopService shopService;

	@GetMapping("/search")
	public String searchPs(String searchValue, @RequestParam(defaultValue = "", required = false) String searchType,
			@RequestParam(defaultValue = "1", required = false) int page, Model model) {
		System.out.println("페이지 : " + page);
		int total = shopService.getSearchProductsCount(searchValue, searchType);
		int limit = 10;
		String link = "/shop/search?searchValue=" + searchValue + "&searchType=" + searchType + "&page=";
		Pagination_v2 pagination = new Pagination_v2(page, total, 0, limit, link);
		List<ProductRequestDto> searchResult = shopService.getSearchProducts((page - 1) * limit, limit, searchValue,
				searchType);

		model.addAttribute("addCss", new String[] { "/shop/searchProduct" });
		model.addAttribute("addJs", new String[] { "/shop/searchProduct","/common/kakaoShare" });

		model.addAttribute("searchResult", searchResult);
		model.addAttribute("pagination", pagination);

		System.out.println(pagination);

		return "shop/searchProduct";
	}

}
