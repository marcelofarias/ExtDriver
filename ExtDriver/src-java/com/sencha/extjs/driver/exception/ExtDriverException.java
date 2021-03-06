package com.sencha.extjs.driver.exception;

public class ExtDriverException extends RuntimeException {
	
	private static final long serialVersionUID = -788502357542534106L;

	public ExtDriverException(String message) {
		super(message);
	}
	
	public ExtDriverException(String message, Throwable cause) {
		super(message, cause);
	}

}
