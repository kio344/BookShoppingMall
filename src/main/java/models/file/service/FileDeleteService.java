package models.file.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.file.FileInfoDao;
import models.file.FileInfoDto;

@Service
public class FileDeleteService {

	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private HttpServletRequest req;
	
	public FileInfoDto process(Long id) {
		
		FileInfoDto fileInfo = fileInfoDao.get(id);
		if(fileInfo == null) {
			throw new RuntimeException("등록되지 않은 파일입니다.");
		}
		
		String folder = String.valueOf(id % 10);
		String path = req.getServletContext().getRealPath(".") + "/../resources/static/uploads/"+ folder + "/" + id + fileInfo.getExtension();
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
		
		fileInfoDao.delete(id);
		
		return fileInfo;
	}
	
}
