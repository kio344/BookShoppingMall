package models.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardConfig;
import models.entity.BoardData;
import models.entity.User;

@Component
public class BoardDataDao {
	
	@Autowired
	private EntityManager em;
	
	/**
	 * 게시글 추가 / 수정
	 * @param dto
	 * @return
	 */
	public BoardDataDto register(BoardDataDto dto) {
		
		BoardData entity = null;
		
		if(dto.getId() == null) { // 추가
			entity = BoardDataDto.toEntity(dto);
			if(dto.getBoard() != null) {
				BoardConfig config = em.find(BoardConfig.class, dto.getBoard().getBoardId());
				entity.setBoard(config);
			}
			if(dto.getUser() != null) {
				User user = em.find(User.class, dto.getUser().getMemNo());
				entity.setUser(user);
			}
		} else { // 수정
			entity = em.find(BoardData.class, dto.getId());
			entity.setSubject(dto.getSubject());
			entity.setContents(dto.getContents());
			entity.setPoster(dto.getPoster());
			if(entity.isPrivate() != dto.isPrivate()) {
				entity.setPrivate(dto.isPrivate());
				if(dto.isPrivate()) {
					entity.setPrivatePassword(dto.getPrivatePassword());
				}
			}
		}
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
	}
	
	public BoardDataDto countUp(Long id) {
		
		BoardData entity = em.find(BoardData.class, id);
		entity.setHit(entity.getHit() + 1);
		
		em.persist(entity);
		em.flush();
		
		return entity == null ? null : BoardDataDto.toDto(entity);
	}
	
	/**
	 * 게시글 단일 조회
	 * @param id
	 * @return
	 */
	public BoardDataDto get(Long id) {
		
		BoardData entity = em.find(BoardData.class, id);
		
		return entity == null ? null : BoardDataDto.toDto(entity);
	}
	
	/**
	 * 게시글 리스트 조회(페이지 네이션)
	 * @param boardId
	 * @return
	 */
	public List<BoardDataDto> gets(String boardId, int page, int limit, String select, String search) {
		int offset = (page - 1) * limit;
		String sql = "SELECT b FROM BoardData b WHERE boardId = :boardId";
		if(select != null && search != null) {
			switch (select) {
			case "id":
				sql += " AND id = :id";
				break;
			case "subject":
				sql += " AND subject LIKE CONCAT('%',:subject,'%')";
				break;
			case "poster":
				sql += " AND poster LIKE CONCAT('%',:poster,'%')";
				break;
			default:
				break;
			}
		}
		sql += " ORDER BY regDt DESC";
		TypedQuery<BoardData> entities = em.createQuery(sql, BoardData.class);
		entities.setParameter("boardId", boardId);
		if(select != null && search != null) {
			if(select.equals("id")) {
				Long x = Long.parseLong(search);
				entities.setParameter(select, x);
			} else {
				entities.setParameter(select, search);
			}
		}
		
		entities.setFirstResult(offset);
		entities.setMaxResults(limit);
		
		List<BoardDataDto> boards = entities.getResultStream().map(BoardDataDto::toDto).toList();
		
		return boards;
	}
	
	public Long total(String boardId) {
		
		String sql = "SELECT COUNT(*) FROM BoardData b WHERE boardId = :boardId";
		TypedQuery<Long> entities = em.createQuery(sql, Long.class);
		entities.setParameter("boardId", boardId);
		
		Long total = entities.getSingleResult();
		return total;
	}
	
	public BoardDataDto searchGid(String gid) {
		
		String sql = "SELECT b FROM BoardData b WHERE gid = :gid";
		
		TypedQuery<BoardData> entity = em.createQuery(sql, BoardData.class);
		entity.setParameter("gid", gid);
		
		BoardData en = entity.getSingleResult();
		
		return en == null ? null : BoardDataDto.toDto(en);
	}
	
	/**
	 * 게시글 삭제
	 * @param id
	 */
	public BoardDataDto delete(Long id) {
		
		BoardData entity = em.find(BoardData.class, id);
		
		em.remove(entity);
		em.flush();
		
		return BoardDataDto.toDto(entity);
		
	}

}
