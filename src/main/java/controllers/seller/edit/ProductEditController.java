package controllers.seller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.admin.product.service.AdminProductService;
import models.seller.edit.SearchDao;
import models.seller.edit.SearchService;
import models.seller.product.ProductRequestDto;

@Controller
public class ProductEditController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private AdminProductService productService;

	@Autowired
	private SearchDao dao;

	@RequestMapping("/seller/product/edit")
	public String edit(@RequestParam(required = false, name = "searchType", defaultValue = "") String searchType,
			@RequestParam(required = false, name = "search", defaultValue = "") String search, Model model) {
		
		List<ProductRequestDto> ProductRequestList = dao.gets();
		
		model.addAttribute("list", ProductRequestList);
		model.addAttribute("addCss", new String[] { "/seller/product/sellerEdit" });
		
		if (!searchType.isBlank()) {
			List<ProductRequestDto> item = searchService.search(searchType, search);
			model.addAttribute("list", item);
		}
		return "/seller/editAgreeProduct";
	}
	
	@RequestMapping("/seller/product/update")
	public String remove(@RequestParam(required = false, name = "mode") String mode, HttpServletRequest request) {
		
		if (mode.equals("update")) {
			productService.update(request);
		} else {
			productService.remove(request);
		}
		return "redirect:/seller/product/edit";
	}
}
