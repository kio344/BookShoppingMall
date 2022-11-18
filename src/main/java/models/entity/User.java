package models.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import models.user.UserType;

@Entity
public class User extends BaseEntity {

	@Id
	@GeneratedValue
	private Long memNo;
	@Column(length = 45, unique = true, nullable = false)
	private String memId;
	@Column(length = 60, unique = true, nullable = false)
	private String memPw;
	@Column(length = 40, nullable = false)
	private String memNm;
	@Column(length = 45, nullable = false)
	private String fakeName;
	@Column(length = 11, nullable = false)
	private String mobile;
	@Column(length = 100, nullable = false)
	private String email;
	@Column(nullable = false)
	private String adress;
	@Column(nullable = false)
	private String birthDay;
	@Column(length = 4, nullable = false)
	private String gender;
	@Column(length = 7)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	private Long kakaoId;

	public Long getMemNo() {
		return memNo;
	}

	public void setMemNo(Long memNo) {
		this.memNo = memNo;
	}

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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserType getUserType() {
		return userType;
	}
	
	public void setUserType(UserType userType) {
		if(userType == null) userType = UserType.USER;
		this.userType = userType;
	}
	
	

	public Long getKakaoId() {
		return kakaoId;
	}

	public void setKakaoId(Long kakaoId) {
		this.kakaoId = kakaoId;
	}

	@Override
	public String toString() {
		return "User [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memNm=" + memNm + ", fakeName="
				+ fakeName + ", mobile=" + mobile + ", email=" + email + ", adress=" + adress + ", birthDay=" + birthDay
				+ ", gender=" + gender + ", userType=" + userType + ", kakaoId=" + kakaoId + "]";
	}
	
	

}
