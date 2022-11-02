package controllers.seller.product;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import models.seller.product.ProductRequest;
import models.seller.product.ProductSaveService;
import models.user.UserDto;

@Controller
@RequestMapping("/seller")
public class ProductController {
	
	@Autowired
	private ProductSaveService saveService;

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
	public String productRequest(@Valid ProductRequest request, Errors errors, Model model, MultipartFile image) throws IOException{
		saveService.save(request);
		
		return "redirect:/seller/sellerProduct";
	}
	
	@PostMapping("/productImage")
	public void file(MultipartFile image) {
		
		saveService.saveImage(image);
		
	}
	
}
