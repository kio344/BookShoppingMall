package models.seller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSaveService {
	
	@Autowired
	private ProductDao productDao;
	
	
	
	public void save(ProductRequest request) {
		
		ProductDto dto = new ProductDto();
		dto.setBookName(request.getBookName());
		dto.setCategory(request.getCategory());
		dto.setCount(request.getCount());
		dto.setPrice(request.getPrice());
		dto.setPublisher(request.getPublisher());
		dto.setSeller(request.getSeller());
		dto.setProgress(Progress.Examine);
		
		productDao.save(dto);
		
	}
}
