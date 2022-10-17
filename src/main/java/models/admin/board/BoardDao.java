package models.admin.board;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardConfig;

@Component
public class BoardDao {
	
	@Autowired
	private EntityManager em;
	
	public BoardDto get(String id) {
		
		BoardConfig entity = em.find(BoardConfig.class, id);
		
		return BoardDto.toDto(entity);
	}
	
	
}
