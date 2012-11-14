package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

public abstract class AbstractFormFixture<T extends AbstractFormFixture<T>> extends AbstractContainerFixture<T> {
	
	public AbstractFormFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	public AbstractFormFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractFormFixture() {
		
	}
	
	protected boolean isCollapsingOrExpanding() {
		Object isCollapsingOrExpanding = getDriver().executeScript("return Ext.getCmp(arguments[0]).isCollapsingOrExpanding", getId());
		return !(new Long(0).equals(isCollapsingOrExpanding));
	}

	protected WebElement getCollapseToolElement() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('.x-panel-header .x-tool img').elements[0]", getId());
	}
	
	protected String getTitle() {
		WebElement titleElement = (WebElement) getDriver().executeScript(
				"return Ext.get(arguments[0]).select('div.x-panel-header span.x-panel-header-text').elements[0]", getId()); 
		return titleElement.getText();
	}

}