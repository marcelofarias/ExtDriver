package com.sencha.extjs.driver.locator;

import com.sencha.extjs.driver.ExtComponentFixture;
import com.sencha.extjs.driver.ExtDriver;

public interface ComponentLocator {
	public String getId(ExtComponentFixture scope, ExtDriver driver);
}
