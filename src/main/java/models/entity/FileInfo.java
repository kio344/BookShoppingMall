package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FileInfo extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 45, nullable = false)
	private String gid;
	@Column(length = 100, nullable = false)
	private String fileName;
	@Column(length = 60, nullable = false)
	private String contentType;
	@Column(length = 15, nullable = false)
	private String extension;
	private boolean done;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memNo")
	private User user; // 회원 Entity(파일 삭제시 소유를 체크)

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
