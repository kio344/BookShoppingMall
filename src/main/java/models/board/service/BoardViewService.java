package models.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import models.board.BoardDataDao;
import models.board.BoardDataDto;
import models.file.FileInfoDao;
import models.file.FileInfoDto;

@Service
public class BoardViewService {
	
	@Autowired
	private BoardDataDao boardDataDao;
	@Autowired
	private FileInfoDao fileInfoDao;
	
	public BoardDataDto view(Long id, Model model) {
		
		BoardDataDto board = boardDataDao.countUp(id);
		
		List<FileInfoDto> fileInfos = fileInfoDao.gets(board.getGid());
		
		model.addAttribute("fileInfos", fileInfos);
		
		return board;
	}
	
	

}
