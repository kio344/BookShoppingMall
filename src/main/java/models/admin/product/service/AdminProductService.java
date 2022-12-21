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
	
	/**
	 * 상품 정보 수정 후 DB에 업데이트 해주는 기능
	 * @author kimminho
	 * @param request
	 */
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
	
	/**
	 * 관리자가 상품 수락하면 상품이 판매 페이지에 등록이 되고 Progress가 Agree로 바뀐다
	 * @author kimminho
	 * @param request
	 */
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
			
			productRequestDao.updateAdmin(dto);
		}
		/** 수정 시작 E */
	}		
	
	/**
	 * 상품 삭제 기능
	 * @author kimminho
	 * @param request
	 */
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
	
	/**
	 * 관리자가 상품 거절을 하면 Rejected로 변경 후 거절됨 표시
	 * @author kimminho
	 * @param request
	 */
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
	
	/**
	 * 관리자가 컨펌을 한 상품만 출력 Agree 상품
	 * @author kimminho
	 * @param progress
	 * @param type
	 * @return
	 */
	public boolean isAdminConfirmed(Progress progress, String type) {
		if (progress == null || type == null || type.isBlank()) {
			return false;
		}
		
		Progress compare = Progress.valueOf(type);
		
		return progress == compare;
	}

	
	
}
