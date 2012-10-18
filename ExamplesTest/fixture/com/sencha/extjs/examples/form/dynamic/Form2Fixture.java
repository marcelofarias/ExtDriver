package com.sencha.extjs.examples.form.dynamic;

import com.sencha.extjs.driver.ButtonFixture;
import com.sencha.extjs.driver.ExtComponentFixture;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.FieldSetFixture;
import com.sencha.extjs.driver.FormFixture;
import com.sencha.extjs.driver.TextFieldFixture;
import com.sencha.extjs.driver.locator.ButtonLabelLocator;
import com.sencha.extjs.driver.locator.ComponentLocator;
import com.sencha.extjs.driver.locator.FieldLabelLocator;
import com.sencha.extjs.driver.locator.FieldSetTitleLocator;

public class Form2Fixture extends FormFixture {

	public Form2Fixture(ExtDriver driver) {
		super("fieldSetForm", driver);
	}
	
	public UserInformationFixture userInformation() {
		return new UserInformationFixture(new FieldSetTitleLocator("User Information"), this, getDriver());
	}
	
	public PhoneNumberFixture phoneNumber() {
		return new PhoneNumberFixture(new FieldSetTitleLocator("Phone Number"), this, getDriver());
	}
	
	public ButtonFixture save() {
		return new ButtonFixture(new ButtonLabelLocator("Save"), this, getDriver());
	}
	
	public ButtonFixture cancel() {
		return new ButtonFixture(new ButtonLabelLocator("Cancel"), this, getDriver());
	}
	
	public static class UserInformationFixture extends FieldSetFixture {

		public UserInformationFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
			super(locator, scope, driver);
		}
		
		public TextFieldFixture firstName() {
			return new TextFieldFixture(new FieldLabelLocator("First Name"), this, getDriver());
		}
		
		public TextFieldFixture lastName() {
			return new TextFieldFixture(new FieldLabelLocator("Last Name"), this, getDriver());
		}
		
		public TextFieldFixture company() {
			return new TextFieldFixture(new FieldLabelLocator("Company Name"), this, getDriver());
		}
		
		public TextFieldFixture email() {
			return new TextFieldFixture(new FieldLabelLocator("Email"), this, getDriver());
		}
		
	}
	
	public static class PhoneNumberFixture extends FieldSetFixture {
		
		public PhoneNumberFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
			super(locator, scope, driver);
		}
		
		public TextFieldFixture home() {
			return new TextFieldFixture(new FieldLabelLocator("Home"), this, getDriver());
		}
		
		public TextFieldFixture business() {
			return new TextFieldFixture(new FieldLabelLocator("Business"), this, getDriver());
		}
		
		public TextFieldFixture mobile() {
			return new TextFieldFixture(new FieldLabelLocator("Mobile"), this, getDriver());
		}
		
		public TextFieldFixture fax() {
			return new TextFieldFixture(new FieldLabelLocator("Fax"), this, getDriver());
		}
		
	}

}
