package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ScanLinks {

	public static void main(String[] args) throws IOException, ParserException {

		System.out.println("Checking for broken LDN links ...");
		System.out.println("This may take several minutes ...");
		
		String docDir = args[0];
		
		//Still need to account for DXP article links
		
		String dirType = "";

		//for (String dirType : dirTypes) {
			        
			File articleDir = new File("../" + docDir + "/articles" + dirType);
			
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

			for (File mkdFile : articles) {

				if (!mkdFile.getName().endsWith(".markdown")) {
					continue;
				}

				LineNumberReader in = new LineNumberReader(new FileReader(
						mkdFile));
				String line = null;

				while ((line = in.readLine()) != null) {
					
					if (!line.contains("localhost")) {
						if (line.contains("](/develop/") || line.contains("](/discover/") ||
								line.contains("](/distribute/")) {
							
							String ldnUrl = extractLdnUrl(line, in.getLineNumber(), mkdFile.getName());
							checkLdnUrl(ldnUrl, mkdFile.getName(), in.getLineNumber());
						}
						else if (line.contains("](develop/") || line.contains("](discover/") ||
								line.contains("](distribute/")) {
							
							String ldnArticle2 = ldnArticle.substring(1, ldnArticle.length());
							System.out.println(ldnArticle2 + " in FILENAME --- " + mkdFile.getName() + ":" + in.getLineNumber() + " is missing beginning slash");
							resultsNumber = resultsNumber + 1;
						}
						
						// Regular URL checks are unreliable. Need to investigate
						
						/*else if (line.contains("](www.") || line.contains("](http")) {
							String url = extractRegularUrl(line, in.getLineNumber(), mkdFile.getName());
							checkUrl(url, mkdFile.getName(), in.getLineNumber());
						}*/
					}
				}
				in.close();
			}
		//}
		System.out.println("Total Broken Links: " + resultsNumber);
	}
	
	private static void checkLdnUrl(String url, String fileName, int lineNumber) {
		
		NodeList list = new NodeList();
		
		try {
			Parser htmlParser = new Parser(url);
			list = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
		} catch (ParserException e) {
			System.out.println(url + " in FILENAME --- " + fileName + ":" + lineNumber + " DOES NOT EXIST");
			resultsNumber = resultsNumber + 1;
		}
		
		List<String> results = new LinkedList<String>();
		
		for (int i = 0; i < list.size(); i++) {

			LinkTag link = (LinkTag) list.elementAt(i);
			String linkString = link.getLink();
            results.add(linkString);
        }
		
		for (String x : results) {
			if (x.contains("2Fsearch&#x25;2Fsearch&#x26;_3_redirect&#x3d;")) {
				System.out.println(ldnArticle + " in FILENAME --- " + fileName + ":" + lineNumber + " DOES NOT EXIST");
				resultsNumber = resultsNumber + 1;
				break;
			}
			else {
				continue;
			}
		}
	}
	
	private static void checkUrl(String url, String fileName, int lineNumber) {
		
		try {
			URL u = new URL(url);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection(); 
			huc.setRequestMethod("GET");
			huc.connect();
		} catch (IOException e) {
			System.out.println(url + " in FILENAME --- " + fileName + ":" + lineNumber + " DOES NOT EXIST");
			resultsNumber = resultsNumber + 1;
		}
	}
	
	private static String extractLdnUrl(String line, int lineNumber, String fileName) {
		
			int begIndex = line.indexOf("](/") + 2;
			int endIndex = line.indexOf(")", begIndex);
			String endLdnUrl = null;
			
			try{
				endLdnUrl = line.substring(begIndex, endIndex);
			} catch (StringIndexOutOfBoundsException e) {
				endLdnUrl = line.substring(begIndex, line.length());
				System.out.println("Relative path in " + fileName + ":" + lineNumber + " has incorrect link ending");
				resultsNumber = resultsNumber + 1;
			}
			
			ldnArticle = endLdnUrl;
			
			String begLdnUrl = "https://dev.liferay.com";

			String ldnUrl = begLdnUrl.concat(endLdnUrl);

			return ldnUrl;
	}

	private static String extractRegularUrl(String line, int lineNumber, String fileName) {

		
		int begIndex = line.indexOf("](") + 2;
		int endIndex = line.indexOf(")", begIndex);
		String url = null;

		try {
			url = line.substring(begIndex, endIndex);
		} catch (StringIndexOutOfBoundsException e) {
			url = line.substring(begIndex, line.length());
			System.out.println("URL in " + fileName + ":" + lineNumber + " has incorrect link ending");
			resultsNumber = resultsNumber + 1;
		}
		
		return url;
	}

	private static String ldnArticle;
	private static int resultsNumber;

}
