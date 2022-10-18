package models.user.exception;

import common.error.CommonException;

public class MobilePatternException extends CommonException {
	public MobilePatternException() {
		super("MobilePattern", "mobile");
	}
}
