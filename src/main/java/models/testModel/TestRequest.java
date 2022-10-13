package models.testModel;

import org.hibernate.validator.constraints.NotBlank;

public class TestRequest {

	private Long memNo;
	@NotBlank
	private String memId;
	@NotBlank
	private String memPw;
	@NotBlank
	private String memPwRe;
	@NotBlank
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

	@Override
	public String toString() {
		return "testRequest [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memPwRe=" + memPwRe
				+ ", memNm=" + memNm + "]";
	}

}
