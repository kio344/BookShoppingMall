package models.board;

import org.hibernate.validator.constraints.NotBlank;

public class BoardPrivateRequest {

	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	private Long id;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
