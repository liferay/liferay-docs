package com.liferay.documentation.util;

import java.io.IOException;

public class DocUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String command = args[0];
		System.out.println(command);
		if (args[0] == null) {
			System.out.println("Please specify an argument: NumberImages, ResetImages, StripHeaderIds, AddMarkdownMetadata (deprecated), or AddMarkdownChTemplate");
		} 
		else {
			if (command.equalsIgnoreCase("NumberImages")) {
				String markdownFilePath = args[1];
				if (markdownFilePath != null) {
					try {
						NumberImages.numberImages(markdownFilePath);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
					}
				}
			}
			else if (command.equalsIgnoreCase("ResetImages")) {
				String markdownFilePath = args[1];
				if (markdownFilePath != null) {
					try {
						ResetImages.resetImages(markdownFilePath);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
					}
				}
			}
			else if (command.equalsIgnoreCase("StripHeaderIds")) {
				String markdownFilePath = args[1];
				if (markdownFilePath != null) {
					try {
						StripHeaderIds.stripHeaderIds(markdownFilePath);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
					}
				}
			}
			else if (command.equalsIgnoreCase("AddMarkdownMetadata")) {
				String markdownFilePath = args[1];
				String title = args[2];
				String author = args[3];
				String date = args[4];
				if (markdownFilePath != null) {
					try {
						AddMarkdownMetadata.addMarkdownMetadata(markdownFilePath, title, author, date);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
					}
				}
			}
			else if (command.equalsIgnoreCase("AddMarkdownChTemplate")) {
				String markdownFilePath = args[1];
				if (markdownFilePath != null) {
					try {
						AddMarkdownChTemplate.addMarkdownChTemplate(markdownFilePath);
					} catch (IOException e) {
						System.out.println("IOException: " + e.getMessage());
					}
				}
			}
			else {
				System.out.println("You entered an invalid argument. Please try again. You can use NumberImages, ResetImages, AddMarkdownMetadata(deprecated), or AddMarkdownChTemplate");
			}
			
		}

	}

}
