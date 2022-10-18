package models.admin.board;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardConfig;

@Component
public class AdminBoardDao {
	
	@Autowired
	private EntityManager em;
	
	public AdminBoardDto get(String id) {
		
		BoardConfig entity = em.find(BoardConfig.class, id);
		
		
		return AdminBoardDto.toDto(entity);
	}
	
	
}
