package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class AddMarkdownMetadata {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void addMarkdownMetadata(String markdownFilePath, String title, String author, String date) throws IOException {
		
		//
		// Adds metadata to beginning of markdown file
		//
		File markdownFile = new File(markdownFilePath);
		String markdownFileString = FileUtils.readFileToString(markdownFile);
		markdownFileString = "% " + title + "\n% " + author + "\n% " + date + "\n\n" + markdownFileString;
		System.out.println("Inserting metadata...");
		
		//
		// Saves the updated file
		//
		FileUtils.writeStringToFile(markdownFile, markdownFileString);
		System.out.println("Saving file...");
	}

}