package models.seller.edit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.seller.product.ProductRequestDto;

@Component
public class SearchDao {
	
	@Autowired
	private EntityManager em;
	
	/**
	 * 상품 이름으로 검색 또는 글쓴이로 검색 기능
	 * @author kimminho
	 * @param search
	 * @param searchType
	 * @param start
	 * @param offset
	 * @return
	 */
	public List<ProductRequestDto> searchToName(String search, String searchType, int start, int offset) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT p FROM ProductRequest p WHERE ");
		
		if(searchType.equals("bookName")) {
			sb.append("bookName LIKE CONCAT('%', :bookName, '%')");
		}else {
			sb.append("writer LIKE CONCAT('%', :writer, '%')");
		}
		
		sb.append(" ORDER BY regDt DESC");
		
		TypedQuery<ProductRequest> entity = em.createQuery(sb.toString(), ProductRequest.class);
		
		if(searchType.equals("bookName")) {
			entity.setParameter("bookName", search);
		}else {
			entity.setParameter("writer", search);
		}
		
		entity.setFirstResult(start);
		entity.setMaxResults(offset);
		
		List<ProductRequestDto> list = entity.getResultStream().map(ProductRequestDto::toDto).toList();
		
		return list;
	}
	
	/**
	 * 모든 상품 가져오기
	 * @author kimminho
	 * @return
	 */
	public List<ProductRequestDto> gets(){
		TypedQuery<ProductRequest> entity = em.createQuery("SELECT p FROM ProductRequest p", ProductRequest.class);
		
		List<ProductRequestDto> list = entity.getResultStream().map(ProductRequestDto::toDto).toList(); 
		
		
		return list;
	}
	
	/**
	 * 상품정보를 숫자로 가져와서 페이지네이션으로 사용
	 * @author kimminho
	 * @param search
	 * @param searchType
	 * @return
	 */
	public int getsearchProduct(String search, String searchType) {
		
		String sql = "SELECT COUNT(*) FROM ProductRequest p WHERE p." + searchType + " LIKE '%" + search + "%'"; 

		String result = em.createNativeQuery(sql).getSingleResult().toString();
		
		return Integer.parseInt(result) == 0 ? 1 : Integer.parseInt(result);
	}
	
	public ProductRequestDto getProduct(Long productNum){
		
		ProductRequest entity = em.find(ProductRequest.class, productNum);
		
		return ProductRequestDto.toDto(entity);
	}

}	
