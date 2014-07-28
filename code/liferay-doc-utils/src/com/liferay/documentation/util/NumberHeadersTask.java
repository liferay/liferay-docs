package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class NumberHeadersTask {

	public NumberHeadersTask() {
		super();
	}

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 2) {
			throw new IllegalArgumentException("Requires 2 arguments: doc.dir lang");
		}

		NumberHeadersTask task = new NumberHeadersTask();
		String docDir = args[0];
		String lang = args[1];

		boolean foundDuplicateIds = false;

		File chDir = new File("../" + docDir + "/" + lang + "/chapters");
		System.out.println(
			"Numbering headers for files in " + chDir.getPath() + " ...");

		if (!chDir.exists() || !chDir.isDirectory()) {
			throw new Exception(
				"FAILURE - bad chapters directory " + chDir);
		}

		// Get listing of markdown files

		String[] files = chDir.list(new FilenameFilter() {
				String filePatternArg =
					"([0-9]+)([^\\\\\\[\\]\\|:;%<>]+).markdown";
				Pattern fileNamePattern = Pattern.compile(filePatternArg);

				public boolean accept(File file, String name) {
					return (fileNamePattern.matcher(name).matches());
				}
			});

		if (files == null || files.length == 0) {
			throw new Exception(
				"FAILURE - no markdown files found in " + chDir);
		}

		File docPropsFile = new File("../" + docDir + "/" + DOC_PROPERTIES);

		// Load properties file

		Properties props = new Properties();
		try {
			props.load(new FileReader(docPropsFile));
		}
		catch (IOException io) {
			throw io;
		}

		// Process each file

		for (int i = 0; i < files.length; i++) {
			String filename = files[i];
			// String inFile = chDir.getPath() + "\\" + filename;
			// String outFile = chDir.getPath() + "/" + filename;
			File inFile = new File(chDir, filename);
			File outFile = new File(chDir, filename);
			String outFileTmp = outFile + ".tmp";

			try {
				LineNumberReader in =
					new LineNumberReader(new FileReader(inFile));
				BufferedWriter out =
					new BufferedWriter(new FileWriter(outFileTmp));

				String line;
				while ((line = in.readLine()) != null) {

					if (line.startsWith("#")) {
						
						line = line.trim();
						
						String newHeadingLine = handleHeaderLine(line, filename,
							in.getLineNumber(), props, lang);
						if (newHeadingLine != null) {
							line = newHeadingLine;
						}
						else {
							foundDuplicateIds = true;
						}
					}

					out.append(line);
					out.append("\n");
				}
				in.close();
				
				out.flush();
				out.close();

				// Replace original file with modified temp file

				FileUtils.copyFile(
					new File(outFileTmp),
					new File(chDir.getPath() + "/" + filename));

				FileUtils.forceDelete(new File(outFileTmp));
			} catch (IOException e) {
				throw e;
			}
		}

		if (foundDuplicateIds) {
			throw new Exception("FAILURE - Duplicate header IDs exist");
		}
	}

	private static  String extractHeading(String line, int indexOfFirstHeaderChar) {
		String heading2 = line.substring(indexOfFirstHeaderChar);
		heading2 = heading2.trim();

		// Replace each spaced dash, space, dot, and slash with a dash

		heading2 = heading2.replace(" - ", "-");
		heading2 = heading2.replace(' ', '-');
		heading2 = heading2.replace('.', '-');
		heading2 = heading2.replace('/', '-');
		heading2 = heading2.toLowerCase();

		// Filter out characters other than dashes, letters, and digits

		StringBuffer headingSb = new StringBuffer();
		for (int i = 0; i < heading2.length(); i++) {
			char ch = heading2.charAt(i);

			if (ch == '-' || Character.isLetterOrDigit(ch)) {
				headingSb.append(ch);
			}
		}
		heading2 = headingSb.toString();
		return heading2;
	}

	private static  String extractChapterNumber(String fileName) {

		// Extract chapter number from filename

		String chapter = "";
		for (int i = 0; i < fileName.length(); i++) {
			if (Character.isDigit(fileName.charAt(i))) {
				chapter = chapter + fileName.charAt(i);
			}
			else {
				break;
			}
		}
		return chapter;
	}

	private static String handleHeaderLine(String line, String filename, int lineNum,
		Properties props, String lang) throws Exception {

		String newHeadingLine = null;

		// Check if the header contains an ID

		if (headerIdPattern.matcher(line).matches()) {

			// Extract the header ID

			int idStartIndex = line.indexOf("=");
			int idEndIndex = line.indexOf(")", idStartIndex);

			String id = null;
			if (idStartIndex > 0 && idEndIndex > (idStartIndex + 1)) {
				id = line.substring(idStartIndex + 1, idEndIndex);
			}

			if (id.length() > MAX_ID_LEN) {
				StringBuilder sb =
					new StringBuilder("FAILURE - ID longer than ");
				sb.append(MAX_ID_LEN);
				sb.append(" chars in ");
				sb.append(filename);
				sb.append(" - ");
				sb.append(id);
				throw new Exception(sb.toString());
			}
			
			// Check if the ID is already in use

			String filename2 = IDS.get(id);
			if (filename2 != null) {

				//print error
				
				System.out.println("Dup id:" + id + " file:" +
					filename + " line:" + lineNum + " (already used by file:" +
					filename2 + ")");
				return newHeadingLine;
			}
			else {

				// Add the ID

				IDS.put(id, filename);
				newHeadingLine = line;
			}
		}
		else {

			// Generate an ID based on the header text, file chapter
			// number, and counter

			// Find the start of the header text

			int indexOfFirstHeaderChar = -1;
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch != '#' && ch != ' ' && ch != '\t') {
					indexOfFirstHeaderChar = i;
					break;
				}
			}

			String heading = null;
			if (indexOfFirstHeaderChar > 0) {
				heading = extractHeading(line, indexOfFirstHeaderChar);
			}
			else {
				throw new Exception("WARNING - "  + filename + ":" +
					lineNum + " is missing header text.");
			}

			String chapter = extractChapterNumber(filename);

			int idCount = -1;
			String newHeading = null;
			while (true) {

				newHeading = assembleId(heading, props, chapter, lang, idCount);

				if (IDS.get(newHeading) == null) {

					// Heading is unique

					// Add the ID

					IDS.put(newHeading, filename);

					break;
				}

				idCount++;
			}

			newHeadingLine = line + " [](id=" + newHeading + ")";
		}

		return newHeadingLine;
	}

	private static String assembleId(String heading, Properties props,
		String chapter, String lang, int idCount) {

		String namespace = getNamespace(props, chapter, lang);

		String count = "";
		if (idCount > -1) {
			count = "-" + idCount;
		}

		int idLength = heading.length() + namespace.length() + count.length();
		if (idLength >  MAX_ID_LEN) {
			heading = heading.substring(
				0,
				(heading.length() - Math.abs(idLength -  MAX_ID_LEN)));
		}

		StringBuffer sb = new StringBuffer(heading);
		sb.append(namespace);
		sb.append(count);

		return sb.toString();
	}

	private static String getNamespace(Properties props, String chapter,
			String lang) {
		StringBuffer sb = new StringBuffer();

		sb.append("-");
		sb.append(props.getProperty(PRODUCT_NAME).toLowerCase().replace(' ', '-'));
		sb.append("-");
		sb.append(props.getProperty(PRODUCT_VERSION).replace('.', '-'));
		sb.append("-");
		sb.append(props.getProperty(DOC_NAME).toLowerCase().replace(' ', '-'));
		sb.append("-");
		sb.append(chapter);
		sb.append("-");
		sb.append(lang);

		return sb.toString();
	}

	private static final String DOC_PROPERTIES = "doc.properties";

	private static final int MAX_ID_LEN = 75;

	private static final String DOC_NAME = "doc.name";
	private static final String PRODUCT_NAME = "product.name";
	private static final String PRODUCT_VERSION = "product.version";

	private static HashMap<String, String> IDS = new HashMap<String, String>();

	private static Pattern headerIdPattern;

	static {
		String patternArg = "(#)+([^\\\\\\[\\]\\|%<>]*)" +
				Pattern.quote("[") + Pattern.quote("]") +
				Pattern.quote("(") + "id" + Pattern.quote("=") +
				"([^\\\\\\[\\]\\|:;%]+)" + Pattern.quote(")") + 
				"([ \\t\\n\\x0B\\f\\r]*?)";

		headerIdPattern = Pattern.compile(patternArg);
	}
}
