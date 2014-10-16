package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class AddMarkdownChTemplate {

public static void addMarkdownChTemplate(String markdownFilePath) throws IOException {
		
		//
		// Adds metadata template to beginning of markdown file
		//
		File markdownFile = new File(markdownFilePath);
		String markdownFileString = FileUtils.readFileToString(markdownFile);
		markdownFileString = "% Title" + "\n% Author" + "\n% Date" + "\n\n" + markdownFileString;
		System.out.println("Inserting metadata template...");
		
		//
		// Saves the updated file
		//
		FileUtils.writeStringToFile(markdownFile, markdownFileString);
		System.out.println("Saving file...");
	}
	
}