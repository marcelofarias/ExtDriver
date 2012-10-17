package com.sencha.extjs.driver.locator;

import com.sencha.extjs.driver.ExtComponentFixture;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.util.StringUtils;

public  class FieldLabelLocator implements ComponentLocator {
	private String label;
	
	public FieldLabelLocator(String label) {
		this.label = label;
	}
	
	public String getId(ExtComponentFixture scope, ExtDriver driver) {
		if (scope != null) {
			return (String) driver.executeScript(
					"return Ext.get(arguments[0]).select('tbody.x-field:has(label:contains(' + arguments[1] + '))').elements[0].id",
					scope.getId(),
					StringUtils.escapeWhitespaces(label));
		} else {
			return (String) driver.executeScript(
					"return Ext.dom.Query.select('tbody.x-field:has(label:contains(' + arguments[0] + '))')[0].id",
					StringUtils.escapeWhitespaces(label));
		}
	}
}
	
