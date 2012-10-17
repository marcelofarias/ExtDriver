package com.sencha.extjs.driver.locator;

import com.sencha.extjs.driver.ExtComponentFixture;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.util.StringUtils;

public class FieldSetTitleLocator implements ComponentLocator {
	
	private String title;
	
	public FieldSetTitleLocator(String title) {
		this.title = title;
	}
	
	public String getId(ExtComponentFixture scope, ExtDriver driver) {
		if (scope != null) {
			return (String) driver.executeScript(
					"return Ext.get(arguments[0]).select('fieldset:has(div.x-fieldset-header-text:contains(' +  arguments[1] + '))').elements[0].id",
					scope.getId(),
					StringUtils.escapeWhitespaces(title));
		} else {
			return (String) driver.executeScript(
					"return Ext.dom.Query.select('tbody.x-field:has(label:contains(' + arguments[0] + '))')[0].id",
					StringUtils.escapeWhitespaces(title));
		}
	}

}
