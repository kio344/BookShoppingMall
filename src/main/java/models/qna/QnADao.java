package models.qna;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.QnA;
import models.entity.User;

@Component
public class QnADao {

	@Autowired
	private EntityManager em;
	
	public QnADto register(QnADto param) {
		
		QnA entity = QnADto.toEntity(param);
		User user = em.find(User.class, param.getUser().getMemNo());
		entity.setUser(user);
		
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
	}
	
	public QnADto adminAnswer(QnADto param) {
		
		QnA entity = em.find(QnA.class, param.getId());
		
		entity.setAdminName(param.getAdminName());
		entity.setAnswer(param.getAnswer());
		entity.setAnswered(true);
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
	}
	
	public QnADto get(Long id) {
		
		QnA entity = em.find(QnA.class, id);
		
		
		return entity == null ? null : QnADto.toDto(entity);
	}
	
	public List<QnADto> gets(int page, int limit) {
		int offset = (page - 1) * limit;
		String sql = "SELECT q FROM QnA q ORDER BY isAdmin DESC, regDt DESC";
		TypedQuery<QnA> list = em.createQuery(sql, QnA.class);
		list.setFirstResult(offset);
		list.setMaxResults(limit);
		
		List<QnADto> qnas = list.getResultStream().map(QnADto::toDto).toList();
		return qnas;
	}
	
	public Long count(int page, int limit) {
		int offset = (page - 1) * limit;
		String sql = "SELECT COUNT(*) FROM QnA q ORDER BY isAdmin DESC, regDt DESC";
		TypedQuery<Long> list = em.createQuery(sql, Long.class);
		list.setFirstResult(offset);
		list.setMaxResults(limit);
		
		Long result = list.getSingleResult();
		return result;
	}
	
	public QnADto delete(Long id) {
		
		QnA entity = em.find(QnA.class, id);
		
		em.remove(entity);
		em.flush();
		
		return QnADto.toDto(entity);
	}
	 
}
