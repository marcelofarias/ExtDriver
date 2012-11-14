package com.sencha.rhino;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sun.org.mozilla.javascript.internal.RhinoException;

import com.sun.script.javascript.RhinoScriptEngine;

public class RhinoTest {
	
    private ScriptEngineManager factory;
    private RhinoScriptEngine engine;
	
	public RhinoTest() {
        factory = new ScriptEngineManager();
        engine = (RhinoScriptEngine) factory.getEngineByName("JavaScript");
	}
	
	public void run(String fileName) throws Exception {
        engine.put(ScriptEngine.FILENAME, fileName);
        engine.eval(new java.io.FileReader(fileName));
	}
	
	public static void main(String[] arguments) throws Exception {
		RhinoTest rhinoTest = new RhinoTest();
//		rhinoTest.run("/Users/marcelofarias/Documents/workspace_extdriver/RhinoTest/script.js");
//		rhinoTest.run("/Users/marcelofarias/Documents/workspace_extdriver/RhinoTest/prototype.js");
//		rhinoTest.run("/Users/marcelofarias/Documents/workspace_extdriver/RhinoTest/extdriver.js");
		try {
			rhinoTest.run("/Users/marcelofarias/Documents/workspace_extdriver/RhinoTest/settimeout.js");
			rhinoTest.run("/Users/marcelofarias/Documents/workspace_extdriver/RhinoTest/jasmine.js");
		} catch (ScriptException e) {
			RhinoException cause = (RhinoException) e.getCause();
//			e.printStackTrace();
//			cause.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(cause.getScriptStackTrace());
		} catch (Exception e) {
			System.out.println(e.getClass());
			e.printStackTrace();
		}
	}

}
