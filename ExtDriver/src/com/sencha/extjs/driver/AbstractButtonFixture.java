package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractButtonFixture<T extends AbstractButtonFixture<T>> extends ExtComponentFixture {
	
	public AbstractButtonFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractButtonFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}
	
	public T click() {
		getButtonElement().click();
		return (T) this;
	}
	
	private WebElement getButtonElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('button').elements[0]", getId());
	}

}
