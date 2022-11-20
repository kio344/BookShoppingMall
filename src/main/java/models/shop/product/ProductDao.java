package models.shop.product;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.Product;
import models.entity.User;

@Component
public class ProductDao {

	@Autowired
	private EntityManager em;

	/**
	 * 상품 추가
	 * 
	 * @author 5563a
	 * @param param
	 * @return
	 */
	public ProductDto addProduct(ProductDto param) {

		Product entity = new Product();
		entity = ProductDto.toEntity(param);

		User user = em.find(User.class, param.getSeller().getMemNo());
		entity.setSeller(user);

		em.persist(entity);

		em.flush();

		return get(entity.getNum());
	}

	/**
	 * primary 키로 가져오기 (단일값)
	 * 
	 * @author 5563a
	 * @param num
	 * @return
	 */
	public ProductDto get(Long num) {

		Product entity = em.find(Product.class, num);

		return ProductDto.toDto(entity);
	}

	/**
	 * 책이름 가져오기 (단일값)
	 * 
	 * @author 5563a
	 * @param num
	 * @return
	 */
	public ProductDto get(String bookName) {

		String sql = "SELECT p FROM Product p WHERE bookName=:bookName ";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("bookName", bookName);

		Product entity = query.getSingleResult();

		return ProductDto.toDto(entity);
	}

	/**
	 * 최신 상품들 가져오기
	 * 
	 * @author 5563a
	 * @param start  페이지네이션
	 * @param offset 페이지네이션
	 * @return
	 */
	public List<ProductDto> gets(int start, int offset) {

		List<ProductDto> result = null;

		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p order by p.num desc ", Product.class);

		query.setFirstResult(start);
		query.setMaxResults(offset);

		result = query.getResultStream().map(t -> ProductDto.toDto(t)).toList();

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
	public List<ProductDto> getbestSeller(int start, int offset) {
		List<ProductDto> result = null;

		String sql = "SELECT p FROM Product p order by p.salesRate desc";

		TypedQuery<Product> query = em.createQuery(sql, Product.class);

		query.setFirstResult(start);
		query.setMaxResults(offset);

		result = query.getResultStream().map(t -> ProductDto.toDto(t)).toList();

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
	public List<ProductDto> getSearchProduct(int start, int offset, String searchValue, String searchType) {

		String sql = "SELECT p FROM Product p WHERE p." + searchType + " like :searchValue order by p.num desc ";
		TypedQuery<Product> query = em.createQuery(sql, Product.class);
		query.setParameter("searchValue", "%"+searchValue+"%");
		
		query.setFirstResult(start);
		query.setMaxResults(offset);
		
		List<ProductDto> result = query.getResultStream().map(p -> ProductDto.toDto(p)).toList();

		
		
		return result;

	}

	/**
	 * 상품 검색시 항목 수 (페이지 네이션을 위한)
	 * @param searchValue	검색값
	 * @param searchType	bookName,writer
	 * @return
	 */
	public int getSearchProductCount(String searchValue, String searchType) {
		
		String sql = "SELECT COUNT(*) FROM Product p WHERE p." + searchType + " like '%"+searchValue+"%'";
		
		
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
	public ProductDto buyProduct(Long num,int count) {
		
		Product entity = em.find(Product.class, num);
		entity.setCount(entity.getCount()-count);
		entity.setSalesRate(entity.getSalesRate()+count);
		
		em.persist(entity);
		
		em.flush();
		
		return ProductDto.toDto(entity);
		
	}

}
