package models.user.exception;

import common.error.CommonException;

public class PasswordIncorrectException extends CommonException{

	public PasswordIncorrectException() {
		super("PasswordIncorrect", "memPw");
	}
	
}
