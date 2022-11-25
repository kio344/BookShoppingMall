package models.shop.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.ProductRequest;
import models.seller.product.ProductRequestDto;
import models.seller.product.Progress;

@Component
public class ProductDao {

	@Autowired
	private EntityManager em;

	

	/**
	 * primary 키로 가져오기 (단일값)
	 * 
	 * @author 5563a
	 * @param num
	 * @return
	 */
	public ProductRequestDto get(Long num) {

		ProductRequest entity = em.find(ProductRequest.class, num);

		return ProductRequestDto.toDto(entity);
	}

	/**
	 * 책이름 가져오기 (단일값)
	 * 
	 * @author 5563a
	 * @param num
	 * @return
	 */
	public ProductRequestDto get(String bookName) {

		String sql = "SELECT p FROM ProductRequest p WHERE bookName=:bookName AND progress=:progress ";
		TypedQuery<ProductRequest> query = em.createQuery(sql, ProductRequest.class);
		query.setParameter("bookName", bookName);
		query.setParameter("progress", Progress.Agree);
		
		ProductRequest entity = query.getSingleResult();

		return ProductRequestDto.toDto(entity);
	}

	/**
	 * 최신 상품들 가져오기
	 * 
	 * @author 5563a
	 * @param start  페이지네이션
	 * @param offset 페이지네이션
	 * @return
	 */
	public List<ProductRequestDto> gets(int start, int offset) {

		List<ProductRequestDto> result = null;

		TypedQuery<ProductRequest> query = em.createQuery("SELECT p FROM ProductRequest p WHERE p.progress=:progress order by p.num desc ", ProductRequest.class);

		query.setParameter("progress", Progress.Agree);
		
		query.setFirstResult(start);
		query.setMaxResults(offset);

		result = query.getResultStream().map(t -> ProductRequestDto.toDto(t)).toList();

		return result;
	}

	/**
	 * 베스트셀러 가져오기
	 * 
	 * @author 5563a
	 * @param start  페이지네이션
	 * @param offset 페이지네이션
	 * @return
	 */
	public List<ProductRequestDto> getbestSeller(int start, int offset) {
		List<ProductRequestDto> result = null;

		String sql = "SELECT p FROM ProductRequest p WHERE p.progress=:progress  order by p.salesRate desc";

		TypedQuery<ProductRequest> query = em.createQuery(sql, ProductRequest.class);

		query.setParameter("progress", Progress.Agree);
		
		query.setFirstResult(start);
		query.setMaxResults(offset);

		result = query.getResultStream().map(t -> ProductRequestDto.toDto(t)).toList();

		return result;
	}

	/**
	 * 상품 검색
	 * 
	 * @param start  페이지네이션
	 * @param offset 페이지네이션
	 * @param searchValue	검색값
	 * @param searchType	bookName,writer
	 * @return
	 */
	public List<ProductRequestDto> getSearchProduct(int start, int offset, String searchValue, String searchType) {

		String sql = "SELECT p FROM ProductRequest p WHERE p." + searchType + " like :searchValue order by p.num desc ";
		TypedQuery<ProductRequest> query = em.createQuery(sql, ProductRequest.class);
		query.setParameter("searchValue", "%"+searchValue+"%");
		
		System.out.println(searchValue);
		
		query.setFirstResult(start);
		query.setMaxResults(offset);
		
		List<ProductRequestDto> result = query.getResultStream().map(p -> ProductRequestDto.toDto(p)).toList();

		
		
		return result;

	}

	/**
	 * 상품 검색시 항목 수 (페이지 네이션을 위한)
	 * @param searchValue	검색값
	 * @param searchType	bookName,writer
	 * @return
	 */
	public int getSearchProductCount(String searchValue, String searchType) {
		
		String sql = "SELECT COUNT(*) FROM ProductRequest p WHERE p.progress='Agree' AND p." + searchType + " like '%"+searchValue+"%'";
		
				
		String result = em.createNativeQuery(sql).getSingleResult().toString();
		
		
		return Integer.parseInt(result);
	}
	
	
	/**
	 * 상품 구매 시
	 * 판매량(Product.salesRate) ++
	 * 재고(Product.count) 	--
	 * 처리
	 * @param num
	 * @return
	 */
	public ProductRequestDto buyProduct(Long num,int count) {
		
		ProductRequest entity = em.find(ProductRequest.class, num);
		entity.setCount(entity.getCount()-count);
		entity.setSalesRate(entity.getSalesRate()+count);
		
		em.persist(entity);
		
		em.flush();
		
		return ProductRequestDto.toDto(entity);
		
	}

}
