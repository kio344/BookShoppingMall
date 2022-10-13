package controllers.test;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.testModel.TestDao;
import models.testModel.TestDto;
import models.testModel.TestRequest;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestDao testDao;

	@GetMapping("/controller")
	public String test(Model model) {
		model.addAttribute("test", "테스트중!!!@!@!@");
		return "test/controller";

	}

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("testRequest", new TestRequest());
		return "test/insert";
	}

	@PostMapping("/insert")
	public String insertPs(Model model, @Valid TestRequest request, Errors errors) {
	

		TestDto dto = new TestDto();
		dto.setMemId(request.getMemId());
		dto.setMemPw(request.getMemPw());
		dto.setMemPwRe(request.getMemPwRe());
		dto.setMemNm(request.getMemNm());

		testDao.insert(dto);
		System.out.println(request);

		return "test/insert";
	}
	
	@GetMapping("/layout")
	public String layout() {
		return "test/mainLayoutTest";
	}
	
}
