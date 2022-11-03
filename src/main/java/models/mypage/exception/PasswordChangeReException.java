package models.mypage.exception;

import common.error.CommonException;

public class PasswordChangeReException extends CommonException{
	
	public PasswordChangeReException() {
		super("passwordRequest", "passwordChangeRe");
	}
	
}
