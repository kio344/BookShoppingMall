package controllers.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import models.shop.PaymentRequest;
import models.shop.ProductDao;
import models.shop.ProductDto;
import models.shop.service.ShopService;
import models.user.UserDto;
import models.user.UserRequest;
import models.user.UserType;

@Controller
@RequestMapping("/shop")
public class PaymentController {

	@Autowired
	private ShopService shopService;

	@GetMapping("/payment/{productNum}")
	public String payment(@PathVariable(required = false, name = "productNum") Long productNum,
			@RequestParam(name = "count", required = false, defaultValue = "123") int count,
			@RequestParam(name = "mode") String mode
			,Model model,HttpSession session
			) {

		ProductDto product=shopService.getProduct(productNum);
		
		UserDto user=(UserDto)session.getAttribute("user");
		
		PaymentRequest request=new PaymentRequest();
		
		
		if (user==null) {
			user=new UserDto();
			user.setMemId("5563a");
			user.setEmail("jmspon33@gmail.com");
			user.setMemNo(30L);
			user.setMobile("01075175563");
			user.setMemNm("정민상");
			user.setFakeName("KING");
			user.setUserType(UserType.USER);
			

			session.setAttribute("user", user);
		}
		

		
		switch (mode) {
		case "buy":
			System.out.println("구매");
			
			model.addAttribute("addCss", new String[] {"/shop/payment"});
			model.addAttribute("addJs", new String[] {"/shop/payment"});
			model.addAttribute("request",request);
			model.addAttribute("product",product);
			
			return "shop/payment";
			
		case "addCart":
			System.out.println("판매");
			return "shop/shop";

		default:
			break;
		}

		return "shop/payment";
		
	}
	
	@ResponseBody
	@PostMapping("/payment/process")
	public ResponseEntity<PaymentRequest> paymentPs(PaymentRequest request,HttpSession session) {
		
		UserDto user=(UserDto)session.getAttribute("user");

		
		request.setUserNum(user.getMemNo());


		
		
		System.out.println(request);
		
		
		
		return ResponseEntity.ok(request);
	}
}

