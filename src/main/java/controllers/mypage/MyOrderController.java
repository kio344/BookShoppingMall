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
	
	//페이지네이션
	private int offset = 5;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductReviewService productReviewService;

	/**
	 * 주문한상품 페이지
	 * 
	 * @param session
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping
	public String myOrder(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
		ProductReviewRequest productReviewRequest = new ProductReviewRequest();

		UserDto user = JmsUtil.getLoginUser(session);

		//내가 주문한 상품들 가져오기
		List<PaymentDto> result = paymentService.gets(user.getMemNo(), (page - 1) * offset, offset);

		//주문한 상품 수량
		int paymentC = (int) paymentService.getsC(user.getMemNo());

		Pagination_v2 pagination = new Pagination_v2(page, paymentC, paymentC / offset, offset, "?page=");


		model.addAttribute("productReview", productReviewRequest);
		model.addAttribute("pagination", pagination);
		model.addAttribute("paymentList", result);
		model.addAttribute("addCss", new String[] { "/mypage/myOrder" });
		model.addAttribute("addJs",
				new String[] { "/common/kakaoShare", "/mypage/ckeditor/ckeditor", "/mypage/myOrder" });

		return "mypage/myOrder";
	}

	/**
	 * 리뷰 작성, 업데이트처리
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public ProductReviewDto myOrderPs(ProductReviewRequest request) {

		ProductReviewDto review = productReviewService.writeReview(request);

		return review;
	}

	/**
	 * 리뷰 불러오기 리뷰 작성하기 버튼 누르면 해당 내용 요청받아서 ckeditor 로 뿌려줌
	 * 
	 * @param paymentnum
	 * @return
	 */
	@PostMapping("/getreview")
	@ResponseBody
	public ProductReviewDto getReview(Long paymentnum) {

		ProductReviewDto review = productReviewService.getReivewForPayment(paymentnum);

		return review;
	}

	/**
	 * 수취 완료 처리
	 * 
	 * @param paymentNum
	 * @return
	 */
	@PostMapping("/paymentcomplete")
	public ResponseEntity<JsonData<PaymentDto>> paymentCompletePs(Long paymentNum) {

		PaymentDto paymentDto = paymentService.updateProgress(paymentNum, PaymentProgress.COMPLETED);

		JsonData<PaymentDto> result = new JsonData<>();

		if (paymentDto == null) {
			result.setResult(false);
			result.setMessage("서비스 담당자에게 문의해주세요");
		} else {
			result.setData(paymentDto);
			result.setResult(true);
		}

		return ResponseEntity.ok().body(result);
	}

}
