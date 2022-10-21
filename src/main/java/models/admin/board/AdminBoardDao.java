package models.admin.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	/**
	 * TypedQuery 찾아보기 public AdminBoardDto searchToName(String name) {
	 * 
	 * BoardConfig entity = em.find(BoardConfig.class, name);
	 * 
	 * return AdminBoardDto.toDto(entity); }
	 */

	public List<AdminBoardDto> gets() {
		
		TypedQuery<BoardConfig> entity = em.createQuery("SELECT b FROM BoardConfig b", BoardConfig.class);
		
		List<AdminBoardDto> list = entity.getResultStream().map(AdminBoardDto::toDto).toList();
		
		System.out.println(list);
		
		return list;
	}

	public AdminBoardDto save(AdminBoardDto dto) {

		BoardConfig entity = AdminBoardDto.toEntity(dto);
		
		em.persist(entity);
		
		em.flush();
		
		return searchToId(dto.getBoardId());
	}
	
	public void updateBoard(AdminBoardDto dto) {
		
		BoardConfig entity = em.find(BoardConfig.class, dto.getBoardId());
		
		entity.setBoardName(dto.getBoardName());
		entity.setCommentUse(dto.getCommentUse());
		entity.setIsUse(dto.getIsUse());
		entity.setMemberOnly(dto.getMemberOnly());
		entity.setPageCount(dto.getPageCount());
		
		em.persist(entity);
		
		em.flush();
		
	}

}
