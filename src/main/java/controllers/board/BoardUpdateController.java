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

import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.board.BoardDataRequest;
import models.board.service.BoardWriteService;
import models.user.UserDto;

@Controller
@RequestMapping("/board")
public class BoardUpdateController {
	
	@Autowired
	private BoardWriteService service;
	@Autowired
	private BoardDataDao boardDataDao;

	@GetMapping("/update/{id}")
	public String form(@PathVariable(name = "id") Long id, Model model, HttpSession session) {
		
		model.addAttribute("addCss", new String[] { "/board/write" });
		model.addAttribute("addJs", new String[] { "/ckeditor/ckeditor", "/board/board", "/file/fileupload" });
		
		BoardDataRequest boardDataRequest = BoardDataDto.toRequest(boardDataDao.get(id));
		
		model.addAttribute("boardDataRequest", boardDataRequest);
		model.addAttribute("mode", "update");
		
		return "board/write";
	}
	
	@PostMapping("/update")
	public String process(@Valid BoardDataRequest request, Errors errors, Model model, HttpSession session) {
		
		model.addAttribute("addCss", new String[] { "/board/write" });
		model.addAttribute("addJs", new String[] { "/ckeditor/ckeditor", "/board/board", "/file/fileupload" });
	
		BoardDataDto board = service.register(request, errors, session);
		
		if(errors.hasErrors()) {
			return "board/update";
		}
		return "redirect:/board/view/" + board.getId();
	}
	
}
