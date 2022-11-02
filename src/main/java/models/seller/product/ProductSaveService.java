package models.seller.product;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import models.user.UserDto;

@Service
public class ProductSaveService {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession session;

	@Autowired
	private ProductRequestDao productRequestDao;
	
	private static Long id;
	
	public void save(ProductRequest req) {
		UserDto userSession = (UserDto) session.getAttribute("user");

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
		
		dto = productRequestDao.save(dto);
		id = dto.getNum();
		
	}

	public void saveImage(MultipartFile image) {
		
		ProductRequestDto dto = productRequestDao.get(id);
		
		String uploadDir = request.getServletContext().getRealPath("");
		uploadDir += "../resources/static/productImages";
		
		File _uploadDir = new File(uploadDir);
		
		if (!_uploadDir.isDirectory()) {
			_uploadDir.mkdirs();
		}
		
		long num = dto.getNum();
			
		System.out.println(num);
			
		String folder = String.valueOf(num % 10);

		_uploadDir = new File(uploadDir + "/" + folder);
		if (!_uploadDir.exists()) {
			_uploadDir.mkdir();
		}

		String path = uploadDir + "/" + folder + "/" + num;

		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
