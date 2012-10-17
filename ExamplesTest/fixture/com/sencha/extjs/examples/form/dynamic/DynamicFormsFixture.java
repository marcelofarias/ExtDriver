package com.sencha.extjs.examples.form.dynamic;

import com.sencha.extjs.driver.ExtDriver;

public class DynamicFormsFixture {

	private static DynamicFormsFixture instance;
	
	private ExtDriver driver;
	
	private DynamicFormsFixture(ExtDriver driver) {
		this.driver = driver;
	}
	
	public static void createInstance(ExtDriver driver) {
		instance = new DynamicFormsFixture(driver);
	}

	public static DynamicFormsFixture getInstance() {
		return instance;
	}

	public Form1Fixture form1() {
		return new Form1Fixture(driver);
	}
	
	public Form2Fixture form2() {
		return new Form2Fixture(driver);
	}
	
	public void dispose() {
		driver.getWebDriver().close();
		driver.getWebDriver().quit();
		DynamicFormsFixture.instance = null;
	}

}
