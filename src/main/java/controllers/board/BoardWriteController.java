package controllers.board;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDto;
import models.board.BoardDataRequest;
import models.board.service.BoardWriteService;
import models.user.UserDto;

@Controller
@RequestMapping("/board")
public class BoardWriteController {
	
	@Autowired
	private BoardWriteService service;
	
	@GetMapping("/write/{boardId}")
	public String form(@PathVariable(name = "boardId") String boardId, Model model, HttpSession session) {
		model.addAttribute("addCss", new String[] { "/board/write" });
		model.addAttribute("addJs", new String[] { "/ckeditor/ckeditor", "/board/board", "/file/fileupload" });
		
		BoardDataRequest boardDataRequest = new BoardDataRequest();
		UserDto user = (UserDto)session.getAttribute("user");
		if(user != null) {
			boardDataRequest.setPoster(user.getFakeName());
		}
		boardDataRequest.setBoardId(boardId);
		model.addAttribute("boardDataRequest", boardDataRequest);
		model.addAttribute("mode", "insert");
		
		return "board/write";
	}
	
	@PostMapping("/write")
	public String process(@Valid BoardDataRequest request, Errors errors, Model model, HttpSession session) {
		model.addAttribute("addCss", new String[] { "/board/write" });
		model.addAttribute("addJs", new String[] { "/ckeditor/ckeditor", "/board/board", "/file/fileupload" });
		if(errors.hasErrors()) {
			return "board/write";
		}
		
	
		BoardDataDto board = service.register(request, errors, session);
		
		
		return "redirect:/board/view/" + board.getId();
	}

}
