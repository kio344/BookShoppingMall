package models.comment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.BoardData;
import models.entity.Comment;
import models.entity.User;

@Component
public class CommentDao {

	@Autowired
	private EntityManager em;

	public CommentDto register(CommentDto param) {

		Comment entity = CommentDto.toEntity(param);

		User user = em.find(User.class, param.getUser().getMemNo());
		BoardData board = em.find(BoardData.class, param.getBoard().getId());
		entity.setBoard(board);
		entity.setUser(user);

		em.persist(entity);
		if (entity.getId() != null) {
			entity.setMatchComment(entity.getId());
		}
		em.flush();

		return get(entity.getId());
	}

	public CommentDto get(Long id) {

		Comment entity = em.find(Comment.class, id);

		return entity == null ? null : CommentDto.toDto(entity);
	}
	
	public List<CommentDto> gets(String gid) {
		String sql = "SELECT c FROM Comment c WHERE gid = :gid";
		TypedQuery<Comment> params = em.createQuery(sql, Comment.class);
		params.setParameter("gid", gid);
		
		List<CommentDto> comments = params.getResultStream().map(CommentDto::toDto).toList();
		
		return comments;
	}

}
