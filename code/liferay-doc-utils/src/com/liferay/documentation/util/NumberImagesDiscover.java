package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;

public class NumberImagesDiscover {

	public static int numberImages(String markdownFilePath, int figNum)
			throws IOException {
		File markdownfile = new File(markdownFilePath); // example: "/home/$USER/workspace/01-example-chapter.markdown"
		File parentFolder = markdownfile.getParentFile();

		String parentFolderName = "";

		if (parentFolder == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(
				"No chapter number could be derived for Markdown file ");
			sb.append(markdownFilePath);
			sb.append(", since it has no parent folder.");

			throw new BuildException(sb.toString());
		} else {
			parentFolderName = parentFolder.getName();
		}

		String chapternum = parentFolderName.substring(0, 2);

		figNum = numberImages(markdownFilePath,
				Integer.parseInt(chapternum), figNum);

		return figNum;
	}

	public static int numberImages(String markdownFilePath, int chnum,
			int figNum) throws IOException {
		File markdownfile = new File(markdownFilePath);

		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure " + chnum + ".x:";
		System.out.println("find: " + find);

		String replace = "\\!\\[Figure " + chnum + "." + figNum + ":";
		System.out.println("replace: " + replace);

		Pattern pattern = Pattern.compile(find);

		Matcher matcher = pattern.matcher(source);
		
		if (matcher.find()) {
			String output = matcher.replaceFirst(replace);

			while (matcher.find()) {
				matcher = pattern.matcher(output);
				figNum++;
				replace = "\\!\\[Figure " + chnum + "." + figNum + ":";
				System.out.println("replace: " + replace);
				output = matcher.replaceFirst(replace);
			}

			FileUtils.writeStringToFile(markdownfile, output);

			return figNum + 1;
		}
		else {
			return figNum;
		}
	}

}