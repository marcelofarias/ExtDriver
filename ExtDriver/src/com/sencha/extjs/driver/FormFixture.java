package com.sencha.extjs.driver;

public class FormFixture extends AbstractFormFixture<FormFixture> {

	public FormFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		super(id, parent, driver);
	}

	public FormFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	@Override
	public String getClassName() {
		return "Ext.form.Panel";
	}
	
}