package models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class UserCategory extends BaseEntity{
	
	@Id @GeneratedValue
	private Long num;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	
	@Lob
	private String myCategoryData;

	
	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMyCategoryData() {
		return myCategoryData;
	}

	public void setMyCategoryData(String myCategoryData) {
		this.myCategoryData = myCategoryData;
	}

	
	
}
