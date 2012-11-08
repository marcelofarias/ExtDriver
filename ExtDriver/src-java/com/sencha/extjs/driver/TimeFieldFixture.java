package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class TimeFieldFixture extends AbstractTimeFieldFixture<TimeFieldFixture> {

	public TimeFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public TimeFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.field.Time";
	}
	
}
