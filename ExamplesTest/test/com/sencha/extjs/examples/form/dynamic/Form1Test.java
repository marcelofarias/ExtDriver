package com.sencha.extjs.examples.form.dynamic;

import org.junit.Test;

public class Form1Test {
	
	@Test
	public void formHasCorrectTitle() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		form1.requireTitle("Simple Form");
	}
	
	@Test
	public void formCanBeCollapsedAndExpanded() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		form1
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded();
	}
	
	@Test
	public void formCanBeCollapsedAndExpandedALotOfTimes() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		form1
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand()
			.requireExpanded();
	}
	
	@Test
	public void weCanTypeOnTextFields() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		
		form1.firstName()
			.requireEmpty()
			.type("Marcelo")
			.requireText("Marcelo");
		form1.lastName()
			.requireEmpty()
			.type("Bukowski de Farias")
			.requireText("Bukowski de Farias");
		form1.company()
			.requireEmpty()
			.type("Sencha Inc.")
			.requireText("Sencha Inc.");
		form1.email()
			.requireEmpty()
			.type("marcelo.farias@sencha.com")
			.requireText("marcelo.farias@sencha.com");
		form1.dob()
			.requireEmpty()
			.type("10/15/1981")
			.requireText("10/15/1981");
	}
	
	@Test
	public void spinUpAndDownOnNumberFields() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		
		form1.age()
			.type("31")
			.spinDown()
			.requireText("30")
			.spinUp()
			.requireText("31")
			.spinUp()
			.spinUp()
			.spinUp()
			.spinUp()
			.spinUp()
			.requireText("36")
			.spinDown()
			.spinDown()
			.spinDown()
			.spinDown()
			.spinDown()
			.requireText("31");
	}
	
	@Test
	public void selectOptionOnTimeField() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		
		form1.time()
			.requireEmpty()
			.selectOption("4:15 PM")
			.requireText("4:15 PM");
	}
	
	@Test
	public void requiredFieldsAreValidated() {
		Form1Fixture form1 = DynamicFormsFixture.getInstance().form1();
		
		form1.cancel().click();
		form1.save().click();
		
		form1.firstName()
			.requireInvalid()
			.requireErrorMessage("This field is required");
		form1.lastName()
			.requireInvalid()
			.requireErrorMessage("This field is required 2");
		form1.email()
			.requireInvalid()
			.requireErrorMessage("This field is required");
		
		form1.cancel().click();
	}

}
