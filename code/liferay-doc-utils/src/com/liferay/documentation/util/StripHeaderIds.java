package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class StripHeaderIds {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void stripHeaderIds(String markdownFilePath) throws IOException {

		File markdownFile = new File(markdownFilePath);
		String source = FileUtils.readFileToString(markdownFile);

		String find = "\\[\\]\\(id=[^\\s]+?\\)";
		System.out.println("find: " + find);
		String replace = "";

		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);
		
		FileUtils.writeStringToFile(markdownFile, output);

		/*
		 * This code is for saving the output to a new file so the original is
		 * not overwritten; useful for debugging
		 * 
		 * String markdownFileOutputPath = args[1]; File markdownFileOutput =
		 * new File(markdownFileOutputPath);
		 * FileUtils.writeStringToFile(markdownFileOutput, output);
		 */

	}

}
