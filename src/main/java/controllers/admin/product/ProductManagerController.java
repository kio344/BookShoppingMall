package controllers.admin.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.admin.product.AdminProductDao;
import models.seller.product.ProductDto;

@Controller
@RequestMapping("/admin")
public class ProductManagerController {
	
	@Autowired
	private AdminProductDao productDao;
	
	@GetMapping("/productManager")
	public String productManager(Model model) {
		
		List<ProductDto> dto = productDao.getProducts();
		System.out.println("----------------------------------------- productDto :" +dto);
		model.addAttribute("product", dto);
		model.addAttribute("addCss", new String[] {"/product/adminProduct.css"});
		return "admin/product/adminProduct";
	}
	
	@PostMapping("/productManager")
	public String product() {
		
		
		
		return "admin/product/adminProduct";
	}
	
}
