package models.user;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class JoinRequest {

	@NotBlank
	@Size(min = 2)
	private String memId;
	@NotBlank
	@Size(min = 4)
	private String memPw;
	@NotBlank
	private String memPwRe;
	@NotBlank
	private String memNm;
	@NotBlank
	@Size(min = 2)
	private String fakeName;
	@NotBlank
	private String mobile;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String adress;
	@NotBlank
	private LocalDateTime birthDay;
	@NotBlank
	private String gender;
	@AssertTrue
	private boolean agreeTerms;

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

	public String getMemPwRe() {
		return memPwRe;
	}

	public void setMemPwRe(String memPwRe) {
		this.memPwRe = memPwRe;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getFakeName() {
		return fakeName;
	}

	public void setFakeName(String fakeName) {
		this.fakeName = fakeName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public LocalDateTime getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDateTime birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isAgreeTerms() {
		return agreeTerms;
	}

	public void setAgreeTerms(boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}

	@Override
	public String toString() {
		return "JoinRequest [memId=" + memId + ", memPw=" + memPw + ", memPwRe=" + memPwRe + ", memNm=" + memNm
				+ ", fakeName=" + fakeName + ", mobile=" + mobile + ", email=" + email + ", adress=" + adress
				+ ", birthDay=" + birthDay + ", gender=" + gender + ", agreeTerms=" + agreeTerms + "]";
	}

}
