package controllers.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/board")
public class AdminBoardController {

	@GetMapping
	public String adminBoard() {
		
		return "admin/board/adminBoard";
	}
	
	@PostMapping
	public String adminBoardEx() {
		
		return null;
	}
}
