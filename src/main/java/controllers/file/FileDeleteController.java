package controllers.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.file.FileInfoDto;
import models.file.service.FileDeleteService;

@RestController
@RequestMapping("/file")
public class FileDeleteController {
	
	@Autowired
	private FileDeleteService deleteService;

	@GetMapping("/delete/{id}")
	public FileInfoDto form(@PathVariable(name = "id") Long id) {
		
		FileInfoDto fileInfo = deleteService.process(id);
		
		return fileInfo;
	}
	
}
