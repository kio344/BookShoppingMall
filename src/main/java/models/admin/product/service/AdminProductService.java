package models.admin.product.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.entity.FileInfo;
import models.file.FileInfoDto;
import models.seller.product.ProductRequestDao;
import models.seller.product.ProductRequestDto;
import models.seller.product.Progress;
import models.shop.product.ProductDao;


@Service
public class AdminProductService {
	
	@Autowired
	private ProductRequestDao productRequestDao;
	
	@Autowired
	private ProductDao productDao;
	
	public void update(HttpServletRequest request) {
		/** 유효성 검사 S */
		String[] productIds = request.getParameterValues("product");
		ProductRequestDto dto = new  ProductRequestDto();
		if (productIds == null) {
			throw new RuntimeException("상품을 선택 하세요.");
		}
		/** 유효성 검사 E */
		for(String product : productIds) {
			dto = productRequestDao.get(Long.parseLong(product));
			
			dto.setPrice(Long.parseLong((request.getParameter("price_" + product))));
			dto.setCount(Integer.parseInt((request.getParameter("count_" + product))));
			
			productRequestDao.update(dto);
			
		}
	}
	
	public void agree(HttpServletRequest request) {
		/** 유효성 검사 S */
		String[] productIds = request.getParameterValues("product");
		if (productIds == null) {
			throw new RuntimeException("상품을 선택 하세요.");
		}

		/** 유효성 검사 E */
		
		/** 수정 시작 S */
		for(String product : productIds) {
			ProductRequestDto dto = new ProductRequestDto();
			dto = productRequestDao.get(Long.parseLong(product)); 
			dto.setProgress(Progress.Agree);
//			productDao.addProduct(ProductRequestDto.toRequest(dto));
			
			productRequestDao.updateAdmin(dto);
		}
		/** 수정 시작 E */
	}		
	
	public void remove(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("product");
		if (productIds == null) {
			throw new RuntimeException("상품을 선택 하세요.");
		}
		for(String product : productIds) {
			
		ProductRequestDto dto = new  ProductRequestDto();
		dto = productRequestDao.get(Long.parseLong(product));
		
		productRequestDao.remove(dto);
		
		Long images = Long.parseLong(product);
		
		String dir = request.getServletContext().getRealPath("");
		String folder = String.valueOf(images % 10);
		dir += "/../resources/static/productImages" + "/" + folder + "/" + images;
		
		File remove = new File(dir);
		remove.delete();
		
		}
	}
	
	public void rejected(HttpServletRequest request) {
		
		/** 유효성 검사 S */
		String[] productIds = request.getParameterValues("product");
		if (productIds == null) {
			throw new RuntimeException("상품을 선택 하세요.");
		}
		
		/** 유효성 검사 E */
		
		/** 수정 시작 S */
		for(String product : productIds) {
			ProductRequestDto dto = new ProductRequestDto();
			dto.setNum(Long.parseLong(product));
			dto.setProgress(Progress.Rejected);
			productRequestDao.argee(dto);
			
		}
		/** 수정 시작 E */
	}
	
	public boolean isAdminConfirmed(Progress progress, String type) {
		if (progress == null || type == null || type.isBlank()) {
			return false;
		}
		
		Progress compare = Progress.valueOf(type);
		
		return progress == compare;
	}

	
	
}
