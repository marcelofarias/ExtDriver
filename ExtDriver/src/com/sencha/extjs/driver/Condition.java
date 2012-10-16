package com.sencha.extjs.driver;

public abstract class Condition {
	
	public static long CONDITION_TIMEOUT = 30000;
	
	public void waitUntilSatisfied(String timeoutErrorMessage) {
		long millisecondsAtStart = System.currentTimeMillis();
		while (System.currentTimeMillis() - millisecondsAtStart <= CONDITION_TIMEOUT) {
			if (isSatisfied()) {
				return;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new ExtDriverException("Unexpected error while waiting for condition", e);
			}
		}
		throw new ExtDriverException(timeoutErrorMessage);
	}
	
	public abstract boolean isSatisfied();
	
}
