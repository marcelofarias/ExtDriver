package com.sencha.testrunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sencha.extjs.driver.Condition;
import com.sencha.extjs.driver.ExtDriver;
import com.sencha.extjs.driver.factory.DriverFactory;

public class TestRunner {
	
	private static final String jsInjectScript =
			" var head = document.head || document.getElementsByTagName('head')[0]; " +    
			" var script = document.createElement('script'); " +
			" script.src = arguments[0]; " +
			" script.type = 'text/javascript'; " +
			" head.appendChild(script); ";
	
	public Collection<TestResult> run(final ExtDriver driver, String[] unitTestDirectories) {
		Collection<ContextDescriptor> unitTestContexts = new ArrayList<ContextDescriptor>();
		int seed = 10000;
		for (String directory : unitTestDirectories) {
			unitTestContexts.add(new ContextDescriptor(directory, "/" + seed++));
		}
		
		TestRunnerHttpServer server = new TestRunnerHttpServer();
		server.start(unitTestContexts);
		
		driver.get("http://localhost:1903");
		
		new Condition() {
			@Override
			public boolean isSatisfied() {
				return driver.executeScript("return Ext.isReady;").equals(Boolean.TRUE);
			}
			@Override
			public String getErrorMessage() {
				return "Timeout waiting for ExtJS to be ready";
			}
		}.waitUntilSatisfied(30000, 1000);
		
		for (ContextDescriptor unitTestContext : unitTestContexts) {
			Collection<String> specFileNames = new ArrayList<String>();
			FileUtils.listSpecFiles(unitTestContext.getAbsolutePath(), specFileNames);
			
			for (String specFileName : specFileNames) {
				String specRelativeUrl = specFileName.replace(
						unitTestContext.getAbsolutePath(),
						unitTestContext.getRelativeUrl());
				
				driver.executeScript(jsInjectScript, specRelativeUrl);
			}
		}
		
		driver.executeScript("jasmine.getEnv().execute();");
		
		new Condition() {
			@Override
			public boolean isSatisfied() {
				return driver.executeScript("return Sencha.running;").equals(Boolean.FALSE);
			}
			@Override
			public String getErrorMessage() {
				return "Timeout waiting for tests to complete";
			}
		}.waitUntilSatisfied(5 * 60 * 1000, 1000);
		
		Collection<TestResult> results = new ArrayList<TestResult>();
		@SuppressWarnings("unchecked")
		List<Map> rawResults = (List<Map>) driver.executeScript("return Sencha.results;");
		for (Map rawResult : rawResults) {
			results.add(buildTestResult(rawResult));
		}
		
		server.stop();
		return results;
	}
	
	private TestResult buildTestResult(Map rawResult) {
		String description = (String) rawResult.get("description");
		Boolean passed = (Boolean) rawResult.get("passed");
		
		return new TestResult(description, passed);
	}

	public static void main(String[] arguments) {
		ExtDriver driver = DriverFactory.newFirefoxInstance();
		TestRunner runner = new TestRunner();
		Collection<TestResult> results = runner.run(
				driver,
				new String[] {
					"/Users/marcelofarias/git/SDK/extjs/test/unit/spec"
				});
		driver.dispose();
		
		for (TestResult result : results) {
			System.out.println(result);
		}
	}

}
