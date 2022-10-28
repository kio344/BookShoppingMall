package models.seller.product;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.user.UserDto;

@Service
public class ProductSaveService {
	
	@Autowired
	private ProductDao productDao;
	
	
	
	public void save(ProductRequest req, HttpSession session) {
		UserDto user = (UserDto)session.getAttribute("user");
		
		ProductDto dto = new ProductDto();

		dto.setSeller(user.getMemId());
		dto.setSerialnum(req.getSerialnum());
		dto.setBookName(req.getBookName());
		dto.setWriter(req.getWriter());
		dto.setPrice(req.getPrice());
		dto.setCategory(req.getCategory());
		dto.setPublisher(req.getPublisher());
		dto.setCount(req.getCount());
		dto.setProgress(req.getProgress());
		
		productDao.save(dto);
		
	}
}
