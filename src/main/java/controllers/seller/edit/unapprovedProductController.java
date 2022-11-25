package controllers.seller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.page.Pagination_v2;
import models.admin.product.service.AdminProductService;
import models.seller.edit.SearchDao;
import models.seller.edit.SearchService;
import models.seller.product.ProductRequestDto;

@Controller
@RequestMapping("/seller/unapproved")
public class unapprovedProductController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private AdminProductService productService;
	
	@Autowired
	private SearchDao dao;
	
	@GetMapping("/edit")
	public String unapprovedEdit(@RequestParam(required = false, name = "searchType", defaultValue = "bookName") String searchType,
			@RequestParam(required = false, name = "search", defaultValue = "") String search, @RequestParam(defaultValue = "1", required = false)int page, Model model) {
		
		List<ProductRequestDto> ProductRequestList = dao.gets();
		
		int total = searchService.total(search, searchType);
		int limit = 5;
		String link = "/seller/unapproved/edit?serach=" + search + "&searchType=" + searchType + "&page=";
		Pagination_v2 pagination = new Pagination_v2(page, total, 0, limit, link);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("list", ProductRequestList);
		model.addAttribute("addCss", new String[] { "/seller/product/sellerEdit" });
		
		if (!searchType.isBlank()) {
			List<ProductRequestDto> item = searchService.search(searchType, search, (page - 1) * limit, limit);
			
			model.addAttribute("list", item);
		}
		return "/seller/editProduct";
	}
	
	@PostMapping("/update")
	public String remove(@RequestParam(required = false, name = "mode") String mode, HttpServletRequest request) {
		
		if (mode.equals("update")) {
			productService.update(request);
		} else {
			productService.remove(request);
		}
		return "redirect:/seller/unapproved/edit";
	}
	
}
