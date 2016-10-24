package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScanLinks {

	public static void main(String[] args) throws IOException {

		System.out.println("Checking for broken links ...");
		
		String docDir = args[0];
		
		String[] dirTypes = {"", "-dxp"};

		for (String dirType : dirTypes) {
			        
			File articleDir = new File("../" + docDir + "/articles" + dirType);
			
			File[] articleDirFiles = articleDir.listFiles();
			List<File> articles = new ArrayList<File>();
			ArrayList<String> ldnUrls = new ArrayList<String>();
			ArrayList<String> regularUrls = new ArrayList<String>();

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

				System.out.println(" " + mkdFile.getName());

				LineNumberReader in = new LineNumberReader(new FileReader(
						mkdFile));
				String line = null;

				while ((line = in.readLine()) != null) {
					if (line.contains("](/develop/") || line.contains("](/discover/") ||
							line.contains("](/distribute/")) {
						ldnUrls.add(extractLdnUrl(line));
					}
					else if (line.contains("](www.") || line.contains("](http")) {
							regularUrls.add(extractRegularUrl(line));
					}
				}
				in.close();
			}
		}

	}
	
	private static String extractLdnUrl(String line) {

		
		int begIndex = line.indexOf("](/") + 2;
		int endIndex = line.indexOf(")", begIndex);

		String endLdnUrl = line.substring(begIndex, endIndex);
		String begLdnUrl = "https://dev.liferay.com";
		
		String ldnUrl = begLdnUrl.concat(endLdnUrl);
		
		System.out.println("URL: " + ldnUrl);
		
		return ldnUrl;
	}

private static String extractRegularUrl(String line) {

		
		int begIndex = line.indexOf("](") + 2;
		int endIndex = line.indexOf(")", begIndex);

		String url = line.substring(begIndex, endIndex);
		
		System.out.println("Regular URL: " + url);
		
		return url;
	}

}
