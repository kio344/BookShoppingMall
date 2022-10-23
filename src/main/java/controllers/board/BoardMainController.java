package controllers.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDto;
import models.board.service.BoardListService;

@Controller
@RequestMapping("/board")
public class BoardMainController {
	
	@Autowired
	private BoardListService boardListService;

	@GetMapping("/{boardId}")
	public String form(@PathVariable(name = "boardId") String boardId , Model model) {
		
		List<BoardDataDto> boards = boardListService.gets(boardId);
		model.addAttribute("boards", boards);
		model.addAttribute("addCss", new String[] {"/board/index"});
		
		return "board/index";
	}
	
}
