package models.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.seller.product.ProductRequestDto;
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

	/**
	 * 카테고리 업데이트
	 * @param userNo
	 * @param addCategory
	 * @return
	 */
	public UserCategoryDto userCategoryUpdate(Long userNo, String addCategory) {

		return userCategoryDao.updateCategory(userNo, addCategory);
	}

	public UserCategoryDto register(Long userNo) {

		return userCategoryDao.register(userNo);

	}

	/**
	 * 유저 카테고리 데이터 가져오기
	 * 
	 * @param userNo
	 * @return
	 */
	public UserCategoryDto get(Long userNo) {

		return userCategoryDao.getCategory(userNo);
	}

	/**
	 * 해당 유저의 추천 도서 가져오기
	 * 
	 * @param userNo
	 * @return
	 */
	public List<ProductRequestDto> getMyShopProduct(Long userNo) {

		UserCategoryDto myCategoryDto = get(userNo);

		/** 정렬 후 관심 카테고리 가져오기 S */

		// 관심카테고리
		String searchVal = null;

		if (myCategoryDto == null) {
			return null;
		}

		//카테고리 데이터의 1번째 카테고리 항목 가져오기
		Map<String, Integer> map = myCategoryDto.getMyCategory().get(1);

		List<Entry<String, Integer>> list_Entry = new ArrayList<Entry<String, Integer>>(map.entrySet());
		
		//카테고리 데이터 정렬
		Collections.sort(list_Entry, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}

		});
		
		//가장 많이 기록된 카테고리값 searchVal 에 저장
		for (Entry<String, Integer> entry : list_Entry) {
			searchVal = entry.getKey();
			break;
		}

		/** 정렬 후 관심 카테고리 가져오기 E */

		// 카테고리가 searchVal 인 책 가져오기
		List<ProductRequestDto> result = productDao.getSearchProduct(0, 5, searchVal, "category");

		return result;
	}

}
