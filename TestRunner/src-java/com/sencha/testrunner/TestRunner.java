package com.sencha.testrunner;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

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
	
	public Collection<TestResult> run(final ExtDriver driver, String[] unitTestDirectories, String[] extDriverTestDirectories) {
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
		
        final Context context = Context.enter();
        try {
            final Scriptable scope = context.initStandardObjects();
            
            rhinoEvalResource(context, scope, "/resources/js/sencha-environment.js");
            rhinoEvalResource(context, scope, "/resources/js/rhino-jasmine.js");
            rhinoEvalResource(context, scope, "/resources/js/sencha-rhino-reporter.js");
            rhinoEval(context, scope, "jasmine.getEnv().addReporter(new Sencha.Reporter());");
            
            Object jsDriver = Context.javaToJS(driver, scope);
            ScriptableObject.putProperty(scope, "driver", jsDriver);
            
            Object jsOut = Context.javaToJS(System.out, scope);
            ScriptableObject.putProperty(scope, "out", jsOut);
            
            Object jsResults = Context.javaToJS(results, scope);
            ScriptableObject.putProperty(scope, "results", jsResults);

            Collection<String> extDriverSpecFileNames = new ArrayList<String>();
            for (String directory : extDriverTestDirectories) {
            	FileUtils.listSpecFiles(directory, extDriverSpecFileNames);
            }
            for (String fileName : extDriverSpecFileNames) {
            	rhinoEvalFile(context, scope, fileName);
            }
            
            rhinoEval(context, scope, "jasmine.getEnv().execute()");
            
            new Condition() {
				@Override
				public boolean isSatisfied() {
					return !Context.toBoolean(rhinoEval(context, scope, "Sencha.running"));
				}
				@Override
				public String getErrorMessage() {
					return "Timeout waiting for ExtDriver tests to complete";
				}
			}.waitUntilSatisfied(60000, 1000);
            
            
//            rhinoEval(context, scope, "driver.get('http://www.google.com')");
            
//            try { Thread.sleep(5000); } catch (Exception e) { };

        } finally {
            Context.exit();
        }
		
		server.stop();
		return results;
	}
	
	private Object rhinoEval(Context context, Scriptable scope, String script) {
		try {
			return context.evaluateString(scope, script, "anonymous", 1, null);
		} catch (RhinoException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getScriptStackTrace());
			return null;
		}
	}
	
	private void rhinoEvalResource(Context context, Scriptable scope, String resourceName) {
		InputStream inputStream = null;
		InputStreamReader reader = null;
		try {
			inputStream = this.getClass().getResourceAsStream(resourceName);
			reader = new InputStreamReader(inputStream);
			context.evaluateReader(scope, reader, resourceName, 1, null);
		} catch (RhinoException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getScriptStackTrace());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { reader.close(); } catch (Exception e) { e.printStackTrace(); }
			try { inputStream.close(); } catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	private void rhinoEvalFile(Context context, Scriptable scope, String fileName) {
		try (FileReader fileReader = new FileReader(fileName)) {
			context.evaluateReader(scope, fileReader, fileName, 1, null);
		} catch (RhinoException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getScriptStackTrace());
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while evaluating JS file", e);
		}
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
					"/Users/marcelofarias/git/SDK/extjs/test/unit/spec/button",
					"/Users/marcelofarias/git/SDK/extjs/test/unit/spec/container",
					"/Users/marcelofarias/git/SDK/extjs/test/unit/spec/dom",
					"/Users/marcelofarias/git/SDK/extjs/test/unit/spec/form",
				},
				new String[] {
//					"/Users/marcelofarias/Documents/workspace_extdriver/ExamplesTest/js-test/"
				});
		driver.dispose();
		
		for (TestResult result : results) {
			System.out.println(result);
		}
		
		System.exit(0);
	}

}
