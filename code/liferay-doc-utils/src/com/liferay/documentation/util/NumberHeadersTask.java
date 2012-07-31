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
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberHeadersTask extends Task {

	@Override
	public void execute() throws BuildException {
		boolean foundDuplicateIds = false;

		File chDir = new File("../" + docDir + "/" + lang + "/chapters");
		System.out.println(
			"Numbering headers for files in " + chDir.getPath() + " ...");

		if (!chDir.exists() || !chDir.isDirectory()) {
			throw new BuildException(
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
			throw new BuildException(
				"FAILURE - no markdown files found in " + chDir);
		}

		File docPropsFile = new File("../" + docDir + "/" + DOC_PROPERTIES);

		// Load properties file

		Properties props = new Properties();
		try {
			props.load(new FileReader(docPropsFile));
		}
		catch (IOException io) {
			throw new BuildException(io);
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
						
						String newHeadingLine = handleHeaderLine(line, filename,
							in.getLineNumber(), props);
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
				throw new BuildException(e.getLocalizedMessage());
			}
		}

		if (foundDuplicateIds) {
			throw new BuildException("FAILURE - Duplicate header IDs exist");
		}
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setDocDir(String docDir) {
		this.docDir = docDir;
	}

	private  String extractHeading(String line, int indexOfFirstHeaderChar) {
		String heading2 = line.substring(indexOfFirstHeaderChar);

		// Replace each space with a dash

		heading2 = heading2.replace(' ', '-').toLowerCase();

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

	private  String extractChapterNumber(String fileName) {

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

	private  String handleHeaderLine(String line, String filename, int lineNum,
		Properties props) throws BuildException {

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
				throw new BuildException("WARNING - "  + filename + ":" +
					lineNum + " is missing header text.");
			}

			String chapter = extractChapterNumber(filename);

			// Note, length of heading

			int headingLen = heading.length();

			int idCount = 0;
			String newHeading = null;
			while (true) {

				
				newHeading = assembleId(heading, props, chapter, idCount);

				int lenDiff = newHeading.length() - MAX_ID_LEN;
				if (lenDiff > 0) {
					// Trim heading
					heading = heading.substring(0, headingLen - lenDiff);
				}

				newHeading = assembleId(heading, props, chapter, idCount);
				
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

	private String assembleId(String heading, Properties props, String chapter,
			int idCount) {
		StringBuffer headingSb = new StringBuffer();
		headingSb.append(props.getProperty(PRODUCT_ABBREV));
		headingSb.append("-");
		headingSb.append(props.getProperty(PRODUCT_VERSION).replace('.', '-'));
		headingSb.append("-");
		headingSb.append(props.getProperty(DOC_ABBREV));
		headingSb.append(lang);
		headingSb.append(chapter);
		headingSb.append("-");
		headingSb.append(heading);
		headingSb.append("-");
		headingSb.append(idCount);
		return headingSb.toString();
	}

	private static final String DOC_PROPERTIES = "doc.properties";

	private static final int MAX_ID_LEN = 75;

	private static final String DOC_ABBREV = "doc.abbrev";
	private static final String PRODUCT_ABBREV = "product.abbrev";
	private static final String PRODUCT_VERSION = "product.version";

	private static HashMap<String, String> IDS = new HashMap<String, String>();

	private static Pattern headerIdPattern;

	static {
		String patternArg = "(#)+([^\\\\\\[\\]\\|%<>]*)" +
				Pattern.quote("[") + Pattern.quote("]") +
				Pattern.quote("(") + "id" + Pattern.quote("=") +
				"([^\\\\\\[\\]\\|:;%]+)" + Pattern.quote(")");

		headerIdPattern = Pattern.compile(patternArg);
	}

	private String lang;
	private String docDir;
}
