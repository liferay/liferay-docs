package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class CleanImagesSiteMain {

	public static void main(String[] args) throws Exception {

		System.out.println("Start checking for unused images ...");

		String docDir = args[0];

		File articleDir = new File("../" + docDir + "/articles");

		File[] articleDirs = articleDir.listFiles();

		ArrayList<String> imageNames = new ArrayList<String>();

		for (File topicDir : articleDirs) {
			if (topicDir.isDirectory()) {

				File[] topicDirs = topicDir.listFiles();

				System.out.println("checking " + topicDir.getPath());

				for (File mkdFile : topicDirs) {

					if (!mkdFile.getName().endsWith(".markdown")) {
						continue;
					}

					System.out.println(" " + mkdFile.getName());

					LineNumberReader in = new LineNumberReader(new FileReader(
							mkdFile));
					String line = null;

					while ((line = in.readLine()) != null) {
						if (line.contains("(../../images/")) {
							imageNames.add(extractImageName(line));
						}
					}
					in.close();
				}
			}
		}

		File imagesDir = new File("../" + docDir + "/images");
		File[] imageFiles = imagesDir.listFiles();

		for (File imageFile : imageFiles) {
			if (!imageNames.contains(imageFile.getName())) {
				System.out.println("Deleting unused image: "
						+ imageFile.getName());
				imageFile.delete();
			}
		}
	}

	private static String extractImageName(String line) {

		String imageName = line.substring(
				line.lastIndexOf("(../../images/") + 14, line.lastIndexOf(")"));
		return imageName;
	}
}

