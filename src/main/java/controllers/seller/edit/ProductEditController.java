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

import models.admin.product.service.AdminProductService;
import models.seller.edit.SearchDao;
import models.seller.edit.SearchService;
import models.seller.product.ProductRequestDto;

@Controller
@RequestMapping("/seller/product")
public class ProductEditController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private AdminProductService productService;
	
	@Autowired
	private SearchDao dao;
	
//	@GetMapping("/editPage")
//	public String editPage(Model model) {
//			
//		List<ProductRequestDto> list = dao.gets();
//		model.addAttribute("list", list);
//		
//		return "/seller/editProduct";
//	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(required = false, name = "searchType" , defaultValue = "") String searchType, @RequestParam(required = false, name = "search",defaultValue = "") String search, Model model) {
			
		List<ProductRequestDto> list = dao.gets();
			model.addAttribute("list", list);
			
		if(!searchType.isBlank()) {
			List<ProductRequestDto> item = searchService.search(searchType, search);
			model.addAttribute("list", item);
		}
			
		return "/seller/editProduct";
	}
	
	@PostMapping("/update")
	public String remove(@RequestParam(required = false, name = "mode")String mode, HttpServletRequest request) {
			
		if(mode.equals("update")) {
			productService.update(request);
		}else {
			productService.remove(request);
		}
		
		return "redirect:/seller/product/edit";
	}
	
}