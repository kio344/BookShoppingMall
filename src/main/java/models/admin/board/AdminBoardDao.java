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
	 * 게시판 정보 가져오기
	 * @author kimminho
	 * @return
	 */
	public List<AdminBoardDto> gets() {
		
		TypedQuery<BoardConfig> entity = em.createQuery("SELECT b FROM BoardConfig b", BoardConfig.class);
		
		List<AdminBoardDto> list = entity.getResultStream().map(AdminBoardDto::toDto).toList();
		
		return list;
	}
	
	/**
	 * 게시판 정보 DB에 저장
	 * @param dto
	 * @return
	 */
	public AdminBoardDto save(AdminBoardDto dto) {

		BoardConfig entity = AdminBoardDto.toEntity(dto);
		
		em.persist(entity);
		
		em.flush();
		
		return searchToId(dto.getBoardId());
	}
	
	/**
	 * DB에 게시판 정보 수정 업데이트
	 * @author kimminho
	 * @param dto
	 */
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
	
	/**
	 * 게시판 정보 삭제
	 * @author kimminho
	 * @param boardId
	 */
	public void deleteBoard(String boardId){
		
		BoardConfig entity = em.find(BoardConfig.class, boardId);
		
		em.remove(entity);
		
		em.flush();
		
	}

}
