package com.sencha.specrunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sencha.extjs.driver.ExtDriver;

public class SpecRunner {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public void run() {
		WebDriver webDriver;
		
		try {
		   File firebug = new File("lib/firebug-1.9.2.xpi");
		   File errorCollector = new File("lib/JSErrorCollector.xpi");
		   FirefoxProfile firefoxProfile = new FirefoxProfile();
		   firefoxProfile.addExtension(firebug);
		   firefoxProfile.addExtension(errorCollector);
		   
		   JavaScriptError.addExtension(firefoxProfile);
		   firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.9.2"); // Avoid startup screen
			   
		   webDriver = new FirefoxDriver(firefoxProfile);
		} catch (IOException e) {
			throw new RuntimeException("Die, die my darling", e);
		}
		
//		ExtDriver driver = new ExtDriver("http://localhost/~marcelofarias/jasmine/runner.html", webDriver);
		ExtDriver driver = new ExtDriver("http://localhost/~marcelofarias/SDK/extjs/test/unit/index.html", webDriver);
		
//		driver.executeScript("Ext.require('*');");
//		boolean extIsReady = false;
//		while (!extIsReady) {
//			extIsReady = (Boolean) driver.executeScript("return Ext.isReady;");
//			driver.sleep(500);
//		}
		driver.getWebDriver().switchTo().frame("sandbox");
		loadSpecs(driver);
		System.out.println("Executing specs...");
		driver.executeScript(
				" var env = jasmine.getEnv(); " +
				" env.addReporter(parent.Test.SandBox.reporter); " +
				" parent.Test.SandBox.phantomReporter = new jasmine.PhantomJsReporter(); " +
				" env.addReporter(parent.Test.SandBox.phantomReporter); " +
				" env.execute(); ");
//		driver.executeScript("execJasmine()");
//		driver.executeScript("jasmine.getEnv().execute()");
		
//		webDriver.close();
//		webDriver.quit();
	}
	
	private void loadSpecs(ExtDriver driver) {
//		String spec = readTextFile("/Users/marcelofarias/git/SDK/extjs/test/unit/spec/AlternateClassNames.js");
//		driver.executeScript(spec);
		
//		driver.executeScript(
//				" window.jsErrors = []; " +
//				" window.onerror = function(errorMessage) { " +
//				"     window.jsErrors[window.jsErrors.length] = errorMessage; " +
//				" } ");

		Collection<String> fileList = new ArrayList<String>();
		ListFilesUtil listFilesUtil = new ListFilesUtil();
		listFilesUtil.listJSFiles("/Users/marcelofarias/git/SDK/platform/core/test/unit/spec/", fileList);
		listFilesUtil.listJSFiles("/Users/marcelofarias/git/SDK/platform/test/unit/spec/", fileList);
		listFilesUtil.listJSFiles("/Users/marcelofarias/git/SDK/extjs/test/unit/spec/", fileList);
		
		loadSpecsFromFileList(driver, fileList);
		
	}

	private void loadSpecsFromFileList(ExtDriver driver, Collection<String> fileList) {
		for (String file : fileList) {
			String spec = readTextFile(file).trim();
			if (spec.startsWith("describe(") && spec.endsWith("});")) {
				try {
					driver.executeScript(spec);
					System.out.println("Spec " + file + " loaded");
				} catch (Exception e) {
					System.out.println("Error loading spec " + file);
//					List<JavaScriptError> jsErrors = JavaScriptError.readErrors(driver.getWebDriver());
//					for (JavaScriptError error : jsErrors) {
//						System.out.println(error.getErrorMessage());
//						System.out.println(error.getLineNumber());
//						System.out.println(error.getSourceName());
//					}
//					System.out.println("=====================");
//					System.out.println(spec);
//					System.out.println("=====================");
				}
			}
		}
	}
	
	private String readTextFile(String fileName) {
		Path path = Paths.get(fileName);
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
				sb.append("\n");
			}
		} catch (Exception e) {
			throw new RuntimeException("Damn!", e);
		}
		return sb.toString();
	}
	
	public static void main(String[] arguments) {
		SpecRunner runner = new SpecRunner();
		runner.run();
	}

}
