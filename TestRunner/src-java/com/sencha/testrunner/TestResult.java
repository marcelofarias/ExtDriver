package com.sencha.testrunner;

public class TestResult {
	
	private static final String toStringFormat =
			"{ description: '%s', passed: %b }";

	private String description;
	private Boolean passed;

	public TestResult(String description, Boolean passed) {
		this.description = description;
		this.passed = passed;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getPassed() {
		return passed;
	}
	
	public String toString() {
		return String.format(toStringFormat, description, passed);
	}

}
