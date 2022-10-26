package controllers.board;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.error.CommonException;
import models.board.BoardDataDto;
import models.board.BoardPrivateRequest;
import models.board.service.BoardPrivateService;

@Controller
@RequestMapping("/board")
public class BoardPrivateController {
	
	@Autowired
	private BoardPrivateService boardPrivateService;

	@GetMapping("/private/{id}")
	public String form(@PathVariable(name = "id") Long id, Model model) {
		
		BoardPrivateRequest boardPrivateRequest = new BoardPrivateRequest();
		boardPrivateRequest.setId(id);
		model.addAttribute("boardPrivateRequest", boardPrivateRequest);
		
		return "board/private";
	}
	
	@PostMapping("/private")
	public String process(@Valid BoardPrivateRequest boardPrivateRequest, Errors errors) {
		
		if(errors.hasErrors()) {
			return "board/private";
		}
		BoardDataDto board = null;
		try {
			board = boardPrivateService.sucess(boardPrivateRequest);
		} catch(CommonException e) {
			errors.rejectValue("password", e.getMessage());
			return "board/private";
		}
		
		return "redirect:/board/view/" + board.getId();
	}
}
