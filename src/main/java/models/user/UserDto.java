package models.user;

import java.time.LocalDateTime;

import models.common.BaseDto;
import models.entity.User;

public class UserDto extends BaseDto {

	private Long memNo;
	private String memId;
	private String memPw;
	private String memNm;
	private String fakeName;
	private String mobile;
	private String email;
	private String adress;
	private LocalDateTime birthDay;
	private String gender;
	private UserType userType;

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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		if (userType == null)
			userType = UserType.USER;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDto [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memNm=" + memNm + ", fakeName="
				+ fakeName + ", mobile=" + mobile + ", email=" + email + ", adress=" + adress + ", birthDay=" + birthDay
				+ ", gender=" + gender + ", userType=" + userType + ", getRegDt()=" + getRegDt() + ", getModDt()="
				+ getModDt() + "]";
	}

	public static User toEntity(UserDto user) {
		if (user == null) {
			return null;
		}

		User entity = new User();

		entity.setMemId(entity.getMemId());
		entity.setMemPw(entity.getMemPw());
		entity.setMemNm(entity.getMemNm());
		entity.setFakeName(entity.getFakeName());
		entity.setMobile(entity.getMobile());
		entity.setEmail(entity.getEmail());
		entity.setAdress(entity.getAdress());
		entity.setBirthDay(entity.getBirthDay());
		entity.setGender(entity.getGender());

		return entity;

	}

	public static UserDto toDto(User entity) {
		if (entity == null) {
			return null;
		}

		UserDto user = new UserDto();

		user.setMemId(entity.getMemId());
		user.setMemPw(entity.getMemPw());
		user.setMemNm(entity.getMemNm());
		user.setFakeName(entity.getFakeName());
		user.setMobile(entity.getMobile());
		user.setEmail(entity.getEmail());
		user.setAdress(entity.getAdress());
		user.setBirthDay(entity.getBirthDay());
		user.setGender(entity.getGender());
		user.setRegDt(entity.getRegDt());
		user.setModDt(entity.getModDt());
		user.setUserType(entity.getUserType());

		return user;

	}

}
