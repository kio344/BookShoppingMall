package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 65, nullable = false)
	private String gid;
	@Column(length = 45, nullable = false)
	private String poster;
	@Column(nullable = false)
	private String comments;
	private boolean isFirstComment;
	private Long matchComment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memId")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board")
	private BoardData board;

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

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isFirstComment() {
		return isFirstComment;
	}

	public void setFirstComment(boolean isFirstComment) {
		this.isFirstComment = isFirstComment;
	}

	public Long getMatchComment() {
		return matchComment;
	}

	public void setMatchComment(Long matchComment) {
		this.matchComment = matchComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BoardData getBoard() {
		return board;
	}

	public void setBoard(BoardData board) {
		this.board = board;
	}

}
