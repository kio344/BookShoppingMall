package models.mypage.exception;

import common.error.CommonException;

public class PasswordChangeException extends CommonException{

	public PasswordChangeException() {
		super("passChange", "passwordChange");
	}
}
