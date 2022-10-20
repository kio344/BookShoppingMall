package controllers.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import models.file.FileInfoDto;
import models.file.service.UploadService;

@Controller
@RequestMapping("/file/upload")
public class UploadController {
	
	@Autowired
	private UploadService uploadService;

	@PostMapping
	@ResponseBody
	public List<FileInfoDto> process(@RequestParam(name = "gid", required = false) String gid, @RequestParam(name = "file", required = false) MultipartFile[] files) {
		
		List<FileInfoDto> data = uploadService.upload(files, gid);
		
		return data;
	}
	
}
