package com.sencha.testrunner;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Scanner;

public class FileUtils {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;
	 
    public static void listSpecFiles(String directoryName, Collection<String> fileList) {
 
        File directory = new File(directoryName);
 
        File[] fList = directory.listFiles();
 
        for (File file : fList) {
        	String absolutePath = file.getAbsolutePath();
			if (absolutePath.startsWith(".")) {
        		continue;
        	}
            if (file.isFile()) {
            	if (absolutePath.endsWith(".js") && seemsToBeASpec(absolutePath)) {
            		fileList.add(absolutePath);
            	}
            } else if (file.isDirectory()) {
                listSpecFiles(absolutePath, fileList);
            }
        }
    }

	private static boolean seemsToBeASpec(String absolutePath) {
		String specCandidate = readTextFile(absolutePath).trim();
		return specCandidate.startsWith("describe(") && specCandidate.endsWith("});");
	}
	
	private static String readTextFile(String fileName) {
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

}
