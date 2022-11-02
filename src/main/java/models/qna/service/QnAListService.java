package models.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import common.page.Pagination;
import models.qna.QnADao;
import models.qna.QnADto;

@Service
public class QnAListService {
	
	@Autowired
	private QnADao dao;
	
	public List<QnADto> list(int page, Model model) {
		
		Long count = dao.count(page, 20);
		
		List<QnADto> qnas = dao.gets(page, 20);
		
		int total = count.intValue();
		
		Pagination pagination = new Pagination(page, total, 5, 20);
		
		model.addAttribute("pagination", pagination);
		
		return qnas;
	}

}
