package controllers.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Util.JmsUtil;
import common.Util.JsonData;
import common.page.Pagination_v2;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.productReview.ProductReviewDto;
import models.shop.productReview.ProductReviewRequest;
import models.shop.service.PaymentService;
import models.shop.service.ProductReviewService;
import models.user.UserDto;

@Controller
@RequestMapping("/mypage/myorder")
public class MyOrderController {
	private int offset=5;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductReviewService productReviewService;

	@GetMapping
	public String myOrder(HttpSession session, Model model,@RequestParam(defaultValue = "1")int page) {

		System.out.println(page);
		
		UserDto user = JmsUtil.getLoginUser(session);

		List<PaymentDto> result = paymentService.gets(user.getMemNo(),page,offset);
		
		int paymentC=(int) paymentService.getsC(user.getMemNo());
		
		ProductReviewRequest productReviewRequest = new ProductReviewRequest();

		model.addAttribute("productReview", productReviewRequest);
		
		Pagination_v2 pagination=new Pagination_v2(page,paymentC,paymentC/offset,offset,"?page="); 
		model.addAttribute("pagination", pagination);
		
		model.addAttribute("addCss", new String[] { "/mypage/myOrder" });
		model.addAttribute("addJs", new String[] { "/common/kakaoShare","/mypage/ckeditor/ckeditor", "/mypage/myOrder" });
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
	
	
	/**
	 * 수취 완료
	 * @param paymentNum
	 * @return
	 */
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
