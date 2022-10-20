package controllers.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDto;
import models.board.service.BoardViewService;

@Controller
@RequestMapping("/board")
public class BoardViewController {
	
	@Autowired
	private BoardViewService boardViewService;
	
	@GetMapping("/view/{id}")
	public String form(@PathVariable(name = "id") Long id, Model model) {
		
		BoardDataDto board = boardViewService.view(id);
		model.addAttribute("boardDataDto", board);
		
		return "board/view";
	}

}
