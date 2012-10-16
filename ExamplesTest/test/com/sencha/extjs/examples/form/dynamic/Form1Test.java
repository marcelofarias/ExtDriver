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
		form1.firstName().type("abc def");
	}

}
