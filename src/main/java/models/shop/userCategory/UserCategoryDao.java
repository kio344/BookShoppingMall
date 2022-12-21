package models.shop.userCategory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.User;
import models.entity.UserCategory;
import models.user.UserDto;

/**
 * 유저별 카테고리 기록 DB
 * @author 5563a
 *
 */
@Component
public class UserCategoryDao {

	@Autowired
	private EntityManager em;

	/**
	 * 기존 카테고리기록이 존재하지 않을때 진행
	 * @param userNo
	 * @return
	 */
	public UserCategoryDto register(Long userNo) {

		User user = em.find(User.class, userNo);

		//카테고리 항목 3개
		UserCategoryDto categoryDto = new UserCategoryDto(3, UserDto.toDto(user));

		UserCategory entity = UserCategoryDto.toEntity(categoryDto);

		entity.setUser(user);

		em.persist(entity);

		em.flush();

		return UserCategoryDto.toDto(entity);
	}

	/**
	 * 유저의 카테고리 정보를 가져옴
	 * @param user
	 * @return
	 */
	public UserCategoryDto getCategory(UserDto user) {

		String sql = "SELECT c FROM UserCategory c WHERE user_memNo=:userNo";

		TypedQuery<UserCategory> query = em.createQuery(sql, UserCategory.class);

		query.setParameter("userNo", user.getMemNo());

		UserCategory result = null;

		try {
			result = query.getSingleResult();

		} catch (NoResultException e) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return UserCategoryDto.toDto(result);

	}
	/**
	 * 유저 카테고리 정보가져옴
	 * @param userNo
	 * @return
	 */
	public UserCategoryDto getCategory(Long userNo) {

		UserDto user = new UserDto();
		user.setMemNo(userNo);

		return getCategory(user);
	}

	/**
	 * 카테고리에 기록하기
	 * 
	 * @param userNo   해당 유저
	 * @param category 추기 기록할 상품의 카테고리
	 * @return
	 */
	public UserCategoryDto updateCategory(Long userNo, String category) {

		/** UserCategory 가져오기 S */
		String sql = "SELECT c FROM UserCategory c WHERE user_memNo=:userNo";

		TypedQuery<UserCategory> query = em.createQuery(sql, UserCategory.class);

		query.setParameter("userNo", userNo);

		UserCategory result = query.getSingleResult();

		UserCategoryDto nowCategoryDto = UserCategoryDto.toDto(result);
		/** UserCategory 가져오기 E */

		// 카테고리 추가하기
		nowCategoryDto.addProductCategory(category);

		/** 업데이트된 카테고리 저장 S */
		UserCategory newCategory = UserCategoryDto.toEntity(nowCategoryDto);

		result.setMyCategoryData(newCategory.getMyCategoryData());

		em.persist(result);

		em.flush();

		/** 업데이트된 카테고리 저장 E */

		return UserCategoryDto.toDto(newCategory);
	}

}
