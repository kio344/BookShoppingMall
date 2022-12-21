package models.shop.userCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.common.BaseDto;
import models.entity.UserCategory;
import models.user.UserDto;

/**
 * DB에 저장된 UserCategory 데이터를 파싱
 * 
 * @author 5563a
 *
 */
public class UserCategoryDto extends BaseDto {

	private Long num;

	private UserDto user;

	/**
	 * 
	 * 카테고리 기록 0번째 맵 국내 해외 분류 1번째 맵 총류, 철학, 종교 분류 2번째 맵 세부 분류
	 * 
	 * 각 맵의 key값 해당 카테고리에 해당하는 책을 본 횟수
	 * 
	 * {{해외:2, 국내:4 }{총류:1, 철학:5, 종교:3 , ....}{도서학:4, 문헌정보학:12 , ....}}
	 * 
	 */
	private ArrayList<HashMap<String, Integer>> myCategory = new ArrayList<HashMap<String, Integer>>();

	public UserCategoryDto() {
	}

	public UserCategoryDto(int categoryCount, UserDto user) {
		this.user = user;
		for (int i = 0; i < categoryCount; i++) {
			myCategory.add(new HashMap<String, Integer>());
		}
	}

	/**
	 * 기존 카테고리 데이터에 추가
	 * 
	 * @param categorys
	 */
	public void addProductCategory(String categorys) {
		String[] category = categorys.split("/");

		for (int i = 0; i < category.length; i++) {
			addMap(category[i], myCategory.get(i));
		}

	}

	/**
	 * 기존에 데이터가 있으면 증감, 없으면 추가
	 * 
	 * @param value
	 * @param map
	 */
	public void addMap(String value, HashMap<String, Integer> map) {
		if (map.containsKey(value)) {
			map.put(value, map.get(value) + 1);
		} else {
			map.put(value, 1);
		}

	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	/**
	 * 
	 * 
	 */
	public ArrayList<HashMap<String, Integer>> getMyCategory() {
		return myCategory;
	}

	public void setMyCategory(ArrayList<HashMap<String, Integer>> myCategory) {
		this.myCategory = myCategory;
	}

	@Override
	public String toString() {
		return "UserCategoryDto [num=" + num + ", user=" + user + ", myCategory=" + myCategory + "]";
	}

	public static UserCategory toEntity(UserCategoryDto dto) {

		UserCategory entity = new UserCategory();

		entity.setUser(UserDto.toEntity(dto.getUser()));

		entity.setNum(dto.getNum());

		ObjectMapper om = new ObjectMapper();
		String myCategoryToString = null;
		try {
			myCategoryToString = om.writeValueAsString(dto.getMyCategory());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		entity.setMyCategoryData(myCategoryToString);

		return entity;
	}

	public static UserCategoryDto toDto(UserCategory entity) {

		if (entity == null) {
			return null;
		}

		UserCategoryDto dto = new UserCategoryDto();

		dto.setNum(entity.getNum());
		dto.setUser(UserDto.toDto(entity.getUser()));

		ObjectMapper om = new ObjectMapper();
		ArrayList<HashMap<String, Integer>> mycategory = null;

		try {
			mycategory = om.readValue(entity.getMyCategoryData().getBytes(),
					new TypeReference<ArrayList<HashMap<String, Integer>>>() {
					});

			dto.setMyCategory(mycategory);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;

	}

}
