package models.user;

import org.hibernate.validator.constraints.NotBlank;

public class LoginRequest {

	@NotBlank
	private String memId;
	@NotBlank
	private String memPw;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	@Override
	public String toString() {
		return "LoginRequest [memId=" + memId + ", memPw=" + memPw + "]";
	}

}
