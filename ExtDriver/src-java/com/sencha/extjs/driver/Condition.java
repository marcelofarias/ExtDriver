package com.sencha.extjs.driver;

import com.sencha.extjs.driver.exception.ExtDriverAssertionError;
import com.sencha.extjs.driver.exception.ExtDriverException;

public abstract class Condition {
	
	public static long CONDITION_TIMEOUT = 5000;
	
	public void waitUntilSatisfied() {
		waitUntilSatisfied(CONDITION_TIMEOUT, 100);
	}
	
	public void waitUntilSatisfied(long timeout, long sleepInterval) {
		long millisecondsAtStart = System.currentTimeMillis();
		while (System.currentTimeMillis() - millisecondsAtStart <= timeout) {
			if (isSatisfied()) {
				return;
			}
			try {
				Thread.sleep(sleepInterval);
			} catch (InterruptedException e) {
				throw new ExtDriverException("Unexpected error while waiting for condition", e);
			}
		}
		throw new ExtDriverAssertionError(getErrorMessage());
	}
	
	public abstract boolean isSatisfied();
	
	public abstract String getErrorMessage();
	
}
