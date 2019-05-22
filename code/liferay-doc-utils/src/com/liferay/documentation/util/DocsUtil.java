package com.liferay.documentation.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;

public class DocsUtil {

	public static List<String> getMarkdownFileList(String docDir, String dirType) {
		
		File articlesDir = new File("../" + docDir + "/articles" + dirType);
		File docSetDir = new File("../" + docDir);

		if (!articlesDir.exists() && !dirType.contains("dxp")) {
			throw new BuildException("FAILURE - bad articles directory " + articlesDir);
		}
		else if (!articlesDir.exists() && dirType.contains("dxp")) {
			return new ArrayList<String>();
		}

		List<File> docSetDirFolders = new ArrayList<File>();
		Queue<File> q = new LinkedList<File>();

		File articlesDirContents[] = articlesDir.listFiles();
		for (File f : articlesDirContents) {
			if (f.isDirectory()) {
				q.add(f);
			}
		}
	
		while (!q.isEmpty()) {
			File f = q.remove();
			docSetDirFolders.add(f);
			File[] files = f.listFiles();
		
			for (File file : files) {
				if (file.isDirectory()) {
					q.add(file);
				}
			}
		}

		docSetDirFolders.add(articlesDir);
		docSetDirFolders.add(docSetDir);
	
		File docSetDirFoldersArray[] = docSetDirFolders.toArray(new File[docSetDirFolders.size()]);
	
		List<String> fileList = new ArrayList<String>();

		for (int i = 0; i < docSetDirFoldersArray.length; i++) {
			File files[] = docSetDirFoldersArray[i].listFiles(new FilenameFilter() {
				String filePatternArg =
						"([^\\\\\\[\\]\\|:;%<>]+).markdown";
				Pattern fileNamePattern = Pattern.compile(filePatternArg);

				public boolean accept(File file, String name) {
					return (fileNamePattern.matcher(name).matches());
				}
			});
		
			for (int j = 0; j < files.length; j++) {
				fileList.add(files[j].getPath());
			}
		
		}
		
		return fileList;
	}
}