package models.file;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.entity.FileInfo;
import models.entity.User;
import models.user.UserDto;

@Component
public class FileInfoDao {
	
	@Autowired
	private EntityManager em;
	
	public FileInfoDto register(FileInfoDto dto) {
		
		FileInfo entity = FileInfoDto.toEntity(dto);
		if(dto.getUser() != null) {
			User user = em.find(User.class, dto.getUser().getMemNo());
			entity.setUser(user);
		}
		
		em.persist(entity);
		em.flush();
		
		return get(entity.getId());
	}
	
	public void doneFinish(String gid) {
		String sql = "SELECT f FROM FileInfo f WHERE gid=:gid";
		TypedQuery<FileInfo> files = em.createQuery(sql, FileInfo.class);
		files.setParameter("gid", gid);
		
		List<FileInfo> fileInfos = files.getResultList();
		
		for(FileInfo file : fileInfos) {
			file.setDone(true);
			em.persist(file);
		}
		em.flush();
		
	}
	
	public FileInfoDto get(Long id) {
		
		FileInfo entity = em.find(FileInfo.class, id);
		
		return entity == null ? null : FileInfoDto.toDto(entity);
	}
	
	public List<FileInfoDto> gets(String gid) {
		String sql = "SELECT f FROM FileInfo f WHERE gid=:gid";
		TypedQuery<FileInfo> files = em.createQuery(sql, FileInfo.class);
		files.setParameter("gid", gid);
		
		List<FileInfoDto> fileInfos = files.getResultStream().map(FileInfoDto::toDto).toList();
		
		return fileInfos;
	}

}
