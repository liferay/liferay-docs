package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CleanImages {

	public static void main(String[] args) throws Exception {

		System.out.println("Start checking for unused images ...");

		String docDir = args[0];

		String[] dirTypes = {"", "-dxp"};

		for (String dirType : dirTypes) {

			File articleDir = new File("../" + docDir + "/articles" + dirType);

			ArrayList<String> imageNames = new ArrayList<String>();

			File[] articleDirFiles = articleDir.listFiles();
			List<File> articles = new ArrayList<File>();

			Queue<File> q = new LinkedList<File>();
			for (File f : articleDirFiles) {
				q.add(f);
			}

			while (!q.isEmpty()) {
				File f = q.remove(); 

				if (f.isDirectory()) {
					File[] files = f.listFiles();

					for (File file : files) {
						q.add(file);
					}
				}
				else {
					if (f.getName().endsWith(".markdown")) {
						articles.add(f);
					}
				}
			}

			for (File mkdFile : articles) {

				if (!mkdFile.getName().endsWith(".markdown")) {
					continue;
				}

				//Useful for debugging:
				//System.out.println(" " + mkdFile.getName());

				LineNumberReader in = new LineNumberReader(new FileReader(
						mkdFile));
				String line = null;
				String imageString = "../../images";
				int imageClosingChar;

				while ((line = in.readLine()) != null) {
					if (line.contains(imageString)) {
						
						// Check each line for images; if more than one image on
						// line, check each image
						while (line.contains(imageString)) {

							int imageIndex = line.indexOf(imageString);
							
							if (line.contains("<img src=")) {
								imageClosingChar = line.indexOf("\"", imageIndex);
							}
							else {
								imageClosingChar = line.indexOf(")", imageIndex);
							}

							String lineSubstring = line.substring(0, imageClosingChar);

							imageNames.add(extractImageName(lineSubstring, dirType));

							line = line.substring(lineSubstring.length());
						}
					}
				}
				in.close();
			}

			File imagesDir = new File("../" + docDir + "/images" + dirType);
			File[] imageFiles = imagesDir.listFiles();

			for (File imageFile : imageFiles) {
				
				if (imageFile.getName().endsWith(".markdown") || 
						imageFile.getName().endsWith(".txt")) {
					continue;
				}
				
				if (!imageNames.contains(imageFile.getName())) {
					System.out.println("Deleting unused image: "
							+ imageFile.getName());
					imageFile.delete();
				}
			}
		}
	}

	private static String extractImageName(String line, String dirType) {

		int imageBegIndex;

		if (dirType.equals("")) {

			// "../../images/" length
			imageBegIndex = 13;
		}
		else {

			// "../../images-dxp/" length
			imageBegIndex = 17;
		}

		String imageName = line.substring(
				line.lastIndexOf("../../images") + imageBegIndex, line.length());
		
		return imageName;
	}
}

