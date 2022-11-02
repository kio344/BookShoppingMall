package models.user;

public class UserRequest {
	private Long memNo;
	private String memNm;
	private String fakeName;
	private String email;
	private String gender;
	private String adress;
	private String birthDay;
	private String mobile;

	public Long getMemNo() {
		return memNo;
	}

	public void setMemNo(Long memNo) {
		this.memNo = memNo;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UserRequest [memNm=" + memNm + ", fakeName=" + fakeName + ", email=" + email + ", gender=" + gender
				+ ", adress=" + adress + ", birthDay=" + birthDay + ", mobile=" + mobile + "]";
	}

}
