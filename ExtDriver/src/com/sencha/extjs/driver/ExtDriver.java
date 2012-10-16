package com.sencha.extjs.driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ExtDriver {
	
	private WebDriver webDriver;
	private JavascriptExecutor executor;
	
	public ExtDriver(String url) {
		try {
		   File file = new File("lib/firebug-1.9.2.xpi");
		   FirefoxProfile firefoxProfile = new FirefoxProfile();
		   firefoxProfile.addExtension(file);
		   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup screen
		   
		   webDriver = new FirefoxDriver(firefoxProfile);
		   executor = (JavascriptExecutor) webDriver;
		   
		   webDriver.get(url);
		} catch (IOException e) {
			throw new ExtDriverException("Error creating WebDriver", e);
		}
	}
	
	public Object executeScript(String script, Object... arguments) {
		return executor.executeScript(script, arguments);
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}

}
