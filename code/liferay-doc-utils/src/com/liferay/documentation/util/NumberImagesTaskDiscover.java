package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberImagesTaskDiscover extends Task {
	
	@Override
	public void execute() throws BuildException {

		File articlesDir = new File("articles");
		System.out.println("Numbering images for files in "
				+ articlesDir.getPath() + " ...");

		if (!articlesDir.exists() || !articlesDir.isDirectory()) {
			throw new BuildException("FAILURE - bad chapters directory " + articlesDir);
		}
		
		File[] articlesDirContents = articlesDir.listFiles();
		
		for (File subDir : articlesDirContents) {
			// Get listing of markdown files
			if (subDir.isDirectory()) {
				String[] files = subDir.list();
				
				Arrays.sort(files);
		
				if (files == null || files.length == 0) {
					throw new BuildException("FAILURE - no markdown files found in " + subDir.getAbsolutePath());
				}
				
				int figNum = 1;
				
				// Process each file
				for (int i = 0; i < files.length; i++) {
					String filename = files[i];
					
					if (!filename.endsWith(".markdown")) {
						continue;
					}
					
					String filepath = subDir.getAbsolutePath() + "/" + filename;
					
					try {
						ResetImagesDiscover.resetImages(filepath);
						figNum = NumberImagesDiscover.numberImages(filepath, figNum);
					} catch (IOException e) {
						throw new BuildException(e.getLocalizedMessage());
					}
				}
			}
		
		}
	}

	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	private String docDir;

}