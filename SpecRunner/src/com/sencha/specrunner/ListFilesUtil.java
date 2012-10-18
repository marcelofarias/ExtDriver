package com.sencha.specrunner;

import java.io.File;
import java.util.Collection;

public class ListFilesUtil {
	 
    /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
    public void listJSFiles(String directoryName, Collection<String> fileList) {
 
        File directory = new File(directoryName);
 
        //get all the files from a directory
        File[] fList = directory.listFiles();
 
        for (File file : fList) {
        	if (file.getAbsolutePath().startsWith(".")) {
        		continue;
        	}
            if (file.isFile()) {
            	if (file.getAbsolutePath().endsWith(".js")) {
            		fileList.add(file.getAbsolutePath());
//            		System.out.println(file.getAbsolutePath());
            	}
            } else if (file.isDirectory()) {
                listJSFiles(file.getAbsolutePath(), fileList);
            }
        }
    }

}
