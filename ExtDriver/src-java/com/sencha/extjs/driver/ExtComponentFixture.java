package com.sencha.extjs.driver;

import com.sencha.extjs.driver.exception.ExtDriverException;
import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class ExtComponentFixture {
	
	private String id;
	private ExtComponentFixture scope;
	private ExtDriver driver;
	
	public ExtComponentFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		this.id = id;
		this.scope = scope;
		this.driver = driver;
		requireClassName(getClassName());
	}
	
	public ExtComponentFixture(String id, ExtDriver driver) {
		this(id, null, driver);
	}
	
	public ExtComponentFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		this(locator.getId(scope, driver), scope, driver);
	}
	
	private ExtComponentFixture requireClassName(String className) {
		String actualClassName = (String) driver.executeScript("return Ext.getCmp(arguments[0]).$className", id);
		if (!actualClassName.equals(className)) {
			throw new ExtDriverException(String.format("Component with id %s is not a valid instance of %s", id, className));
		}
		return this;
	}
	
	protected void waitForCondition(Condition condition, String timeoutErrorMessage) {
		long millisecondsAtStart = System.currentTimeMillis();
		while (System.currentTimeMillis() - millisecondsAtStart <= Condition.CONDITION_TIMEOUT) {
			
		}
	}
	
	public abstract String getClassName();
	
	public String getId() {
		return id;
	}
	
	public ExtComponentFixture getScope() {
		return scope;
	}
	
	public ExtDriver getDriver() {
		return driver;
	}
	

}
