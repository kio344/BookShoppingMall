package models.shop.userCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.mapping.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.common.BaseDto;
import models.entity.User;
import models.entity.UserCategory;
import models.user.UserDto;

public class UserCategoryDto extends BaseDto{

	private Long num;

	private UserDto user;

	private ArrayList<HashMap<String, Integer>> myCategory = new ArrayList<HashMap<String, Integer>>();

	public UserCategoryDto() {
	}

	public UserCategoryDto(int categoryCount, UserDto user) {
		this.user=user;
		for (int i = 0; i < categoryCount; i++) {
			myCategory.add(new HashMap<String, Integer>());
		}
	}

	public void addProductCategory(String categorys) {
		String[] category = categorys.split("/");

		for (int i = 0; i < category.length; i++) {
			addMap(category[i], myCategory.get(i));
		}

	}

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
		
		UserCategory entity=new UserCategory();
		
		entity.setUser(UserDto.toEntity(dto.getUser()));
		
		entity.setNum(dto.getNum());
		
		ObjectMapper om=new ObjectMapper();
		String myCategory=null;
		try {
			myCategory=om.writeValueAsString(dto.getMyCategory());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		entity.setMyCategoryData(myCategory);
		
		
		return entity;
	}
	

	public static UserCategoryDto toDto(UserCategory entity) {

		UserCategoryDto dto = new UserCategoryDto();

		dto.setNum(entity.getNum());
		dto.setUser(UserDto.toDto(entity.getUser()));
		
		ObjectMapper om=new ObjectMapper();
		ArrayList<HashMap<String, Integer>> mycategory=null;
		
		try {
			mycategory =  om.readValue(entity.getMyCategoryData().getBytes(), new TypeReference<ArrayList<HashMap<String, Integer>>>() {});
			
			dto.setMyCategory(mycategory);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return dto;

	}

}
