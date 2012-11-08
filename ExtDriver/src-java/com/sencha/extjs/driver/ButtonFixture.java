package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class ButtonFixture extends AbstractButtonFixture<ButtonFixture> {

	public ButtonFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public ButtonFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.button.Button";
	}
	
}
