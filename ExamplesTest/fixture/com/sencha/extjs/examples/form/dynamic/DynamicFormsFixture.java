package com.sencha.extjs.examples.form.dynamic;

import com.sencha.extjs.driver.ExtDriver;

public class DynamicFormsFixture {
	
	private ExtDriver driver;
	
	private DynamicFormsFixture() {
		driver = new ExtDriver("http://qa.sencha.com/knightly/qa/ext-4.1-20121014/examples/form/dynamic.html");
	}

	private static DynamicFormsFixture instance;

	public static DynamicFormsFixture getInstance() {
		if (instance == null) {
			instance = new DynamicFormsFixture();
		}
		return instance;
	}

	public Form1Fixture form1() {
		return new Form1Fixture(driver);
	}
	
	public void quit() {
		driver.getWebDriver().close();
		driver.getWebDriver().quit();
	}

}