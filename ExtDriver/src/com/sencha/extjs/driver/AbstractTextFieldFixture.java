package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

public abstract class AbstractTextFieldFixture<T extends AbstractTextFieldFixture<T>> extends ExtComponentFixture {

	public AbstractTextFieldFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		super(id, parent, driver);
	}
	
	public AbstractTextFieldFixture(TextFieldLocator locator, ExtComponentFixture parent, ExtDriver driver) {
		super(locator.getId(parent, driver), parent, driver);
	}
	
	public T type(String string) {
		getInputElement().sendKeys(string);
		return (T) this;
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
