package models.file;

import models.common.BaseDto;
import models.entity.FileInfo;
import models.user.UserDto;

public class FileInfoDto extends BaseDto {

	private Long id;
	private String gid;
	private String fileName;
	private String contentType;
	private String extension;
	private boolean done;
	private UserDto user;
	private String fileUrl;
	private String filePath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileDto [id=" + id + ", gid=" + gid + ", fileName=" + fileName + ", contentType=" + contentType
				+ ", extension=" + extension + ", done=" + done + ", user=" + user + "]";
	}

	public static FileInfo toEntity(FileInfoDto dto) {
		if (dto == null) {
			return null;
		}

		FileInfo entity = new FileInfo();
		entity.setId(dto.getId());
		entity.setGid(dto.getGid());
		entity.setFileName(dto.getFileName());
		entity.setContentType(dto.getContentType());
		entity.setExtension(dto.getExtension());
		entity.setDone(dto.isDone());
		entity.setUser(UserDto.toEntity(dto.getUser()));

		return entity;
	}

	public static FileInfoDto toDto(FileInfo entity) {
		if (entity == null) {
			return null;
		}

		FileInfoDto dto = new FileInfoDto();
		dto.setId(entity.getId());
		dto.setGid(entity.getGid());
		dto.setFileName(entity.getFileName());
		dto.setContentType(entity.getContentType());
		dto.setExtension(entity.getExtension());
		dto.setDone(entity.isDone());
		dto.setUser(UserDto.toDto(entity.getUser()));
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());

		return dto;
	}

}
