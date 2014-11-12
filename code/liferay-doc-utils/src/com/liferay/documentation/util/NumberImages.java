package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class NumberImages {

	public static void numberImages (String markdownFilePath) throws IOException {
		File markdownfile = new File(markdownFilePath); // example: "/home/$USER/workspace/01-example-chapter.markdown"
		String markdownfilename = FilenameUtils.getName(markdownfile.getPath());
		String chapternum = markdownfilename.substring(0,2);
		numberImages (markdownFilePath, Integer.parseInt(chapternum));
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void numberImages(String markdownFilePath, int chnum) throws IOException {
		
		File markdownfile = new File(markdownFilePath); // example: "/home/$USER/workspace/01-example-chapter.markdown"
		String markdownfilename = FilenameUtils.getName(markdownfile.getPath());
		
		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure " + chnum + ".x:";
		System.out.println("find: " + find);
		int fignum = 1;
		String replace = "\\!\\[Figure " + chnum + "." + fignum + ":";
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
			replace = "\\!\\[Figure " + chnum + "." + fignum + ":";
			System.out.println("replace: " + replace);
			output = matcher.replaceFirst(replace);
		}
		
		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);
		
		/* This code is for saving the output to a new file so the original is not overwritten; useful for debugging
		
		String markdownfileoutputpath = args[1];
		File markdownfileoutput = new File(markdownfileoutputpath);
		FileUtils.writeStringToFile(markdownfileoutput, output);
		
		*/
	}
}
