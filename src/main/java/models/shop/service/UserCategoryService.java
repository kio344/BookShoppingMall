package models.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.entity.User;
import models.shop.userCategory.UserCategoryDao;
import models.shop.userCategory.UserCategoryDto;
import models.user.UserDto;

@Service
public class UserCategoryService {

	@Autowired
	private UserCategoryDao userCategoryDao;

	public UserCategoryDto userCategoryUpdate(Long userNo, String addCategory) {

		return userCategoryDao.updateCategory(userNo, addCategory);
	}



}
