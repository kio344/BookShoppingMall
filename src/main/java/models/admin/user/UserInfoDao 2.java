package models.admin.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.User;
import models.user.UserDto;

@Component
public class UserInfoDao {
	
	@Autowired
	private EntityManager em;
		
	public UserDto gets() {
		User entity = em.find(User.class, em);
		
		return UserDto.toDto(entity);
	}
	
}
