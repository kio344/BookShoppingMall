package controllers.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.file.FileInfoDao;

@Controller
public class BoardDeleteController {
	
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private FileInfoDao fileInfoDao;

	@RequestMapping("/board/delete/{id}")
	public String form(@PathVariable(name = "id") Long id) {
		
		BoardDataDto board = boardDataDao.delete(id);
		fileInfoDao.deletes(board.getGid());
		
		return "redirect:/";
	}
	
}
