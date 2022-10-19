package models.admin.board;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class AdminBoardRequest {

	@NotBlank(message = "게시판 아이디가 없습니다.")
	private String boardId;
	@NotBlank(message = "게시판 이름이 없습니다.")
	private String boardName;
	@Min(value = 1, message = "페이지당 표시할 개수를 입력해 주세요.")
	private int pageCount = 20;
	private int isUse = 1;
	private int memberOnly = 1;
	private int commentUse = 1;

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
	
	
	public static AdminBoardDto toDto(AdminBoardRequest adminBoardRequest) {
		if(adminBoardRequest == null) return null;
		
		AdminBoardDto dto = new AdminBoardDto();
		
		dto.setBoardId(adminBoardRequest.getBoardId());
		dto.setBoardName(adminBoardRequest.getBoardName());
		dto.setPageCount(adminBoardRequest.getPageCount());
		dto.setIsUse(adminBoardRequest.getIsUse());
		dto.setMemberOnly(adminBoardRequest.getMemberOnly());
		dto.setCommentUse(adminBoardRequest.getCommentUse());
		
		return dto;
	}
}
