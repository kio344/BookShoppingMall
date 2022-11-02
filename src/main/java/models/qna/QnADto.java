package models.qna;

import models.common.BaseDto;
import models.entity.QnA;
import models.user.UserDto;

public class QnADto extends BaseDto {

	private Long id;
	private String poster;
	private String subject;
	private String question;
	private String adminName;
	private String answer;
	private boolean isAnswered;
	private boolean isAdmin;
	private UserDto user;

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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "QnADto [id=" + id + ", poster=" + poster + ", subject=" + subject + ", question=" + question
				+ ", adminName=" + adminName + ", answer=" + answer + ", isAnswered=" + isAnswered + ", isAdmin=" + isAdmin
				+ ", user=" + user + "]";
	}

	public static QnA toEntity(QnADto dto) {
		if (dto == null) {
			return null;
		}
		QnA entity = new QnA();
		entity.setPoster(dto.getPoster());
		entity.setSubject(dto.getSubject());
		entity.setQuestion(dto.getQuestion());
		entity.setAdminName(dto.getAdminName());
		entity.setAnswer(dto.getAnswer());
		entity.setAnswered(dto.isAnswered());
		entity.setAdmin(dto.isAdmin());
		entity.setUser(UserDto.toEntity(dto.getUser()));

		return entity;
	}
	
	public static QnADto toDto(QnA entity) {
		if (entity == null) {
			return null;
		}
		QnADto dto = new QnADto();
		dto.setId(entity.getId());
		dto.setPoster(entity.getPoster());
		dto.setSubject(entity.getSubject());
		dto.setQuestion(entity.getQuestion());
		dto.setAdminName(entity.getAdminName());
		dto.setAnswer(entity.getAnswer());
		dto.setAnswered(entity.isAnswered());
		dto.setAdmin(entity.isAdmin());
		dto.setUser(UserDto.toDto(entity.getUser()));
		dto.setRegDt(entity.getRegDt());
		dto.setModDt(entity.getModDt());

		return dto;
	}

}
