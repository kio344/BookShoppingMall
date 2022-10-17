package models.user.exception;

import common.error.CommonException;

public class UserNotFoundException extends CommonException{
	public UserNotFoundException() {
		super("UserNotFound", "memId");
	}
	
	
}
