package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class CleanImagesSiteMain {

	public static void main(String[] args) throws Exception {

		System.out.println("Start checking for unused images ...");

		// 1. Store the list of the target directory's files (really
		// directories) in an array.

		String docDir = args[0];

		File articleDir = new File("../" + docDir + "/articles");

		File[] articleDirs = articleDir.listFiles();

		// 2. Loop through the array, and store the name of each directory in a
		// File Array.

		ArrayList<String> imageNames = new ArrayList<String>();

		for (File topicDir : articleDirs) {
			if (topicDir.isDirectory()) {
				
				File[] topicDirs = topicDir.listFiles();
				
				System.out.println("checking " + topicDir.getPath());

				// 3. This gets me the image names into an
				// array list (not surprisingly, named imageNames, instantiated
				// above). It extracts the imageNames from line using a private static
				// method, defined below the Main method.

				for (File mkdFile : topicDirs) {
					
					System.out.println(" " + mkdFile.getName());
					if (!mkdFile.getName().endsWith("markdown")) {
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
			}
		}
		// 4. Save the image files (in the images directory of the docDir) in an
		// array so we can check the imageNames array against them. Delete
		// any imageFile that is not found in the imageNames array. 
		
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

	// 5. Below is the method we're using to extract the image Name from the line
	// containing image references in the markdown file.
	
	private static String extractImageName(String line) {

		String imageName = line.substring(
				line.lastIndexOf("(../../images/") + 14, line.lastIndexOf(")"));
		return imageName;
	}
}

