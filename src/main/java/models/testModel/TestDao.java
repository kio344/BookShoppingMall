package models.testModel;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDao {

	@Autowired
	private EntityManager em;
	
	public TestDto insert(TestDto param) {
		System.out.println(param);
		TestEntity entity=new TestEntity();
		entity.setMemId(param.getMemId());
		entity.setMemPw(param.getMemPw());
		entity.setMemPwRe(param.getMemPwRe());
		entity.setMemNm(param.getMemNm());
		
		em.persist(entity);
		em.flush();
		
		return param;
	}
	
}
