package com.sencha.extjs.driver;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractDateFieldFixture<T extends AbstractTextFieldFixture<T>> extends AbstractTextFieldFixture<T> {

	public AbstractDateFieldFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}

	public AbstractDateFieldFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}

}
