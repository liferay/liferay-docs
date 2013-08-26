
package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CleanImagesTask extends Task {
	
	@Override
	public void execute() throws BuildException {
		
		System.out.println("Start checking for unused images ...");
		
		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" could not be found");
		}

		if (!dir.isDirectory()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" is not a directory");
		}

		File[] files = dir.listFiles();

		if (files.length == 0) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" is empty");
		}

		File langDir = null;
		for (File file : files) {
			if (file.isDirectory() && file.getName().equals(_lang)) {
				langDir = file;
				break;
			}
		}

		if (langDir == null) {
			throw new BuildException("Missing language directory (" + _lang +
				") within doc.dir " + _docdir);
		}
		
		ArrayList<String> imageNames = new ArrayList<String>();
		
		try {
			File chDir = new File(langDir.getPath()+ "/chapters");
			if (!chDir.exists() || !chDir.isDirectory()) {
				throw new BuildException("Missing chapters directory " +
						chDir.getAbsolutePath());
			}

			for (File chFile : chDir.listFiles()) {
				if (!chFile.getName().endsWith("markdown")) {
					continue;
				}

				System.out.println("inside " + chFile.getPath());

				LineNumberReader in =
					new LineNumberReader(new FileReader(chFile));
				String line;
				try {
					while ((line = in.readLine()) != null) {
						if (line.contains("(../../images/")) {
							imageNames.add(extractImageName(line));
						}
					}
					in.close();
				} catch (IOException e) {
					throw new BuildException(e.getLocalizedMessage());
				}
			}

		} catch (FileNotFoundException e) {
			throw new BuildException(e.getLocalizedMessage());
		}
		
		File imagesDir = new File(dir.getPath() + "/images");
		
		if (!imagesDir.exists()) {
			throw new BuildException(imagesDir.getAbsolutePath() +
				" could not be found");
		}

		if (!imagesDir.isDirectory()) {
			throw new BuildException("imagesDir " + imagesDir.getAbsolutePath()
					+ " is not a directory");
		}
		
		File[] imageFiles = imagesDir.listFiles();
		
		for (File imageFile : imageFiles) {
			if (!imageNames.contains(imageFile.getName())) {
				System.out.println("Deleting unused image: "
						+ imageFile.getName());
				imageFile.delete();
			}
		}
		
	}
	
	public void setDocdir(String docdir) {
		_docdir = docdir;
	}
	public void setLang(String lang) {
		_lang = lang;
	}
	
	private String extractImageName(String line) {
		
		String imageName = line.substring(
				line.lastIndexOf("(../../images/") + 14, line.lastIndexOf(")"));
		return imageName;
	}

	private String _docdir;
	private String _lang;

}
