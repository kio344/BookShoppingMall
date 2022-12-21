package models.admin.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.User;
import models.user.UserDto;

@Component
public class UserInfoDao {
	
	@Autowired
	private EntityManager em;
		
	public List<UserDto> gets() {
		TypedQuery<User> entites = em.createQuery("SELECT u FROM User u ", User.class);
		
		List<UserDto> list = entites.getResultStream().map(UserDto::toDto).toList();
		
		return list;
	}
	
	
	
}
