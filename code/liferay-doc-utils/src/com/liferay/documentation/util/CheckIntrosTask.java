package com.liferay.documentation.util;

import java.io.File;
import java.io.FileFilter;
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
		File[] articleFolders = articleDir.listFiles(new FileFilter() {
		    @Override
		    public boolean accept(File file) {
		        return file.isDirectory();
		    }
		});

		for (File articleFolder : articleFolders) {
			checkFolderForIntros(articleFolder);
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

	private void checkFolderForIntros(File folder) {

		boolean containsIntro = false;

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				checkFolderForIntros(fileEntry);
			}
			else if (!containsIntro) {

				if (fileEntry.getName().endsWith("introduction.markdown") ||
						fileEntry.getName().endsWith("intro.markdown")) {

					containsIntro = true;
				}
			}
		}

		if (!containsIntro) {
			errorDirs.add(folder);
		}
	}

	private String _docdir;
	private List<File> errorDirs = new ArrayList<File>();
}
