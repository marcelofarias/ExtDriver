package com.sencha.extjs.examples.form.dynamic;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.exception.ExtDriverException;

@RunWith(Suite.class)
@SuiteClasses({
		Form1Test.class,
		Form2Test.class
})
public class DynamicTestSuite {
	
	@BeforeClass
	public static void initializeMainFixture() {
		WebDriver webDriver;
		try {
		   File file = new File("lib/firebug-1.9.2.xpi");
		   FirefoxProfile firefoxProfile = new FirefoxProfile();
		   firefoxProfile.addExtension(file);
		   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup screen
			   
		   webDriver = new FirefoxDriver(firefoxProfile);
		} catch (IOException e) {
			throw new ExtDriverException("Error creating WebDriver", e);
		}
		
		ExtDriver driver = new ExtDriver("http://qa.sencha.com/knightly/qa/ext-4.1-20121014/examples/form/dynamic.html", webDriver);
		DynamicFormsFixture.createInstance(driver);
	}
	
	@AfterClass
	public static void disposeMainFixture() {
		DynamicFormsFixture.getInstance().dispose();
	}

}
