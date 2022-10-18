package models.user.exception;

import common.error.CommonException;

public class DuplicatedUserException extends CommonException {
	public DuplicatedUserException() {
		super("DuplicatedUser", "memId");
	}
}
