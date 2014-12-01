package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class NumberImagesDiscover {

	public static int numberImages(String markdownFilePath, int figNum)
			throws IOException {

		String[] markdownFilePathArray = markdownFilePath.split("/");

		int markdownFilePathArrayLength = markdownFilePathArray.length;

		String parentFolderName = "";

		if (markdownFilePathArrayLength < 2) {
			System.out
					.println("No chapter number could be extracted from the"
							+ "Markdown file path since the parent folder of the Markdown file"
							+ "could not be found.");
		} else {
			parentFolderName = markdownFilePathArray[markdownFilePathArrayLength - 2];
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