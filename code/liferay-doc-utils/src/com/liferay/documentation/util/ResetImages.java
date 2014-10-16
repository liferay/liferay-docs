package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ResetImages {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void resetImages(String markdownFilePath) throws IOException {
		
		File markdownfile = new File(markdownFilePath); // example: "/home/$USER/workspace/01-example-chapter.markdown"
		String markdownfilename = FilenameUtils.getName(markdownfile.getPath());
		String chapternum = markdownfilename.substring(0,2);
		System.out.println("chapternum: " + chapternum);
		int chnum = Integer.parseInt(chapternum);
		System.out.println("chnum: " + chnum);
		
		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure " + chnum + "\\.[0-9]:";
		System.out.println("find: " + find);
		String replace = "\\!\\[Figure " + chnum + ".x:";
		System.out.println("replace: " + replace);
		
		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);
		
		find = "\\!\\[Figure " + chnum + "\\.[0-9][0-9]:";
		System.out.println("find: " + find);
		pattern = Pattern.compile(find);
		matcher = pattern.matcher(output);
		output = matcher.replaceAll(replace);
		System.out.println("replace: " + replace);

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