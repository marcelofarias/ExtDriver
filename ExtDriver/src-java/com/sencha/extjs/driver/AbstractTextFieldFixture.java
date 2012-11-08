package com.sencha.extjs.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sencha.extjs.driver.exception.ExtDriverAssertionError;
import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractTextFieldFixture<T extends AbstractTextFieldFixture<T>> extends ExtComponentFixture {

	public AbstractTextFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractTextFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}
	
	public T type(String text) {
		getInputElement().sendKeys(text);
		return (T) this;
	}
	
	public T requireText(final String text) {
		new Condition() {
			@Override
			public boolean isSatisfied() {
				return getInputElement().getAttribute("value").equals(text);
			}
			@Override
			public String getErrorMessage() {
				return String.format("Expected text [%s], but found [%s]", text, getInputElement().getAttribute("value"));
			}
		}.waitUntilSatisfied();
		return (T) this;
	}
	
	public T requireEmpty() {
		return requireText("");
	}
	
	public T requireInvalid() {
		new Condition() {
			@Override
			public String getErrorMessage() {
				throw new ExtDriverAssertionError("Field should be invalid");
			}
			@Override
			public boolean isSatisfied() {
				return getInputElement().getAttribute("class").indexOf("x-form-invalid-field") > -1;
			}
		}.waitUntilSatisfied();
		
		return (T) this;
	}
	
	public T requireErrorMessage(final String errorMessage) {
		new Condition() {
			private String actualErrorMessage = "";
			private boolean mouseOvered = false;
			@Override
			public boolean isSatisfied() {
				if (!mouseOvered) {
					WebElement messageTargetElement = getMessageTargetElement();
					if (!messageTargetElement.isDisplayed()) {
						return false;
					}
					getDriver().sleep(100);
					getDriver().mouseOver(messageTargetElement);
					mouseOvered = true;
				}
				WebElement errorTipElement = getErrorTipElement();
				if (errorTipElement == null || !errorTipElement.isDisplayed()) {
					return false;
				}
				
				actualErrorMessage = errorTipElement.findElement(By.tagName("li")).getText();
				return actualErrorMessage.equals(errorMessage);
			}
			@Override
			public String getErrorMessage() {
				return String.format("Expected error message [%s], but found [%s]", errorMessage, actualErrorMessage);
			}
		}.waitUntilSatisfied();
		return (T) this;
	}
	
	private WebElement getErrorTipElement() {
		return (WebElement) getDriver().executeScript("return Ext.dom.Query.select('div.x-form-invalid-tip')[0]");
	}
	
	private WebElement getInputElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('input').elements[0]", getId());
	}
	
	private WebElement getMessageTargetElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('div.x-form-error-msg').elements[0]", getId());
	}

}
