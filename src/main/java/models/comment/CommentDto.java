package models.comment;

import models.board.BoardDataDto;
import models.common.BaseDto;
import models.entity.Comment;
import models.user.UserDto;

public class CommentDto extends BaseDto {

	private Long id;
	private String gid;
	private String poster;
	private String comments;
	private boolean isFirstComment;
	private Long matchComment;
	private UserDto user;
	private BoardDataDto board;

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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public BoardDataDto getBoard() {
		return board;
	}

	public void setBoard(BoardDataDto board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", gid=" + gid + ", poster=" + poster + ", comments=" + comments
				+ ", isFirstComment=" + isFirstComment + ", matchComment=" + matchComment + ", user=" + user
				+ ", board=" + board + "]";
	}

	public static Comment toEntity(CommentDto dto) {
		if (dto == null) {
			return null;
		}

		Comment entity = new Comment();
		entity.setPoster(dto.getPoster());
		entity.setGid(dto.getGid());
		entity.setComments(dto.getComments());
		entity.setFirstComment(dto.isFirstComment());
		entity.setMatchComment(dto.getMatchComment());
		entity.setUser(UserDto.toEntity(dto.getUser()));
		entity.setBoard(BoardDataDto.toEntity(dto.getBoard()));

		return entity;
	}

	public static CommentDto toDto(Comment entity) {
		if (entity == null) {
			return null;
		}

		CommentDto dto = new CommentDto();
		dto.setId(entity.getId());
		dto.setGid(entity.getGid());
		dto.setPoster(entity.getPoster());
		dto.setComments(entity.getComments());
		dto.setFirstComment(entity.isFirstComment());
		dto.setMatchComment(entity.getMatchComment());
		dto.setUser(UserDto.toDto(entity.getUser()));
		dto.setBoard(BoardDataDto.toDto(entity.getBoard()));
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());

		return dto;
	}
}
