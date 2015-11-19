package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class NumberImagesDiscover {

	public static void numberImages(String markdownFilePath) throws IOException {

		File markdownfile = new File(markdownFilePath);

		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure x:";
		System.out.println("find: " + find);
		int fignum = 1;
		String replace = "\\!\\[Figure " + fignum + ":";
		System.out.println("replace: " + replace);

		//
		// Compiles the given regular expression into a pattern
		//
		Pattern pattern = Pattern.compile(find);

		//
		// Creates a matcher that will match the given input against the pattern
		//
		Matcher matcher = pattern.matcher(source);

		//
		// Replaces the first subsequence of the input sequence that matches the
		// pattern with the given replacement string
		//
		String output = matcher.replaceFirst(replace);

		//
		// Loops through file replacing all remaining matching subsequences of
		// the input sequence with the appropriate figure numbers
		//
		while (matcher.find()) {
			matcher = pattern.matcher(output);
			fignum++;
			replace = "\\!\\[Figure " + fignum + ":";
			System.out.println("replace: " + replace);
			output = matcher.replaceFirst(replace);
		}

		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);
	}

}