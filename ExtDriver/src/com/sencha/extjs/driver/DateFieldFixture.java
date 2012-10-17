package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class DateFieldFixture extends AbstractDateFieldFixture<DateFieldFixture> {

	public DateFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public DateFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.field.Date";
	}
	
}
