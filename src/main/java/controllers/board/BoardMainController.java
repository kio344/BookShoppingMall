package controllers.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardMainController {

	@GetMapping
	public String form(Model model) {
		
		model.addAttribute("addCss", new String[] {"/board/index"});
		
		return "board/index";
	}
	
	@GetMapping("/write")
	public String aa(Model model) {
		
		model.addAttribute("addCss", new String[] { "/board/write" });
		model.addAttribute("addJs", new String[] { "/ckeditor/ckeditor", "/board/board" });
		
		return "board/write";
	}
	
}
