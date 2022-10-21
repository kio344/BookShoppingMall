package models.file.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import models.file.FileInfoDao;
import models.file.FileInfoDto;
import models.user.UserDao;
import models.user.UserDto;

@Service
public class UploadService {
	
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest req;

	public List<FileInfoDto> upload(MultipartFile[] files, String gid) {
		String uploadPath = req.getServletContext().getRealPath(".") + "/../resources/static/uploads";
		
		
		UserDto user = (UserDto)session.getAttribute("user");
		
		
		List<FileInfoDto> fileInfos = new ArrayList<>();
		
		for(MultipartFile file : files) {
			// 1. 파일 정보를 DB 기록
			String fileName = file.getOriginalFilename();
			String extension = fileName.substring(fileName.lastIndexOf("."));
			FileInfoDto param = new FileInfoDto();
			param.setGid(gid);
			param.setFileName(fileName);
			param.setContentType(file.getContentType());
			param.setExtension(extension);
			param.setUser(user);
			
			FileInfoDto fileInfo = fileInfoDao.register(param);

			// 2. 파일 등록 번호 -> 분산될 디렉토리 번호 결정, 파일 명으로 업로드
			Long id = fileInfo.getId();
			String folder = "" + (id % 10);
			File uploadDir = new File(uploadPath + "/" + folder);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			String filePath = uploadPath + "/" + folder + "/" + id;
			if(!extension.isBlank()) {
				filePath += extension;
			}
			
			String fileUrl = req.getContextPath() + "/uploads/" + folder + "/" + id;
			if(!extension.isBlank()) {
				fileUrl += extension;
			}
			
			fileInfo.setFilePath(filePath);
			fileInfo.setFileUrl(fileUrl);
			
			try(FileOutputStream fos = new FileOutputStream(filePath);
					BufferedOutputStream bos = new BufferedOutputStream(fos)) {
				bos.write(file.getBytes());
				
				fileInfos.add(fileInfo); // 파일 업로드 성공 시 정보 추가
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileInfos;
	}
	
}
