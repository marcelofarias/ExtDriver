package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractNumberFieldFixture<T extends AbstractTextFieldFixture<T>> extends AbstractTextFieldFixture<T> {

	public AbstractNumberFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public AbstractNumberFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public T spinUp() {
		getSpinUpElement().click();
		return (T) this;
	}
	
	public T spinDown() {
		getSpinDownElement().click();
		return (T) this;
	}
	
	private WebElement getSpinUpElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('div.x-form-spinner-up').elements[0]", getId());
	}
	
	private WebElement getSpinDownElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('div.x-form-spinner-down').elements[0]", getId());
	}

}
