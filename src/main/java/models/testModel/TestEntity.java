package models.testModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {
	@Id
	@GeneratedValue
	private Long memNo;
	@Column(nullable = false)
	private String memId;
	@Column(nullable = false)
	private String memPw;
	@Column(nullable = false)
	private String memPwRe;
	@Column(nullable = false)
	private String memNm;

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

}
