package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FindParentIntros {

	public static void main(String[] args) throws IOException {

		// E.g. articles\100-tooling\05-maven\01-installing-liferay-maven-artifacts.markdown
		String markdownFilePath = args[0];

		// E.g. tutorials
		String docDir = args[1];

		String tempDir = args[2];

		List<File> fileList = new ArrayList<File>();
		File article = new File(markdownFilePath);

		fileList.add(article);

		File parentDir = article.getParentFile();

		boolean containsIntro = true;

		while (containsIntro) {

			File[] parentFiles = parentDir.listFiles();

			containsIntro = false;

			for (File file : parentFiles) {
				if (file.getName().endsWith("introduction.markdown") ||
						file.getName().endsWith("intro.markdown")) {

					fileList.add(file);

					containsIntro = true;
				}
			}

			parentDir = parentDir.getParentFile();
		}

		for (File introFile : fileList) {

			String introFileString = introFile.toString();
			int fileStart = introFileString.lastIndexOf(File.separator);
			String filePathExcludingFileName = introFileString.substring(0, fileStart);
			String path = "../" + docDir + "/" + tempDir + "/" + filePathExcludingFileName;

			File file = new File(path);
			file.mkdirs();

			File dest = new File(path);

			try {
			    FileUtils.copyFileToDirectory(introFile, dest);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
	}
}