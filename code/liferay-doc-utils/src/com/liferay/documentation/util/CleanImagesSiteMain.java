package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class CleanImagesSiteMain {

	public static void main(String[] args) throws Exception {

		System.out.println("Start checking for unused images ...");

		// 1. Store the list of the target directory's files (really
		// directories) in an array (named files in Jesse's code).

		String docDir = args[0];

		File artDir = new File("../" + docDir + "/articles");

		File[] artDirs = artDir.listFiles();

		// 2. Loop through the array, and store the name of each directory in a
		// File Array.

		ArrayList<String> imageNames = new ArrayList<String>();

		for (File file : artDirs) {
			if (file.isDirectory()) {

				File[] files = file.listFiles();

				// 3. This abominable code should get me the image names into an
				// array (not surprisingly, named imageNames, instantiated
				// above). It extracts the imageNames using a private static
				// method, defined below the Main method.

				for (File mkdFile : files) {
					mkdFile.getPath();
					System.out.println(mkdFile.getPath());
					if (mkdFile.getName().endsWith("markdown")) {
						continue;
					}
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

				System.out.println("checking" + file.getPath());
			}
			// 4. For each directory, loop through all the files
			// (printing out which files are being checked), and store the
			// imageNames.

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
