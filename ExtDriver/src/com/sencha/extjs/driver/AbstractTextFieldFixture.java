package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

public abstract class AbstractTextFieldFixture<T extends AbstractTextFieldFixture<T>> extends ExtComponentFixture {

	public AbstractTextFieldFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		super(id, parent, driver);
	}
	
	public AbstractTextFieldFixture(TextFieldLocator locator, ExtComponentFixture parent, ExtDriver driver) {
		super(locator.getId(parent, driver), parent, driver);
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
	
	private WebElement getInputElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('input').elements[0]", getId());
	}
	
	public static interface TextFieldLocator {
		public String getId(ExtComponentFixture scope, ExtDriver driver);
	}
	
	public static class TextFieldLabelLocator implements TextFieldLocator {
		private String label;
		
		public TextFieldLabelLocator(String label) {
			this.label = label;
		}
		
		public String getId(ExtComponentFixture parent, ExtDriver driver) {
			if (parent != null) {
				return (String) driver.executeScript(
						"return Ext.get(arguments[0]).select('tbody.x-field:has(label:contains(' + arguments[1] + '))').elements[0].id",
						parent.getId(),
						label.replaceAll("\\s", "\\\\ "));
			} else {
				return (String) driver.executeScript(
						"return Ext.dom.Query.select('tbody.x-field:has(label:contains(' + arguments[1] + '))').elements[0].id",
						label.replaceAll("\\s", "\\\\ "));
			}
		}
	}

}
