package controllers.seller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.seller.product.ProductRequestDao;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;

@Controller
@RequestMapping("/seller/product")
public class OrderManagerController {
	
	@Autowired
	private ProductRequestDao productRequestDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@GetMapping("/orderManager")
	public String productManager(Model model) {
		
		List<PaymentDto> list = paymentDao.getCompleted();
		model.addAttribute("addCss", new String[] {"/seller/payment/payment"});
		model.addAttribute("list", list);
		
		return "/seller/orderManager";
	}
	
	@PostMapping
	public String productProcessing(@RequestParam(name = "mode")String mode) {
		
		if(mode.equals("ship")) {
			
		}else {
			
		}
		
		return "";
	}
	
}
