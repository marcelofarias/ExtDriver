package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractFieldSetFixture<T extends AbstractFieldSetFixture<T>> extends AbstractContainerFixture<T> {
	
	public AbstractFieldSetFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	public AbstractFieldSetFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractFieldSetFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	protected boolean isCollapsingOrExpanding() {
		return false;
	}

	protected WebElement getCollapseToolElement() {
		final String collapseToolElementQuery =
				" if (Ext.getCmp(arguments[0]).checkboxToggle) { " +
				"     return Ext.get(arguments[0]).select('legend.x-fieldset-header input.x-form-checkbox').elements[0]; " +
				" } else { " +
				"     return Ext.get(arguments[0]).select('legend.x-fieldset-header img.x-tool-toggle').elements[0]; " +
				" } ";
		return (WebElement) getDriver().executeScript(collapseToolElementQuery, getId());
	}
	
	protected String getTitle() {
		WebElement titleElement = (WebElement) getDriver().executeScript(
				"return Ext.get(arguments[0]).select('legend:x-fieldset-header div:x-fieldset-header-text').elements[0]", getId()); 
		return titleElement.getText();
	}

}