package com.liferay.documentation.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberImagesTask extends Task {

	@Override
	public void execute() throws BuildException {
		boolean foundDuplicateIds = false;

		File chDir = new File("../" + docDir + "/" + lang + "/chapters");
		System.out.println("Numbering images for files in " + chDir.getPath() +
			" ...");

		if (!chDir.exists() || !chDir.isDirectory()) {
			throw new BuildException("FAILURE - bad chapters directory " + chDir);
		}

		// Get listing of markdown files
		String[] files = chDir.list(new FilenameFilter() {
				String filePatternArg = "([0-9]+)([^\\\\\\[\\]\\|:;%<>]+).markdown";
				Pattern fileNamePattern = Pattern.compile(filePatternArg);

				public boolean accept(File file, String name) {
					return (fileNamePattern.matcher(name).matches());
				}
			});

		if (files == null || files.length == 0) {
			throw new BuildException("FAILURE - no markdown files found in " + chDir);
		}

		// Process each file

		for (int i = 0; i < files.length; i++) {
			String filename = files[i];
			String filepath = "../" + docDir + "/" + lang + "/chapters/" +
				filename;
			try {
				ResetImages.resetImages(filepath);
				NumberImages.numberImages(filepath);
			} catch (IOException e) {
				throw new BuildException(e.getLocalizedMessage());
			}
		}
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	private String lang;
	private String docDir;
}
