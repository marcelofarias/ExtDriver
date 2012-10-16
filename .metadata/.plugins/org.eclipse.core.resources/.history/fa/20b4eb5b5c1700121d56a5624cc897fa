package com.sencha.extdriver;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class HelloWorld {

	@Test
	public void helloWorld() throws Throwable {

//		ChromeDriverService service = new ChromeDriverService.Builder()
//				.usingDriverExecutable(new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))
//				.usingAnyFreePort().build();
//		service.start();
//
//		WebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
//		JavascriptExecutor executor = (JavascriptExecutor) driver;

	   File file = new File("lib/firebug-1.9.2.xpi");
	   FirefoxProfile firefoxProfile = new FirefoxProfile();
	   firefoxProfile.addExtension(file);
	   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup screen

	   WebDriver driver = new FirefoxDriver(firefoxProfile);
	   JavascriptExecutor executor = (JavascriptExecutor) driver;
		
		// And now use this to visit Google
		// driver.get("http://www.google.com");
		driver.get("http://qa.sencha.com/knightly/qa/ext-4.1-20121014/examples/form/dynamic.html");

		// Find the text input element by its name
		// WebElement element = driver.findElement(By.name("q"));

		// Enter something to search for
		// element.sendKeys("Cheese!");

		// Now submit the form. WebDriver will find the form for us from the
		// element
		// element.submit();

		// Check the title of the page
		System.out.println(executor.executeScript("return Ext.isReady"));
		System.out.println("Page title is: " + driver.getTitle());
		
		driver.quit();
	}

}
