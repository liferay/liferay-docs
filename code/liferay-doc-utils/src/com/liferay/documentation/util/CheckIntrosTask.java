package com.liferay.documentation.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckIntrosTask extends Task {
	
	@Override
	public void execute() throws BuildException {

		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
					" could not be found");
		}

		File articleDir = new File(dir.getAbsolutePath() + "/articles");
		File[] articles = articleDir.listFiles();
		List<File> errorDirs = new ArrayList<File>();

		for (File article : articles) {
			if (article.getName().contains(".")) {
				continue;
			}

			File[] allFiles = article.listFiles();

			boolean containsIntro = false;
			boolean containsTutorials = false;

			for (File file : allFiles) {
				if (file.getName().endsWith("introduction.markdown") ||
						file.getName().endsWith("intro.markdown")) {

					containsIntro = true;
					break;
				}
				else if (file.getName().endsWith(".markdown")) {
					containsTutorials = true;
				}
			}

			if (containsTutorials && !containsIntro) {
				errorDirs.add(article);
			}
		}

		if (!errorDirs.isEmpty()) {
			System.out.println("ERROR - Missing introduction markdown articles"
					+ " ...");

			for (File errorDir : errorDirs){
				System.out.println(errorDir);			
			}
			throw new BuildException("Missing introduction markdown articles");
		}

		System.out.println("Finished checking for introduction markdown "
				+ "articles.");
	}
	
	public void setDocdir(String docdir) {
		_docdir = docdir;
	}
	
	private String _docdir;
}
