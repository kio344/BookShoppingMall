package models.qna;

import org.hibernate.validator.constraints.NotBlank;

public class QnARequest {
	
	private String poster;
	@NotBlank(message = "질문 제목을 입력해주세요.")
	private String subject;
	@NotBlank(message = "질문 내용을 입력해주세요.")
	private String question;
	private boolean isAnswer;
	private boolean isAdmin;

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

	public boolean isAnswer() {
		return isAnswer;
	}

	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
