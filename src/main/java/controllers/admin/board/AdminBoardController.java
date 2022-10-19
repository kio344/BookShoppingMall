package controllers.admin.board;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;
import models.admin.board.AdminBoardRequest;
import models.admin.board.service.AdminBoardDeleteService;
import models.admin.board.service.AdminBoardService;
import models.admin.board.service.AdminBoardUpdateService;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {
	
	@Autowired
	private AdminBoardService adminBoardService;
	
	@Autowired
	private AdminBoardDao adminBoardDao;
	
	@GetMapping("/board")
	public String adminBoard(Model model) {
		AdminBoardRequest adminBoardRequest = new AdminBoardRequest();
		model.addAttribute("adminBoardRequest", adminBoardRequest);
		
		List<AdminBoardDto> list = adminBoardDao.gets();
		
		model.addAttribute("list", list);
		model.addAttribute("addCss", new String[] {"/board/admin/adminBoard"});
		
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
	
	@PostMapping("/list")
	public String adminBoardList(@Valid AdminBoardRequest adminBoardRequest, Model model, @RequestParam(value = "mode", required = false) String mode, @RequestParam(value = "boardId", required = false) String boardId ) {
		
		AdminBoardDto dto = adminBoardDao.searchToId(boardId);
		adminBoardRequest = AdminBoardRequest.toRequest(dto);
		
		if(mode.equals("update")) {
			AdminBoardUpdateService updateService = new AdminBoardUpdateService();
			updateService.update(adminBoardRequest);
		}else if(mode.equals("delete")) {
			AdminBoardDeleteService deleteService = new AdminBoardDeleteService();
			deleteService.delete(adminBoardRequest);
		}
		
		return "redirect:/admin/board";
	}
}






