package com.sencha.extjs.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sencha.extjs.driver.exception.ExtDriverException;

public class ExtDriver {
	
	private WebDriver webDriver;
	private JavascriptExecutor executor;
	
	public ExtDriver(String url, WebDriver webDriver) {
	   this.webDriver = webDriver;
	   this.executor = (JavascriptExecutor) webDriver;
		   
	   webDriver.get(url);
	}
	
	public Object executeScript(String script, Object... arguments) {
		return executor.executeScript(script, arguments);
	}
	
	public void mouseOver(WebElement element) {
		Actions builder = new Actions(getWebDriver());
		builder.moveToElement(element, 2, 2).build().perform();
	}
	
	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			throw new ExtDriverException("Unexpected error while sleeping", e);
		}
	}
	
	public WebDriver getWebDriver() {
		return this.webDriver;
	}

}
