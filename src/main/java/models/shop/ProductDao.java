package models.shop;

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

	

}
