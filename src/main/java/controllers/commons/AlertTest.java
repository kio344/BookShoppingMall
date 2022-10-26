package controllers.commons;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seller/productRequest/alert")
public class AlertTest {
		/** 테스트 중 */
	@GetMapping(produces = "text/html; charset=utf-8")
	public String alert(HttpServletRequest req) {
		
		String path = "http://localhost:3000" + req.getContextPath() + "/seller/sellerProduct";
		
		return "<script>alert('요청이 완료 되었습니다.'); parent.location.replace('" + path + "');</script>";
	}
	/** 테스트 중 */	
}
