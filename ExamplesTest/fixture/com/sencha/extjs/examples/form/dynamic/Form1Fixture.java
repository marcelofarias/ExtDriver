package com.sencha.extjs.examples.form.dynamic;

import com.sencha.extjs.driver.ButtonFixture;
import com.sencha.extjs.driver.DateFieldFixture;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.FormFixture;
import com.sencha.extjs.driver.NumberFieldFixture;
import com.sencha.extjs.driver.TextFieldFixture;
import com.sencha.extjs.driver.TimeFieldFixture;
import com.sencha.extjs.driver.locator.ButtonLabelLocator;
import com.sencha.extjs.driver.locator.FieldLabelLocator;

public class Form1Fixture extends FormFixture {
	
	public Form1Fixture(ExtDriver driver) {
		super("simpleForm", driver);
	}

	public TextFieldFixture firstName() {
		return new TextFieldFixture(new FieldLabelLocator("First Name:"), this, getDriver());
	}

	public TextFieldFixture lastName() {
		return new TextFieldFixture(new FieldLabelLocator("Last Name:"), this, getDriver());
	}
	
	public TextFieldFixture company() {
		return new TextFieldFixture(new FieldLabelLocator("Company:"), this, getDriver());
	}
	
	public TextFieldFixture email() {
		return new TextFieldFixture(new FieldLabelLocator("Email:"), this, getDriver());
	}

	public DateFieldFixture dob() {
		return new DateFieldFixture(new FieldLabelLocator("DOB:"), this, getDriver());
	}

	public NumberFieldFixture age() {
		return new NumberFieldFixture(new FieldLabelLocator("Age:"), this, getDriver());
	}
	
	public TimeFieldFixture time() {
		return new TimeFieldFixture(new FieldLabelLocator("Time:"), this, getDriver());
	}
	
	public ButtonFixture cancel() {
		return new ButtonFixture(new ButtonLabelLocator("Cancel"), this, getDriver());
	}
	
	public ButtonFixture save() {
		return new ButtonFixture(new ButtonLabelLocator("Save"), this, getDriver());
	}

}
