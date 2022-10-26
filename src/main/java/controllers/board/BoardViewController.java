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
		
		BoardDataDto boardDataDto = boardViewService.view(id, model);
		model.addAttribute("boardDataDto", boardDataDto);
		model.addAttribute("addCss", new String[] { "/board/view" });
		model.addAttribute("addJs", new String[] { "/board/filelist", "/board/comment" });
		
		return "board/view";
	}

}
