package models.user;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.User;

@Component
public class UserDao {

	@Autowired
	private EntityManager em;
	
	/**
	 * ID로 회원 정보 찾기
	 * @param memId
	 * @return
	 */
	public UserDto check(String memId) {

		try {
			String sql = "SELECT u FROM User u WHERE u.memId = :memId";
			TypedQuery<User> tq = em.createQuery(sql, User.class);
			tq.setParameter("memId", memId);

			User entity = tq.getSingleResult(); // 쿼리로 찾은 아이디 정보들
			return UserDto.toDto(entity);

		} catch (Exception e) { // 아이디를 못 찾으면 null 리턴
			return null;
		}
		
	}
	
	public UserDto check(UserDto param) {

		User entity = UserDto.toEntity(param);

		em.persist(entity);
		em.flush();

		return check(entity.getMemId());
	}
	
	public UserDto update(UserRequest req) {
		
		User entity = em.find(User.class, req.getMemNo());
		entity.setMemNm(req.getMemNm());
		entity.setFakeName(req.getFakeName());
		entity.setEmail(req.getEmail());
		entity.setGender(req.getGender());
		entity.setAdress(req.getAdress());
		entity.setBirthDay(req.getBirthDay());
		entity.setMobile(req.getMobile());
		em.persist(entity);
		
		em.flush();
		
		return UserDto.toDto(entity);
	}
	
	public void password(UserDto dto) {
		
		User entity = em.find(User.class, dto.getMemNo());
		entity.setMemPw(dto.getMemPw());
		
		em.persist(entity);
		em.flush();
		
	}
	

	
	/**
	 * 유저 memNo 로 member 정보 가져오기
	 * @param memNo
	 * @return
	 */
	public UserDto get(Long memNo) {
		
		User user=em.find(User.class, memNo);
		
		
		
		return UserDto.toDto(user);
	}
	


	public UserDto kakaoMatching(Long kakaoId) {
		
		String sql = "SELECT u FROM User u WHERE kakaoId = :kakaoId";
		
		TypedQuery<User> tq = em.createQuery(sql, User.class);
		tq.setParameter("kakaoId", kakaoId);
		
		User user = tq.getSingleResult();
		
		return user == null ? null : UserDto.toDto(user);
	}
	
	public UserDto kakaoLink(UserDto user, Long kakaoId) {
		
		User entity = em.find(User.class, user.getMemNo());
		entity.setKakaoId(kakaoId);
		
		em.persist(entity);
		em.flush();
		
		return UserDto.toDto(entity);
	}


}
