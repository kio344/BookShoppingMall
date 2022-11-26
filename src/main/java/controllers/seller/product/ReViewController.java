package controllers.seller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.seller.edit.SearchDao;
import models.seller.product.ProductRequestDto;

@Controller
@RequestMapping("/seller/review")
public class ReViewController {

	@Autowired
	private SearchDao searchDao;
	
	@GetMapping
	public String review(Model model) {
		List<ProductRequestDto> list = searchDao.gets();
		
		model.addAttribute("list", list);
		
		return "/seller/review";
	}
	
	@GetMapping("/{productNum}")
	public String reviewPage(@PathVariable(name = "productNum")long productNum, Model model) {
		
		
		
		return "/seller/reviewProduct";
	}
}
