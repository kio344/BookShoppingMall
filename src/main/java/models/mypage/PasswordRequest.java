package models.mypage;

import org.hibernate.validator.constraints.NotBlank;

public class PasswordRequest {
	
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
	@NotBlank(message = "변경하실 비밀번호를 입력해주세요.")
	private String changePassword;
	@NotBlank(message = "비밀번호를 확인해 주세요.")
	private String changePasswordRe;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	public String getChangePasswordRe() {
		return changePasswordRe;
	}

	public void setChangePasswordRe(String changePasswordRe) {
		this.changePasswordRe = changePasswordRe;
	}

	@Override
	public String toString() {
		return "PasswordRequest [password=" + password + ", changePassword=" + changePassword + ", changePasswordRe="
				+ changePasswordRe + "]";
	}
	
	

}
