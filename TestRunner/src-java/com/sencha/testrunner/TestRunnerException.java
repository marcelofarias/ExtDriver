package com.sencha.testrunner;

public class TestRunnerException extends RuntimeException {

	private static final long serialVersionUID = -1824143661677563169L;

	public TestRunnerException(String string, Exception e) {
		super(string, e);
	}

}
