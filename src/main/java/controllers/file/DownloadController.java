package controllers.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.file.service.FileDownloadService;

@RestController
@RequestMapping("/file")
public class DownloadController {
	
	@Autowired
	private FileDownloadService downloadService;

	@GetMapping("/download/{id}")
	public void form(@PathVariable(name = "id") Long id) {
		
		downloadService.download(id);
		
	}
	
}
