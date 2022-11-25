package models.seller.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import models.seller.product.excpetion.BookNameException;
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
	
	/**
	 * @author kimminho
	 * @param req
	 * @param image
	 * 
	 * 상품 승인 신청 버튼을 누르면 상품이 디비에 저장이 되고, 파일은 resources 폴더에 productImages 폴더 안에 
	 * 신청한 사람 PK를 /10 해서 0 ~ 9까지의 폴더를 생성 후 마지막 번호에 맞는 폴더로 저장 
	 *	EX) 329 -> 29 -> 9 
	 */
	public void save(MultipartHttpServletRequest req, MultipartFile image) {
		/** 상품 디비 저장 */
		UserDto userSession = (UserDto) session.getAttribute("user");
		ProductRequestDto dto = new ProductRequestDto();
		
		dto.setSeller(userSession);
		dto.setSerialnum(req.getParameter("serialnum"));
		dto.setBookName(req.getParameter("bookName"));
		dto.setWriter(req.getParameter("writer"));
		dto.setPrice(Long.parseLong(req.getParameter("price")));
		dto.setCategory(req.getParameter("category"));
		dto.setPublisher(req.getParameter("publisher"));
		dto.setCount(Integer.parseInt(req.getParameter("count")));
		dto.setProgress(Progress.Examine);
		
		ProductRequestDto dtotest = productRequestDao.get(dto);
		
		if(dtotest == dto) {
			throw new BookNameException();
		}
		
		dto = productRequestDao.save(dto);
		/** 상품 디비 저장 */
		
		/** 파일 저장 시작 */
		dto.setImages(dto.getNum());
		id = dto.getImages();
			
		String uploadDir = request.getServletContext().getRealPath("");
		uploadDir += "../resources/static/productImages";
		System.out.println(uploadDir);
		File _uploadDir = new File(uploadDir);
		System.out.println(_uploadDir);
		if (!_uploadDir.isDirectory()) {
			_uploadDir.mkdirs();
		}
		
		Long num = id;
		
		String folder = String.valueOf(num % 10);
		
		_uploadDir = new File(uploadDir + "/" + folder);
		if (!_uploadDir.exists()) {
			_uploadDir.mkdir();
		}

		String path = uploadDir + "/" + folder + "/" + num;
		System.out.println(path);
		try (FileOutputStream fos = new FileOutputStream(path)) {
			fos.write(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		/** 파일 저장 끝 */
	}
}
