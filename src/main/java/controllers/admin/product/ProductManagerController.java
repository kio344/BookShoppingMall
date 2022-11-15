package controllers.admin.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.admin.product.AdminProductDao;
import models.admin.product.service.AdminProductService;
import models.seller.product.ProductRequestDto;

@Controller
@RequestMapping("/admin")
public class ProductManagerController {
	
	@Autowired
	private AdminProductDao productRequestDao;
	
	@Autowired
	private AdminProductService productService;
	
	@GetMapping("/productManager")
	public String productManager(Model model, HttpSession session) {
		
		List<ProductRequestDto> dto = productRequestDao.getProducts();
		model.addAttribute("product", dto);
		model.addAttribute("addCss", new String[] {"/product/adminProduct"});
		model.addAttribute("addJs", new String [] {"/admin/productRequest"});
		return "admin/product/adminProduct";
	}
	
	@PostMapping("/productManager")
	public String product(@RequestParam(value = "mode", required = false) String mode, HttpServletRequest request) {
		
		if(mode.equals("agree")) {
			productService.agree(request);
		}else {
			productService.rejected(request);
		}
		
		return "redirect:/admin/productManager";
	}
	
}
