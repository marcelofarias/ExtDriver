package com.sencha.plugin.testrunner.controllers;

import java.util.Collection;

import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.factory.DriverFactory;
import com.sencha.testrunner.TestResult;
import com.sencha.testrunner.TestRunner;
import com.sencha.testrunner.TestRunnerHttpServer;

public class TestRunnerController {
	
	public static enum Browser {
		CHROME,
		FIREFOX
	}
	
	private TestRunner runner;
	
	public TestRunnerController() {
		runner = new TestRunner();
	}
	
	public Collection<TestResult> runTests(Browser browser) {
		ExtDriver driver;
		switch (browser) {
		case CHROME:
			driver = DriverFactory.newChromeInstance();
			break;
		case FIREFOX:
			driver = DriverFactory.newFirefoxInstance();
			break;
		default:
			throw new RuntimeException("Unsupported browser");
		}
		
		Collection<TestResult> results = runner.run(
				driver,
				new String[] {
						"/Users/marcelofarias/git/SDK/extjs/test/unit/spec/button"
				},
				new String[] {
						"/Users/marcelofarias/Documents/workspace_extdriver/ExamplesTest/js-test/"
				});
		driver.dispose();
		return results;
	}

	public String[] startServer() {
		
		TestRunnerHttpServer server = new TestRunnerHttpServer();
//		server.start();
		return new String[] { "Server started" };
	}

}
