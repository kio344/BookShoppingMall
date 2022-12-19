package controllers.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.tool.schema.JdbcMetadaAccessStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Util.JmsUtil;
import common.Util.JsonData;
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

		List<PaymentDto> result = paymentService.gets(user.getMemNo());
		ProductReviewRequest productReviewRequest = new ProductReviewRequest();

		model.addAttribute("productReview", productReviewRequest);


		model.addAttribute("addCss", new String[] { "/mypage/myOrder" });
		model.addAttribute("addJs", new String[] { "/mypage/ckeditor/ckeditor", "/mypage/myOrder" });
		model.addAttribute("paymentList", result);

		return "mypage/myOrder";
	}

	@PostMapping
	public String myOrderPs(ProductReviewRequest request) {

		ProductReviewDto review = productReviewService.writeReview(request);

		return "mypage/myOrder";
	}

	@PostMapping("/getreview")
	@ResponseBody
	public ProductReviewDto getReview(Long paymentnum) {

		System.out.println(paymentnum);

		ProductReviewDto review = productReviewService.getReivewForPayment(paymentnum);

		return review;
	}
	
	@PostMapping("/paymentcomplete")
	public ResponseEntity<JsonData<PaymentDto>> paymentCompletePs(Long paymentNum) {
		
		PaymentDto paymentDto = paymentService.updateProgress(paymentNum, PaymentProgress.COMPLETED);
		
		JsonData<PaymentDto> result=new JsonData<>();
		
		if (paymentDto==null) {
			result.setResult(false);
			result.setMessage("서비스 담당자에게 문의해주세요");
		}else {
			result.setData(paymentDto);
			result.setResult(true);
		}
		
		
		
		return ResponseEntity.ok().body(result);
	}

}
