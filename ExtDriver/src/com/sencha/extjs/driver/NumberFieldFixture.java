package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class NumberFieldFixture extends AbstractNumberFieldFixture<NumberFieldFixture> {

	public NumberFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public NumberFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.field.Number";
	}

}
