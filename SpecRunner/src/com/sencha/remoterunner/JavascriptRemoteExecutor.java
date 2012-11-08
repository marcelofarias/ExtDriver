package com.sencha.remoterunner;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JavascriptRemoteExecutor {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void main (String[] arguments) throws Exception {
		 ScriptEngineManager factory = new ScriptEngineManager();
		 ScriptEngine engine = factory.getEngineByName("JavaScript");
		 
		 engine.eval("var foo = 1;");
		 engine.eval("foo++;");
		 engine.eval("println(foo)");
		 
		 
	}
	
	private String readTextFile(String fileName) {
		Path path = Paths.get(fileName);
		StringBuilder sb = new StringBuilder("var ___LINE_NUMBER = 0;\n");
		int lineNumber = 0;
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				sb.append(String.format("___LINE_NUMBER = %d;", ++lineNumber)).append("\n");
				sb.append(scanner.nextLine()).append("\n");
			}
		} catch (Exception e) {
			throw new RuntimeException("Damn!", e);
		}
		return sb.toString();
	}

}
