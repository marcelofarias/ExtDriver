package com.sencha.extjs.driver.factory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.exception.ExtDriverException;

public class DriverFactory {
	
	public static ExtDriver newFirefoxInstance() {
		WebDriver webDriver;
		try {
		   FirefoxProfile firefoxProfile = new FirefoxProfile();
		   firefoxProfile.addExtension(DriverFactory.class, "/lib/firebug-1.9.2.xpi");
		   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup screen
			   
		   webDriver = new FirefoxDriver(firefoxProfile);
		} catch (IOException e) {
			throw new ExtDriverException("Error creating FirefoxDriver", e);
		}
		
		return new ExtDriver(webDriver);
	}
	
	public static ExtDriver newChromeInstance() {
		System.setProperty("webdriver.chrome.driver", "/Users/marcelofarias/Downloads/chromedriver");
		WebDriver webDriver = new ChromeDriver();
		return new ExtDriver(webDriver);
	}

}
