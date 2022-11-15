package controllers.seller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.seller.edit.SearchService;
import models.seller.product.ProductRequestDto;

@Controller
@RequestMapping("/seller/product")
public class ProductEditController {

	@Autowired
	private SearchService searchService;
	
//	@GetMapping("/edit")
//	public String edit(@RequestParam(required = false, name = "searchType") String searchType, HttpServletRequest request, Model model) {
//			
//			List<ProductRequestDto> list =  searchService.search(searchType, request);
//			
//			model.addAttribute("list", list);
//		
//		return "/seller/editProduct";
//	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(required = false, name = "searchType") String searchType,@RequestParam(required = false, name = "search") String search, Model model) {
		
		List<ProductRequestDto> list =  searchService.search(searchType, search);
		
		model.addAttribute("list", list);
		
		return "/seller/editProduct";
	}
	
}
