package controllers.admin.board;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.admin.board.AdminBoardRequest;
import models.admin.board.service.AdminBoardService;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService adminBoardService;
	
	@GetMapping("/board")
	public String adminBoard(Model model) {
		AdminBoardRequest adminBoardRequest = new AdminBoardRequest();
		
		model.addAttribute("adminBoardRequest", adminBoardRequest);
		
		return "admin/board/adminBoard";
	}
	
	@PostMapping("/board")
	public String adminBoardWrite(@Valid AdminBoardRequest adminBoardRequest, Errors errors) {
		
		if (errors.hasErrors()) {
			return "admin/board/adminBoard";
		}
		
		adminBoardService.write(adminBoardRequest, errors);
		
		if(errors.hasErrors()) {
			return "admin/board/adminBoard";
		}
		
		return "redirect:/admin/board";
	}
}






