package com.sencha.extjs.examples.form.dynamic;

import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.FormFixture;
import com.sencha.extjs.driver.TextFieldFixture;

public class Form1Fixture extends FormFixture {
	
	public Form1Fixture(ExtDriver driver) {
		super("simpleForm", driver);
	}

	public TextFieldFixture firstName() {
		return new TextFieldFixture(new TextFieldFixture.TextFieldLabelLocator("First Name:"), this, getDriver());
	}

	public TextFieldFixture lastName() {
		return new TextFieldFixture(new TextFieldFixture.TextFieldLabelLocator("Last Name:"), this, getDriver());
	}
	
	public TextFieldFixture company() {
		return new TextFieldFixture(new TextFieldFixture.TextFieldLabelLocator("Company:"), this, getDriver());
	}
	
	public TextFieldFixture email() {
		return new TextFieldFixture(new TextFieldFixture.TextFieldLabelLocator("Email:"), this, getDriver());
	}

}
