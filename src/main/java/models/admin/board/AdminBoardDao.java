package models.admin.board;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardConfig;

@Component
public class AdminBoardDao {
	
	@Autowired
	private EntityManager em;
	
	public AdminBoardDto searchToId(String id) {
		
		BoardConfig entity = em.find(BoardConfig.class, id);
		
		return AdminBoardDto.toDto(entity);
	}
	/** TypedQuery 찾아보기
	public AdminBoardDto searchToName(String name) {
		
		BoardConfig entity = em.find(BoardConfig.class, name);
		
		return AdminBoardDto.toDto(entity);
	}
	 */
	
	public AdminBoardDto save(AdminBoardDto dto) {
		
		BoardConfig entity = AdminBoardDto.toEntity(dto);
		
		em.persist(entity);
		
		em.flush();
		
		return searchToId(dto.getBoardId());
	}
	
}
