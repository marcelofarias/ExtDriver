package com.sencha.extdriver;

import org.junit.Test;

import com.sencha.extjs.driver.ExtDriver;

public class ExtDriverTest {
	
	@Test
	public void foo() {
		new ExtDriver("http://qa.sencha.com/knightly/qa/ext-4.1-20121014/examples/form/dynamic.html");
	}

}
