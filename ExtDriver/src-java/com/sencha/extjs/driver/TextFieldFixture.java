package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class TextFieldFixture extends AbstractTextFieldFixture<TextFieldFixture> {
	
	public TextFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	public TextFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.field.Text";
	}

}
