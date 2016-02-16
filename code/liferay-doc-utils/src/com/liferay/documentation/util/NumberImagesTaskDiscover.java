package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberImagesTaskDiscover extends Task {
	
	@Override
	public void execute() throws BuildException {

		File articleDir = new File("articles");
		System.out.println("Numbering images for files in "
				+ articleDir.getPath() + " ...");

		if (!articleDir.exists() || !articleDir.isDirectory()) {
			throw new BuildException("FAILURE - bad chapters directory " + articleDir);
		}
		
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

		
		if (articles.isEmpty()) {
			throw new BuildException("FAILURE - no markdown files found in " + articleDir.getAbsolutePath());
		}
		
		for (File article : articles) {
			String articlePath = article.getAbsolutePath();
			
			try {
				ResetImagesDiscover.resetImages(articlePath);
				NumberImagesDiscover.numberImages(articlePath);
			}
			catch (IOException ie) {
				throw new BuildException(ie.getLocalizedMessage());
			}
		}
	}

	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	private String docDir;

}