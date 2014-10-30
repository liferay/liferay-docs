package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class ResetImagesDevSite {

	public static void resetImages(String markdownFilePath) throws IOException {

		File markdownfile = new File(markdownFilePath); // example:
														// "/home/$USER/workspace/01-example.markdown"

		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure " + "[0-9]:";
		System.out.println("find: " + find);
		String replace = "\\!\\[Figure " + "x:";
		System.out.println("replace: " + replace);

		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);

		find = "\\!\\[Figure " + "[0-9][0-9]:";
		System.out.println("find: " + find);
		pattern = Pattern.compile(find);
		matcher = pattern.matcher(output);
		output = matcher.replaceAll(replace);
		System.out.println("replace: " + replace);

		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);
	}

}
