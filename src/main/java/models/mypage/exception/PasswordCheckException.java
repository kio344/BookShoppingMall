package models.mypage.exception;

import common.error.CommonException;

public class PasswordCheckException extends CommonException{
	
	public PasswordCheckException() {
		super("passwordRequest", "password");
	}
}
