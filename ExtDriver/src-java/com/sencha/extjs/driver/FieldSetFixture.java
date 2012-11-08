package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public class FieldSetFixture extends AbstractFieldSetFixture<FieldSetFixture> {

	public FieldSetFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	public FieldSetFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public FieldSetFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.FieldSet";
	}

	@Override
	protected String getContainerPresentationName() {
		return "FieldSet";
	}
	
}
