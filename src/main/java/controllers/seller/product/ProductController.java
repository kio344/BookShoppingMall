package controllers.seller.product;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.product.ProductRequest;
import models.seller.product.ProductSaveService;
import models.user.UserDto;

@Controller
@RequestMapping("/seller")
public class ProductController {
	
	@Autowired
	private ProductSaveService saveService;
	
	@Autowired
	private HttpServletResponse response;

	@GetMapping("/sellerProduct")
	public String product(Model model, HttpSession session) {
		UserDto user = (UserDto)session.getAttribute("user");
		
		ProductRequest productRequest = new ProductRequest();
		model.addAttribute("productRequest", productRequest);
		model.addAttribute("user", user);
		model.addAttribute("addCss", new String [] {"/product/adminProduct"});
		model.addAttribute("addJs", new String [] {"/sellerProduct/product"});
		
		return "seller/sellerProduct";
	}

	@PostMapping("/productRequest")
	public void productRequest(@Valid ProductRequest request, Errors errors, Model model, HttpSession session) throws IOException{
		
		saveService.save(request, session);
		
	}
}
