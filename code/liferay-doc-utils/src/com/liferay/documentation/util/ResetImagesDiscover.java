package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;

public class ResetImagesDiscover {

	public static void resetImages(String markdownFilePath) throws IOException {

		// Linux example: "/home/$USER/workspace/01-example-chapter.markdown"
		// Windows example: "E:\liferay-docs\discover\portal\articles\01-what-is-liferay\00-what-is-liferay-intro.markdown"
		File markdownfile = new File(markdownFilePath);

		File parentFolder = markdownfile.getParentFile();
		
		String parentFolderName = "";
		
		if (parentFolder == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("No chapter number could be derived for Markdown file ");
			sb.append(markdownFilePath);
			sb.append(", since it has no parent folder.");

			throw new BuildException(sb.toString());
		}
		else {
			parentFolderName = parentFolder.getName();
		}
		
		String chapternum = parentFolderName.substring(0,2);
		System.out.println("chapternum: " + chapternum);
		int chnum = Integer.parseInt(chapternum);
		System.out.println("chnum: " + chnum);

		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure " + "[0-9][0-9]?" + "\\.[0-9[x]]:";
		System.out.println("find: " + find);
		String replace = "\\!\\[Figure " + chnum + ".x:";
		System.out.println("replace: " + replace);

		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);

		find = "\\!\\[Figure " + "[0-9][0-9]?" + "\\.[0-9][0-9[x]]:";
		System.out.println("find: " + find);
		pattern = Pattern.compile(find);
		matcher = pattern.matcher(output);
		output = matcher.replaceAll(replace);
		System.out.println("replace: " + replace);

		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);

		/*
		 * This code is for saving the output to a new file so the original is
		 * not overwritten; useful for debugging
		 * 
		 * String markdownfileoutputpath = args[1]; File markdownfileoutput =
		 * new File(markdownfileoutputpath);
		 * FileUtils.writeStringToFile(markdownfileoutput, output);
		 */

	}

}
