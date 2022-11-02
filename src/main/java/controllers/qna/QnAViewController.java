package controllers.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.qna.QnADao;
import models.qna.QnADto;

@Controller
@RequestMapping("/QnA")
public class QnAViewController {
	
	@Autowired
	private QnADao dao;
	
	@GetMapping("/view/{id}")
	public String form(@PathVariable(name = "id") Long id, Model model) {
		
		QnADto dto = dao.get(id);
		
		model.addAttribute("qnADto", dto);
		model.addAttribute("addCss", new String[] { "/qna/view" });
		
		return "qna/view";
	}

}
