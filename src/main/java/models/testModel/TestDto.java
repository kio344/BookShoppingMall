package models.testModel;

public class TestDto {
	private Long memNo;
	private String memId;
	private String memPw;
	private String memPwRe;
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
		return "testDto [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memPwRe=" + memPwRe + ", memNm="
				+ memNm + "]";
	}

}
