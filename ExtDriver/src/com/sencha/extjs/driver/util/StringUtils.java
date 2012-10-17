package com.sencha.extjs.driver.util;

public class StringUtils {
	
	private StringUtils() {
		
	}
	
	public static String escapeWhitespaces(String string) {
		return string.replaceAll("\\s", "\\\\ ");
	}
 
}
