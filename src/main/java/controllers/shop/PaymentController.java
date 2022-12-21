package controllers.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Util.JmsUtil;
import common.Util.JsonData;
import models.seller.product.ProductRequestDto;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.payment.PaymentRequest;
import models.shop.product.ProductDto;
import models.shop.service.PaymentService;
import models.shop.service.PaymentValidation;
import models.shop.service.ShopService;
import models.shop.service.TossService;
import models.user.service.LoginService;

@Controller
@RequestMapping("/shop")
public class PaymentController {

	@Autowired
	private TossService tossService;

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

	/**
	 * 상품 상세 페이지에서 결제 페이지로 이동
	 * 
	 * @param productNum
	 * @param model
	 * @return
	 */
	public String goPayment(Long productNum, Model model) {

		//결제 정보 관련 설정
		PaymentRequest paymentRequest = paymentService.paymentSetting(productNum);

		//결제할 상품정보 가져오기
		ProductRequestDto product = shopService.getProduct(productNum);

		model.addAttribute("paymentRequest", paymentRequest);
		model.addAttribute("product", product);

		model.addAttribute("addJs", "/shop/payment");
		model.addAttribute("addCss", "/shop/payment");

		return "shop/payment";
	}

	/**
	 * 결제 필수 데이터 검증실패 시 결제페이지로 이동
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public String goPayment(PaymentRequest request, Model model) {

		ProductRequestDto product = shopService.getProduct(request.getProductNum());

		model.addAttribute("paymentRequest", request);
		model.addAttribute("product", product);

		model.addAttribute("addJs", "/shop/payment");
		model.addAttribute("addCss", "/shop/payment");

		return "shop/payment";
	}

	/**
	 * 상품 구매 페이지 이동
	 * 
	 * @param productNum 
	 * @param mode	결제 또는 다른기능
	 * @param model
	 * @return
	 */
	@GetMapping("/payment/{productNum}")
	public String payment(@PathVariable(required = false, name = "productNum") Long productNum,
			@RequestParam(name = "mode") String mode, Model model) {

		switch (mode) {
		case "buy":

			return goPayment(productNum, model);

		default:
			return goPayment(productNum, model);
			
		}


	}

	/**
	 * 결제 검증 실패시 error 표출
	 * 
	 * @param paymentRequest
	 * @param error
	 * @param model
	 * @return
	 */
	@PostMapping("/payment/processErr")
	public String payment(@Valid PaymentRequest paymentRequest, Errors error, Model model) {
		
		//paymentRequest.setAddress();

		return goPayment(paymentRequest, model);
	}

	/**
	 * 결제 검증 진행 (ajex)
	 * 
	 * @param paymentRequest
	 * @param error
	 * @param session
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@PostMapping("/payment/validProcess")
	@ResponseBody
	public ResponseEntity<JsonData<PaymentRequest>> paymentPs(@Valid PaymentRequest paymentRequest, Errors error,
			HttpSession session, HttpServletRequest httpServletRequest, Model model) {

	
		
		JsonData<PaymentRequest> result = new JsonData<>();
		result.setResult(true);

		try {

			validation.validate(paymentRequest, error);

			result.setData(paymentRequest);
			
			return ResponseEntity.ok(result);
			// 토스 결제 진행
		} catch (RuntimeException e) {

			result.setResult(false);
			result.setMessage(e.getMessage());

			return ResponseEntity.ok(result);
			// @PostMapping("/payment/processErr") 진행
		}
	}

	/**
	 * 결제하기 payment 추가 관련
	 * 
	 * 
	 * @param paymentRequest
	 * @return 이 기반으로 tossAPi 호출하여 결제 진행
	 */
	@ResponseBody
	@PostMapping("/payment/process")
	public PaymentDto paymentProcess(PaymentRequest paymentRequest) {
		
		paymentRequest.setAddress();

		return paymentService.paymentProcess(paymentRequest);

	}

	/**
	 * 토스 결제 성공 시 진행상태(Payment:progress) 업데이트, 제품 재고(Product:count)
	 * 판매량(Product:salesRate) 업데이트 tossCallBack 처리
	 * 
	 * @param orderId
	 * @param paymentKey
	 * @param amount
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/sc")
	public String processSc(String orderId, String paymentKey, Long amount, Model model, HttpSession session) {
		String paymentId = orderId.split("__")[1];

		//DB 결제 항목 Progress 결제 완료로 업데이트
		paymentService.updateProgress(Long.parseLong(paymentId), PaymentProgress.PAYMENT_COMPLET, orderId);

		//토스 api 관련 콜백
		tossService.paymentCallBackSc(orderId, paymentKey, amount);

		return shopController.shop(model, session);
	}

	/**
	 * 토스 결제 실패 시 payment 삭제 tossCallBack 처리
	 * 
	 * @param orderId
	 * @param paymentKey
	 * @param amount
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping(produces = "text/html;charset=utf-8", path = "/payment/result/fail")
	public String processFail(String orderId, String paymentKey, Long amount, Model model, HttpSession session) {
		String paymentId = orderId.split("__")[1];

		//DB 결제 항목 제거
		paymentService.removePayment(Long.parseLong(paymentId));
		
		//토스 api 관련 콜백
		tossService.paymentCallBackFail(orderId, paymentKey, amount);

		//쇼핑몰 메인페이지로 이동
		return shopController.shop(model, session);
		
	}
}
