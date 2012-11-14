package com.sencha.extjs.driver;

public class FormFixture extends AbstractFormFixture<FormFixture> {

	public FormFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

	public FormFixture(String id, ExtDriver driver) {
		super(id, driver);
	}
	
	public FormFixture() {
		
	}

	@Override
	public String getClassName() {
		return "Ext.form.Panel";
	}

	@Override
	protected String getContainerPresentationName() {
		return "Form";
	}
	
}
