package models.admin.board;

import models.common.BaseDto;
import models.entity.BoardConfig;

public class AdminBoardDto extends BaseDto {

	private String boardId;
	private String boardName;
	private int pageCount;
	private int isUse;
	private int memberOnly;
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
		return "AdminBoardDto [boardId=" + boardId + ", boardName=" + boardName + ", pageCount=" + pageCount
				+ ", isUse=" + isUse + ", memberOnly=" + memberOnly + ", commentUse=" + commentUse + "]";
	}

	public static BoardConfig toEntity(AdminBoardDto dto) {
		BoardConfig entity = new BoardConfig();
		if(dto == null) return null;
		
		entity.setBoardId(dto.getBoardId());
		entity.setBoardName(dto.getBoardName());
		entity.setCommentUse(dto.getCommentUse());
		entity.setIsUse(dto.getIsUse());
		entity.setMemberOnly(dto.getMemberOnly());
		entity.setRegDt(dto.getRegDt());
		entity.setModDt(dto.getModDt());
		entity.setPageCount(dto.getPageCount());
		return entity;
	}
	
	public static AdminBoardDto toDto(BoardConfig entity) {
		AdminBoardDto dto = new AdminBoardDto();
		if(entity == null ) return null;
		
		dto.setBoardId(entity.getBoardId());
		dto.setBoardName(entity.getBoardName());
		dto.setCommentUse(entity.getCommentUse());
		dto.setIsUse(entity.getIsUse());
		dto.setMemberOnly(entity.getMemberOnly());
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());
		dto.setPageCount(entity.getPageCount());
		
		return dto;
	}
	
}




