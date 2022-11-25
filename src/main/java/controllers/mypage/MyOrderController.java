package controllers.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.tool.schema.JdbcMetadaAccessStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Util.JmsUtil;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.productReview.ProductReviewDto;
import models.shop.productReview.ProductReviewRequest;
import models.shop.service.PaymentService;
import models.shop.service.ProductReviewService;
import models.user.UserDao;
import models.user.UserDto;

@Controller
@RequestMapping("/mypage/myorder")
public class MyOrderController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductReviewService productReviewService;

	
	
	@GetMapping
	public String myOrder(HttpSession session, Model model) {

		JmsUtil.autoLogin(session);
		
		UserDto user = JmsUtil.getLoginUser(session);

		List<PaymentDto> result= paymentService.gets(user.getMemNo(), PaymentProgress.PAYMENT_COMPLET);
		ProductReviewRequest productReviewRequest=new ProductReviewRequest();
		
		model.addAttribute("productReview", productReviewRequest);
		
		System.out.println(result.get(0));
		
		model.addAttribute("addCss",new String[] {"/mypage/myOrder"});
		model.addAttribute("addJs",new String[] {"/mypage/ckeditor/ckeditor","/mypage/myOrder"});
		model.addAttribute("paymentList", result);
		
		return "mypage/myOrder";
	}
	
	@PostMapping
	public String myOrderPs(ProductReviewRequest request) {
		
		System.out.println(request);
		
		System.out.println(productReviewService.writeReview(request));
		
		
		return "mypage/myOrder";
	}
	
	@PostMapping("/getreview")
	@ResponseBody
	public ProductReviewDto getReview(Long paymentnum) {
		
		System.out.println(paymentnum);
		
		ProductReviewDto review = productReviewService.getReivewForPayment(paymentnum);
		
		
		
		return review;
	}

}
