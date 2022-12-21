package models.admin.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.user.UserDao;
import models.user.UserDto;

@Service
public class UserSearchService {
	
	@Autowired
	private UserDao searchDao;

public List<UserDto> search(String search, String searchType, int start, int offset) {
		
		List<UserDto> list = searchDao.search(search, searchType, start, offset);
		
		return list;
	}
	
}
