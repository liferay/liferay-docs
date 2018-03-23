package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckArticleImagesTask extends Task {

	@Override
	public void execute() throws BuildException {
		List<String> errors = new ArrayList<String>();

		System.out.println("Start checking image sources ...");

		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" could not be found");
		}

		if (!dir.isDirectory()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" is not a directory");
		}

		if (!_article.endsWith(".markdown") && !_article.endsWith(".md")) {
			throw new BuildException("Article isn't a Markdown file");
		}

		File article = new File("../" + _docdir + "/" + _article);

		if (!article.exists()) {
			throw new BuildException("Article doesn't exist: " + article.getPath());
		}

		int dirsUp = 0;
		File tmpFile = article.getParentFile();
		String fileName = article.getName();
		while (!_docdir.equals(tmpFile.getName())) {
			tmpFile = tmpFile.getParentFile();
			dirsUp++;
		}

		if (!_docdir.equals(tmpFile.getName())) {
			throw new BuildException("File not in docdir");
		}

		try {
			System.out.println("inside " + article.getPath());

			LineNumberReader in =
				new LineNumberReader(new FileReader(article));
			String line;
			try {
				while ((line = in.readLine()) != null) {
					List<String> newErrors = CheckImageUtil.checkImgSrc(
						line, _article, in.getLineNumber(), dirsUp, _imagedir);

					if (!newErrors.isEmpty()) {
						errors.addAll(newErrors);
					}
				}
			} catch (IOException e) {
				throw new BuildException(e.getLocalizedMessage());
			}
		} catch (FileNotFoundException e) {
			throw new BuildException(e.getLocalizedMessage());
		}

		// Check for errors

		if (!errors.isEmpty()) {

			// Print errors
			
			System.out.println("ERROR - Missing image source files ...");
			for (String error : errors) {
				System.out.println(error);
			}

			throw new BuildException("Missing image source files");
		}

		System.out.println("Finished checking image sources.");
	}

	public void setArticle(String article) {
		_article = article;
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setImagedir(String imagedir) {
		_imagedir = imagedir;
	}

	private String _docdir;
	private String _article;
	private String _imagedir = "images";
}
