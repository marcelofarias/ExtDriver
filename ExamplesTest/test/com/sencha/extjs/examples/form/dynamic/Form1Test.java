package com.sencha.extjs.examples.form.dynamic;

import org.junit.AfterClass;
import org.junit.Test;

public class Form1Test {
	
	@AfterClass
	public static void quitBrowser() {
		DynamicFormsFixture.getInstance().quit();
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
	}

}
