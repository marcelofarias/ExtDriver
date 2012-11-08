package com.sencha.extjs.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractTimeFieldFixture<T extends AbstractTimeFieldFixture<T>> extends AbstractTextFieldFixture<T> {
	
	public AbstractTimeFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractTimeFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}
	
	public T selectOption(String optionLabel) {
		getTriggerElement().click();
		WebElement optionElement = getOptionElement(optionLabel);
		
		// TODO it seems to be automatic
		// getDriver().scrollIntoView(optionElement);
		
		optionElement.click();
 
		return (T) this;
	}
	
	private WebElement getTriggerElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('div.x-form-trigger').elements[0]", getId());
	}
	
	private WebElement getOptionElement(String label) {
		WebElement optionListElement = (WebElement) getDriver().executeScript("return Ext.dom.Query.select('div.x-layer.x-timepicker ul')[0]");
		return (WebElement) optionListElement.findElement(By.xpath(String.format("//li[text() = '%s']", label)));
	}

}
