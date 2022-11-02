package models.mypage;

public class PasswordRequest {

	private String password;
	private String changePassword;
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
