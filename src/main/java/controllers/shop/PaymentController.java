package controllers.shop;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.shop.PaymentRequest;
import models.shop.ProductDto;
import models.shop.TossData;
import models.shop.TossResult;
import models.shop.service.PaymentService;
import models.shop.service.ShopService;
import models.user.UserDto;
import models.user.UserType;

@Controller
@RequestMapping("/shop")
public class PaymentController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payment/{productNum}")
	public String payment(@PathVariable(required = false, name = "productNum") Long productNum,
			@RequestParam(name = "count", required = false, defaultValue = "123") int count,
			@RequestParam(name = "mode") String mode, Model model, HttpSession session) {

		ProductDto product = shopService.getProduct(productNum);

		UserDto user = (UserDto) session.getAttribute("user");

		PaymentRequest request = new PaymentRequest();

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

		switch (mode) {
		case "buy":
			System.out.println("구매");

			model.addAttribute("addCss", new String[] { "/shop/payment" });
			model.addAttribute("addJs", new String[] { "/shop/payment" });
			model.addAttribute("request", request);
			model.addAttribute("product", product);

			return "shop/payment";

		case "addCart":
			System.out.println("판매");
			return "shop/shop";

		default:
			break;
		}

		return "shop/payment";

	}

	
	@PostMapping("/payment/process")
	public String paymentPs(PaymentRequest request, HttpSession session) {

		UserDto user = (UserDto) session.getAttribute("user");

		ProductDto productDto=shopService.getProduct(request.getProductNum());
		request.setUserNum(user.getMemNo());

		///////

		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		try {
			url = new URL("https://pay.toss.im/api/v2/payments");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			org.json.simple.JSONObject jsonBody = new JSONObject();
			jsonBody.put("orderNo", System.currentTimeMillis());
			jsonBody.put("amount", request.getCount()*productDto.getPrice());
			jsonBody.put("amountTaxFree", 0);
			jsonBody.put("productDesc",productDto.getBookName());
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");
			jsonBody.put("autoExecute", false);
			jsonBody.put("retUrl", "http://localhost:3000/BookShoppingMall/shop/payment/sucess");
			jsonBody.put("retCancelUrl", "http://localhost:3000/BookShoppingMall/shop/payment/fail");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println(responseBody.toString());
		///////
		ObjectMapper objectMapper=new ObjectMapper();
		TossData tossData=null;
		try {
			tossData=objectMapper.readValue(responseBody.toString(), TossData.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:"+tossData.getCheckoutPage();
	}
	
	@ResponseBody
	@PostMapping("/payment/result")
	public TossResult processRs(@RequestBody TossResult resultCallback) {
		
		
		return resultCallback;
		
		
	}
	
	@ResponseBody
	@GetMapping(produces = "text/html;charset=utf-8",path = "/payment/sucess")
	public String processSc() {
		return "<script>alert('결제완료');parent.location.replace('http://localhost:3000/BookShoppingMall/shop/index')</script> ";
		
	}
	
	@ResponseBody
	@GetMapping(produces = "text/html;charset=utf-8",path = "/payment/fail")
	public String processFail() {
		return "<script>alert('오류발생');parent.location.replace('http://localhost:3000/BookShoppingMall/shop/index')</script> ";
	}
}
