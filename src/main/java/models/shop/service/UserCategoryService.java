package models.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.shop.product.ProductDao;
import models.shop.product.ProductDto;
import models.shop.userCategory.UserCategoryDao;
import models.shop.userCategory.UserCategoryDto;

@Service
public class UserCategoryService {

	@Autowired
	private UserCategoryDao userCategoryDao;
	
	@Autowired
	private ProductDao productDao;

	public UserCategoryDto userCategoryUpdate(Long userNo, String addCategory) {

		return userCategoryDao.updateCategory(userNo, addCategory);
	}
	
	public UserCategoryDto register(Long userNo) {
		

		
		return userCategoryDao.register(userNo);
		
	}
	
	public UserCategoryDto get(Long userNo) {
		
		return userCategoryDao.getCategory(userNo);
	}
	
	public List<ProductDto> getMyShopProduct(Long userNo){
		
		UserCategoryDto myCategoryDto=get(userNo);
		
		/**
		 * 정렬 후 값 가져오기 S
		 */
		if (myCategoryDto==null) {
			return null;
		}
		
		TreeMap<String, Integer> map=myCategoryDto.getMyCategory().get(1);
		
		List<Entry<String, Integer>> list_Entry=new ArrayList<Entry<String,Integer>>(map.entrySet());
		
		Collections.sort(list_Entry,new Comparator<Entry<String, Integer>>() {
		
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		
		});
	
		String searchVal=null;
		System.out.println(list_Entry);
		for(Entry<String, Integer> entry:list_Entry) {
			searchVal=entry.getKey();
			break;
		}
		
		/**
		 * 정렬 후 값 가져오기 E
		 */
		
		List<ProductDto> result = productDao.getSearchProduct(0,5,searchVal, "category");
		
		return result;
	}
	
	



}
