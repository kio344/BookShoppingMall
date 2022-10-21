package models.board;

import org.hibernate.validator.constraints.NotBlank;

public class BoardDataRequest {

	@NotBlank(message = "게시글 제목이 입력되지 않았습니다.")
	private String subject;
	@NotBlank(message = "게시글 작성자가 입력되지 않았습니다.")
	private String poster;
	@NotBlank(message = "게시글 내용이 입력되지 않았습니다.")
	private String contents;
	private Long id;
	private String gid;

	private String boardId;

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGid() {
		if (gid == null) {
			gid = "" + System.currentTimeMillis();
		}
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

}
