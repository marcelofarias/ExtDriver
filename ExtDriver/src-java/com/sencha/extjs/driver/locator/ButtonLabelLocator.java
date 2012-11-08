package com.sencha.extjs.driver.locator;

import com.sencha.extjs.driver.ExtComponentFixture;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.util.StringUtils;

public class ButtonLabelLocator implements ComponentLocator {
	
	private String label;
	
	public ButtonLabelLocator(String label) {
		this.label = label;
	}

	@Override
	public String getId(ExtComponentFixture scope, ExtDriver driver) {
		if (scope != null) {
			return (String) driver.executeScript(
					"return Ext.get(arguments[0]).select('div.x-btn:has(span.x-btn-inner:contains(' + arguments[1] + '))').elements[0].id",
					scope.getId(),
					StringUtils.escapeWhitespaces(label));
		} else {
			return (String) driver.executeScript(
					"return Ext.dom.Query.select('div.x-btn:has(span.x-btn-inner:contains(' + arguments[0] + '))')[0].id",
					StringUtils.escapeWhitespaces(label));
		}
	}

}
