package controllers.shop;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;
import models.shop.product.ProductDto;
import models.shop.service.PaymentService;
import models.shop.service.ShopService;
import models.shop.toss.TossData;
import models.shop.toss.TossPayment;
import models.shop.toss.TossResult;
import models.user.UserDto;
import models.user.UserType;

@Controller
@RequestMapping("/shop")
public class PaymentController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private PaymentService paymentService;

	

	@GetMapping("/testpayment/faile")
	@ResponseBody
	public String testFaile() {

		System.out.println("결제실패");

		return "fail";
	}

	/** 구매하기 눌렀을때? 결제 페이지로 이동 */
	@GetMapping("/payment/{productNum}")
	public String payment(@PathVariable(required = false, name = "productNum") Long productNum,
			@RequestParam(name = "mode") String mode, Model model, HttpSession session) {

		PaymentRequest request = new PaymentRequest();
		request.setZipCode("10506");
		request.setRoadAddress("경기 고양시 덕양구 호수로 6");
		request.setDetailAddress("상세주소");
		request.setReqAddress("참고항목");
		model.addAttribute("paymentRequest", request);

		return _payment(productNum, mode, model, session);

	}

	/**
	 * @GetMapping("/payment/{productNum}") 쪽 메서드 : 결제 페이지로 이동, 결제 실패시(hasErrors) 다시
	 * 결제페이지로 이동
	 */
	private String _payment(Long productNum, String mode, Model model, HttpSession session) {

		ProductDto product = shopService.getProduct(productNum);

		autoLogin(session);

		switch (mode) {
		case "buy":
			System.out.println("구매");

			model.addAttribute("addCss", new String[] { "/shop/payment" });
			model.addAttribute("addJs", new String[] { "/shop/payment" });
			model.addAttribute("product", product);

			return "shop/payment";

		case "addCart":
			System.out.println("장바구니에 추가");
			return "shop/shop";

		default:
			break;
		}

		return "shop/payment";
	}

	/** 결제 진행 */
	@PostMapping("/payment/process")
	@ResponseBody
	public PaymentDto paymentPs2(@Valid PaymentRequest request, Errors error, HttpSession session,
			HttpServletRequest httpServletRequest, Model model) {

		System.out.println(request);

		UserDto user = (UserDto) session.getAttribute("user");

		ProductDto productDto = shopService.getProduct(request.getProductNum());
		request.setUserNum(user.getMemNo());
		request.setAddress();
		
		

		
		return 	paymentService.paymentProcess(request);

		
	}



	/** 자동로그인 */
	private void autoLogin(HttpSession session) {
		/** 로그인 귀찮을때 S */
		UserDto user = (UserDto) session.getAttribute("user");

		if (user == null) {
			user = new UserDto();
			user.setMemId("5563a");
			user.setEmail("jmspon33@gmail.com");
			user.setMemNo(30L);
			user.setMobile("01075175563");
			user.setMemNm("정민상");
			user.setFakeName("KING");
			user.setUserType(UserType.USER);

			session.setAttribute("user", user);
		}
		/** 로그인 귀찮을때 E */

	}

	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/sc")
	public String processSc(String orderId,String paymentKey,Long amount) {
		String paymentId=orderId.split("__")[1];
		paymentService.updateProgress(Long.parseLong(paymentId), PaymentProgress.PAYMENT_COMPLET);

		return "shop/shop";

	}

	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/fail")
	public String processFail(String orderId,String paymentKey,Long amount) {
		String paymentId=orderId.split("__")[1];
		
		paymentService.removePayment(Long.parseLong(paymentId));
		
		return "shop/shop";
	}
}
