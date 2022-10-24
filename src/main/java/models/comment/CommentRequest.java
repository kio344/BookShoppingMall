package models.comment;

import org.hibernate.validator.constraints.NotBlank;


public class CommentRequest {

	private Long id;
	@NotBlank(message = "작성자를 입력해주세요.")
	private String poster;
	@NotBlank(message = "댓글 내용을 입력해주세요.")
	private String comments;
	private boolean isFirstComment;
	private Long matchComment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
