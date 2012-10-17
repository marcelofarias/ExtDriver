package com.sencha.extjs.examples.form.dynamic;

import org.junit.Test;

public class Form2Test {
	
	@Test
	public void formHasCorrectTitle() {
		Form2Fixture form2 = DynamicFormsFixture.getInstance().form2();
		form2.requireTitle("Simple Form with FieldSets");
	}
	
	@Test
	public void fieldsetsCanBeCollapsedAndExpanded() {
		Form2Fixture form2 = DynamicFormsFixture.getInstance().form2();
		form2.userInformation()
			.requireCollapsed()
			.expand()
			.requireExpanded()
			.collapse();
		form2.phoneNumber()
			.requireExpanded()
			.collapse()
			.requireCollapsed()
			.expand();
		form2.getDriver().sleep(5000);
	}

}
