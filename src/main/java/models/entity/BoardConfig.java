package models.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BoardConfig extends BaseEntity {

	@Id
	@Column(length = 45)
	private String boardId;
	@Column(length = 45, unique = true)
	private String boardName;
	@Column(length = 255)
	private int pageCount;
	@Column(columnDefinition = "TINYINT(1)", length = 1)
	private int isUse;
	@Column(columnDefinition = "TINYINT(1)", length = 1)
	private int memberOnly;
	@Column(columnDefinition = "TINYINT(1)", length = 1)
	private int commentUse;

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public int getMemberOnly() {
		return memberOnly;
	}

	public void setMemberOnly(int memberOnly) {
		this.memberOnly = memberOnly;
	}

	public int getCommentUse() {
		return commentUse;
	}

	public void setCommentUse(int commentUse) {
		this.commentUse = commentUse;
	}

	@Override
	public String toString() {
		return "AdminBoard [boardId=" + boardId + ", boardName=" + boardName + ", pageCount=" + pageCount + ", isUse="
				+ isUse + ", memberOnly=" + memberOnly + ", commentUse=" + commentUse + ", getRegDt()=" + getRegDt()
				+ ", getModDt()=" + getModDt() + "]";
	}

}
