package controllers.shop;

import java.io.IOException;

import static common.Util.JmsUtil.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.mysql.cj.Session;

import common.Util.JsonData;
import models.mypage.service.UserInfoService;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;
import models.shop.product.ProductDto;
import models.shop.service.PaymentService;
import models.shop.service.PaymentValidation;
import models.shop.service.ShopService;
import models.shop.toss.TossData;
import models.shop.toss.TossPayment;
import models.shop.toss.TossResult;
import models.user.UserDao;
import models.user.UserDto;
import models.user.UserType;
import models.user.service.LoginService;

@Controller
@RequestMapping("/shop")
public class PaymentController {

	@Autowired 
	private PaymentValidation validation;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ShopController shopController;

	public String goPayment(Long productNum, Model model) {

		PaymentRequest paymentRequest = paymentService.paymentSetting(productNum);

		ProductDto product = shopService.getProduct(productNum);

		model.addAttribute("paymentRequest", paymentRequest);
		model.addAttribute("product", product);

		model.addAttribute("addJs", "/shop/payment");
		model.addAttribute("addCss", "/shop/payment");

		return "shop/payment";
	}

	public String goPayment(PaymentRequest request, Model model) {

		ProductDto product = shopService.getProduct(request.getProductNum());

		model.addAttribute("paymentRequest", request);
		model.addAttribute("product", product);

		model.addAttribute("addJs", "/shop/payment");
		model.addAttribute("addCss", "/shop/payment");

		return "shop/payment";
	}

	/** 구매하기 or 장바구니 */
	@GetMapping("/payment/{productNum}")
	public String payment(@PathVariable(required = false, name = "productNum") Long productNum,
			@RequestParam(name = "mode") String mode, Model model) {

		switch (mode) {
		case "buy":

			return goPayment(productNum, model);

		case "addCart":

			return "";

		}

		return "";

	}

	@PostMapping("/payment/processErr")
	public String payment(@Valid PaymentRequest paymentRequest, Errors error, Model model) {
		PaymentRequest request = paymentRequest;
		request.setAddress();

		return goPayment(request, model);
	}

	/** 결제 검증 진행 */
	@PostMapping("/payment/validProcess")
	@ResponseBody
	public ResponseEntity<JsonData<PaymentRequest>> paymentPs(@Valid PaymentRequest paymentRequest, Errors error,
			HttpSession session, HttpServletRequest httpServletRequest, Model model) {

		JsonData<PaymentRequest> result = new JsonData<>();
		result.setResult(true);

		try {
			
			validation.validate(paymentRequest, error);

			result.setData(paymentRequest);
			
		} catch (RuntimeException e) {
			
			result.setResult(false);
			result.setMessage(e.getMessage());

			return ResponseEntity.ok(result);
		}
		return ResponseEntity.ok(result);
	}

	@ResponseBody
	@PostMapping("/payment/process")
	public PaymentDto paymentProcess(PaymentRequest paymentRequest) {
		paymentRequest.setAddress();
		System.out.println(paymentRequest);

		return paymentService.paymentProcess(paymentRequest);

	}

	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/sc")
	public String processSc(String orderId, String paymentKey, Long amount, Model model, HttpSession session) {
		String paymentId = orderId.split("__")[1];
		paymentService.updateProgress(Long.parseLong(paymentId), PaymentProgress.PAYMENT_COMPLET,orderId);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
				.header("Authorization", "Basic dGVzdF9za19PNkJZcTdHV1BWdk5sNW9kRFFtVk5FNXZibzFkOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentKey + "\",\"amount\":"
						+ amount + ",\"orderId\":\"" + orderId + "\"}"))
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.body());

		return shopController.shop(model, session);
	}

	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/fail")
	public String processFail(String orderId, String paymentKey, Long amount, Model model, HttpSession session) {
		String paymentId = orderId.split("__")[1];

		paymentService.removePayment(Long.parseLong(paymentId));

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
				.header("Authorization", "Basic dGVzdF9za19PNkJZcTdHV1BWdk5sNW9kRFFtVk5FNXZibzFkOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentKey + "\",\"amount\":"
						+ amount + ",\"orderId\":\"" + orderId + "\"}"))
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.body());

		return shopController.shop(model, session);
	}
}
