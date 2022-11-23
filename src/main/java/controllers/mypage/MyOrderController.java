package controllers.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Util.JmsUtil;
import models.shop.payment.PaymentDao;
import models.shop.payment.PaymentDto;
import models.shop.payment.PaymentProgress;
import models.shop.service.PaymentService;
import models.user.UserDao;
import models.user.UserDto;

@Controller
@RequestMapping("/mypage/myorder")
public class MyOrderController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public String myOrder(HttpSession session, Model model) {
		JmsUtil.autoLogin(session);

		UserDto user = JmsUtil.getLoginUser(session);

		List<PaymentDto> result= paymentService.gets(user.getMemNo(), PaymentProgress.PAYMENT_COMPLET);
		
		model.addAttribute("addCss",new String[] {"/shop/myOrder"});
		model.addAttribute("paymentList", result);
		
		return "mypage/myOrder";
	}

}
