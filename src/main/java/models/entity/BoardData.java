package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoardData extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 45, nullable = false)
	private String gid;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false, length = 20)
	private String poster;
	@Column(nullable = false)
	private String contents;
	private Long hit;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardId")
	private BoardConfig board;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memId")
	private User user;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
		this.hit = hit;
	}

	public BoardConfig getBoard() {
		return board;
	}

	public void setBoard(BoardConfig board) {
		this.board = board;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
