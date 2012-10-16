package com.sencha.extjs.driver;

public abstract class ExtComponentFixture {
	
	private String id;
	private ExtComponentFixture parent;
	private ExtDriver driver;
	
	public ExtComponentFixture(String id, ExtDriver driver) {
		this(id, null, driver);
	}
	
	public ExtComponentFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		this.id = id;
		this.parent = parent;
		this.driver = driver;
		requireClassName(getClassName());
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
	
	public ExtComponentFixture getParent() {
		return parent;
	}
	
	public ExtDriver getDriver() {
		return driver;
	}
	

}
