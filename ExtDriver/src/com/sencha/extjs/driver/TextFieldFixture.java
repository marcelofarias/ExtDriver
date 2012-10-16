package com.sencha.extjs.driver;

public class TextFieldFixture extends AbstractTextFieldFixture<TextFieldFixture> {
	
	public TextFieldFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		super(id, parent, driver);
	}

	public TextFieldFixture(com.sencha.extjs.driver.AbstractTextFieldFixture.TextFieldLocator locator, ExtComponentFixture parent, ExtDriver driver) {
		super(locator, parent, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.field.Text";
	}

}
