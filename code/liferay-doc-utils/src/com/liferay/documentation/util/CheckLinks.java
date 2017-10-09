package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.liferay.portal.kernel.util.Validator;

public class CheckLinks {

	public static void main(String[] args) throws IOException {

		String legacyLinks = args[0];
		checkLegacyLinks = Boolean.parseBoolean(legacyLinks);

		String docDir = args[1];

		File currentArticleDir = new File("../" + docDir + "/articles");

		List<File> currentArticles = findCurrentDirArticles(currentArticleDir);

		assignReferencedDirArticles(articleDirs);

		userGuideHeaders = findHeaders(userGuideArticles);
		adminGuideHeaders = findHeaders(adminGuideArticles);
		userGuideReferenceHeaders = findHeaders(userGuideReferenceArticles);
		tutorialHeaders = findHeaders(tutorialArticles);
		devGuideReferenceHeaders = findHeaders(devGuideReferenceArticles);

		for (File article : currentArticles) {

			LineNumberReader in = new LineNumberReader(new FileReader(article));
			String line = null;

			while ((line = in.readLine()) != null) {

				if (line.contains("](/develop/") || line.contains("](/discover/") ||
						line.contains("](/distribute/")) {

					String header = extractHeader(line, article, in);

					String primaryHeader = null;
					validUrl = true;

					if (header.contains("#")) {
						String[] splitHeaders = header.split("#");

						primaryHeader = splitHeaders[0];
						String secondaryHeader = splitHeaders[1];

						validUrl = isUrlValid(line, article, in, primaryHeader, secondaryHeader);
					}
					else if (header.equals("")) {
						continue;
					}
					else {

						primaryHeader = header;
						validUrl = isUrlValid(line, article, in, primaryHeader, null);
					}

					if (!validUrl) {
						resultsNumber = resultsNumber + 1;

						System.out.println(resultsNumber + ". " + "**INVALID URL**\n File: " +
								article.getName() + ":" + in.getLineNumber() + "\n" +
								" Line: " + line);

					}

				}
			}

			in.close();
		}
		System.out.println("\nTotal Broken Links: " + resultsNumber);
	}

	/**
	 * Returns all the possible referenced headers. This method scans the
	 * current line and assigns it to the appropriate header list.
	 *
	 * @param  line the line containing a relative URL
	 * @return the referenced headers
	 */
	private static ArrayList<List<String>> assignDirHeaders(String line) {

		ArrayList<List<String>> headers = new ArrayList<List<String>>();

		if (line.contains(userGuideDir)) {
			headers = userGuideHeaders;
		}

		if (line.contains(adminGuideDir)) {
			headers = adminGuideHeaders;
		}

		if (line.contains(userGuideReferenceDir)) {
			headers = userGuideReferenceHeaders;
		}

		if (line.contains(tutorialDir)) {
			headers = tutorialHeaders;
		}

		if (line.contains(devGuideReferenceDir)) {
			headers = devGuideReferenceHeaders;
		}

		return headers;
	}

	/**
	 * Finds the articles from other possible referenced directories and sets
	 * them to the appropriate local variables.
	 * 
	 * @param dirs the popular directories residing in the
	 *        <code>liferay-docs</code> repository
	 */
	private static void assignReferencedDirArticles(String[] dirs) {

		for (String articleDir : articleDirs) {

			if (articleDir.equals(userGuideDir)) {
				userGuideArticles = findArticles(userGuideDir);
			}

			if (articleDir.equals(adminGuideDir)) {
				adminGuideArticles = findArticles(adminGuideDir);
			}

			if (articleDir.equals(userGuideReferenceDir)) {
				userGuideReferenceArticles = findArticles(userGuideReferenceDir);
			}

			if (articleDir.equals(tutorialDir)) {
				tutorialArticles = findArticles(tutorialDir);
			}

			if (articleDir.equals(devGuideReferenceDir)) {
				devGuideReferenceArticles = findArticles(devGuideReferenceDir);
			}
		}
	}

	/**
	 * Returns the header ID contained in the given line. For example, the
	 * following line:
	 * 
	 * <p>
	 * <pre>
	 * <code>
	 * [here](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * returns
	 *
	 * <p>
	 * <pre>
	 * <code>
	 * installing-liferay-portal#liferay-home
	 * </code>
	 * </pre>
	 * </p>
	 *
	 * @param  line the line from which to extract the header
	 * @param  article the article containing the line
	 * @param  in the line number reader
	 * @return the header ID
	 */
	private static String extractHeader(String line, File article, LineNumberReader in) {

		int begIndex = line.lastIndexOf("/") + 1;
		int endIndex = line.indexOf(")", begIndex);

		String header = "";

		try {
			header = line.substring(begIndex, endIndex);
		} catch(Exception e) {
			resultsNumber = resultsNumber + 1;

			System.out.println(resultsNumber + ". " + "**CORRUPT URL FORMATTING**\n"
					+ "File: " + article.getName() + ":" + in.getLineNumber() + "\n" +
					" Line: " + line);
		}

		return header;
	}

	/**
	 * Returns the fully qualified LDN URL from the given line.
	 *
	 * @param  line the line from which to extract the URL
	 * @param  lineNumber the line number
	 * @param  fileName the article's name
	 * @return the LDN URL
	 */
	private static String extractLdnUrl(String line, int lineNumber, String fileName) {

		int begIndex = line.indexOf("](/") + 2;
		int endIndex = line.indexOf(")", begIndex);
		String endLdnUrl = null;

		try{
			endLdnUrl = line.substring(begIndex, endIndex);
		} catch (StringIndexOutOfBoundsException e) {
			endLdnUrl = line.substring(begIndex, line.length());
			resultsNumber = resultsNumber + 1;

			System.out.println(resultsNumber + ". " + "**CORRUPT URL FORMATTING**\n"
					+ "File: " + fileName + ":" + lineNumber + "\n" +
					" Line: " + line);
		}

		ldnArticle = endLdnUrl;

		String begLdnUrl = "https://dev.liferay.com";

		String ldnUrl = begLdnUrl.concat(endLdnUrl);

		return ldnUrl;
	}

	/**
	 * Returns the Markdown articles contained in the given path.
	 *
	 * @param  path the partial path for the articles (e.g.,
	 *         <code>develop/tutorials</code>
	 * @return the Markdown articles
	 */
	private static List<File> findArticles(String path) {

		File dir = new File("../../" + path + "/articles");
		File[] dirFiles = dir.listFiles();
		List<File> articles = new ArrayList<File>();

		Queue<File> q = new LinkedList<File>();
		for (File f : dirFiles) {
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

		return articles;
	}

	/**
	 * Returns the Markdown articles located in the current directory.
	 *
	 * @param  articleDir the current directory from which the Ant task was
	 *         executed
	 * @return the current directory's Markdown articles
	 */
	private static List<File> findCurrentDirArticles(File articleDir) {

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

		return articles;
	}

	/**
	 * Returns the headers (primary and secondary) of the given articles. The
	 * headers are returned as an array list: primary headers contained in index
	 * <code>0</code> and secondary headers contained in index <code>1</code>.
	 *
	 * @param  articles the articles for which to find headers
	 * @return the primary and secondary headers
	 * @throws IOException if an IO exception occurred
	 */
	private static ArrayList<List<String>> findHeaders(List<File> articles) throws IOException {

		List<String> primaryHeaders = new ArrayList<String>();
		List<String> secondaryHeaders = new ArrayList<String>();

		for (File article : articles) {

			LineNumberReader in = new LineNumberReader(new FileReader(article));
			String line = null;

			while ((line = in.readLine()) != null) {

				if (line.contains("#") && line.contains("[](id=")) {

					int begIndex = line.indexOf("[](id=") + 6;
					int endIndex = line.lastIndexOf(")");
					String header = line.substring(begIndex, endIndex);

					if (line.contains("##")) {
						secondaryHeaders.add(header);
					}
					else {
						primaryHeaders.add(header);
					}
				}

			}
			in.close();
		}

		ArrayList<List<String>> headers = new ArrayList<List<String>>();
		headers.add(primaryHeaders);
		headers.add(secondaryHeaders);

		return headers;
	}

	/**
	 * Returns <code>true</code> if the LDN URL is valid. This method is used to
	 * check legacy URLs hosted on LDN.
	 *
	 * @param  url the URL to check
	 * @param  fileName the article's name
	 * @param  lineNumber the line number
	 * @return <code>true</code> if the LDN URL is valid; <code>false</code>
	 *         otherwise
	 */
	private static boolean isLdnUrlValid(String url, String fileName, int lineNumber) {

		NodeList list = new NodeList();
		boolean validLDNURL = false;

		try {
			Parser htmlParser = new Parser(url);
			list = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
		} catch (ParserException e) {
			resultsNumber = resultsNumber + 1;

			System.out.println(resultsNumber + ". " + "**INVALID URL**\n File: " +
					fileName + ":" + lineNumber + "\n" +
					" Line: " + ldnArticle);	
		}

		List<String> results = new LinkedList<String>();

		for (int i = 0; i < list.size(); i++) {

			LinkTag link = (LinkTag) list.elementAt(i);
			String linkString = link.getLink();
			results.add(linkString);
		}

		for (String x : results) {
			if (x.contains("2Fsearch&#x25;2Fsearch&#x26;_3_redirect&#x3d;")) {

				resultsNumber = resultsNumber + 1;

				System.out.println(resultsNumber + ". " + "**INVALID URL**\n File: " +
						fileName + ":" + lineNumber + "\n" +
						" Line: " + ldnArticle);
			}
			else {
				validLDNURL = true;
			}
		}

		return validLDNURL;
	}

	/**
	 * Returns <code>true</code> if the URL is valid. This method is used to
	 * check the current version of documentation by matching URLs with their
	 * header IDs contained in the local repo.
	 *
	 * @param  line the line containing the URL
	 * @param  article the article containing the URL
	 * @param  in the line number reader
	 * @param  primaryHeader the primary header ID
	 * @param  secondaryHeader the secondary header ID
	 * @return <code>true</code> if the URL is valid; <code>false</code>
	 *         otherwise
	 */
	private static boolean isUrlValid(String line, File article, LineNumberReader in,
			String primaryHeader, String secondaryHeader) {

		boolean validURL = false;
		ArrayList<List<String>> headers = new ArrayList<List<String>>();

		headers = assignDirHeaders(line);
		
		// Prevents tables with multiple links from being invalidated
		int count1 = StringUtils.countMatches(line, "/7-0/");
		int count2 = StringUtils.countMatches(line, "/6-2/");
		int count3 = StringUtils.countMatches(line, "/6-1/");

		// Check 7.0 links from local liferay-docs repo
		if (line.contains("/7-0/") && count1 < 2) {

			if (Validator.isNull(secondaryHeader)) {

				if (headers.get(0).contains(primaryHeader)) {
					validURL = true;
				}
			}
			else {
				if (headers.get(0).contains(primaryHeader) &&
						headers.get(1).contains(secondaryHeader)) {
					validURL = true;
				}
			}
		}

		// Check legacy URLs by checking remote LDN site. These links must be
		// published to LDN before this tool can verify them.
		else if (checkLegacyLinks && (line.contains("/6-2/") || line.contains("/6-1/")) &&
				(count2 < 2 && count3 < 2)) {

			String ldnUrl = extractLdnUrl(line, in.getLineNumber(), article.getName());
			validURL = isLdnUrlValid(ldnUrl, article.getName(), in.getLineNumber());
		}
		else {
			validURL = true;
		}

		return validURL;
	}

	private static boolean checkLegacyLinks;
	private static String ldnArticle;
	private static int resultsNumber = 0;
	private static boolean validUrl;

	// User Guide

	private static String userGuideDir = "discover/portal";
	private static List<File> userGuideArticles = new ArrayList<File>();
	private static ArrayList<List<String>> userGuideHeaders = new ArrayList<List<String>>();

	private static String adminGuideDir = "discover/deployment";
	private static List<File> adminGuideArticles = new ArrayList<File>();
	private static ArrayList<List<String>> adminGuideHeaders = new ArrayList<List<String>>();

	private static String userGuideReferenceDir = "discover/reference";
	private static List<File> userGuideReferenceArticles = new ArrayList<File>();
	private static ArrayList<List<String>> userGuideReferenceHeaders = new ArrayList<List<String>>();

	// Dev Guide

	private static String tutorialDir = "develop/tutorials";
	private static List<File> tutorialArticles = new ArrayList<File>();
	private static ArrayList<List<String>> tutorialHeaders = new ArrayList<List<String>>();

	private static String devGuideReferenceDir = "develop/reference";
	private static List<File> devGuideReferenceArticles = new ArrayList<File>();
	private static ArrayList<List<String>> devGuideReferenceHeaders = new ArrayList<List<String>>();

	private static String[] articleDirs = {userGuideDir, adminGuideDir, userGuideReferenceDir,
			tutorialDir, devGuideReferenceDir};
}
