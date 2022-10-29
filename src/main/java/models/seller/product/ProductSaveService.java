package models.seller.product;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.entity.User;
import models.user.UserDto;

@Service
public class ProductSaveService {
	
	@Autowired
	private ProductRequestDao productDao;
	
	
	
	public void save(ProductRequest req, HttpSession session) {
		UserDto userSession = (UserDto)session.getAttribute("user");
			
		ProductRequestDto dto = new ProductRequestDto();
		
		dto.setSeller(userSession);
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
