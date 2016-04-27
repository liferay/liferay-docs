package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.io.FileUtils;

public class ReplaceImagesPath {

	public static void replaceImagePaths(File docDir) throws IOException {

		File articleDir = new File(docDir.getAbsolutePath() + "/articles");
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
		
		for (File article : articles) {
			String articlePath = article.getAbsolutePath();
			File markdownfile = new File(articlePath);

			String source = FileUtils.readFileToString(markdownfile);
			
			String find = "../images-dxp/";
			String replace = "../images/";
			String imagePath = source.replaceAll(find, replace);
			
			FileUtils.writeStringToFile(markdownfile, imagePath);
		}

	}

}

