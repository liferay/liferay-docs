package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Task;

public class NumberHeadersSiteMain extends Task {

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 1) {
			throw new IllegalArgumentException("Requires 1 argument: docDir");
		}

		String docDir = args[0];
		String productType = args[1];
		String token = args[2];

		token = "@" + token + "@";
		boolean dxpBuild = false;
		boolean foundDuplicateIds = false;
		
		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
			dxpBuild = true;
		}

		List<String> duplicateFiles = new ArrayList<String>();

		if (dxpBuild) {
			duplicateFiles = getDuplicateFiles(docDir, dirTypes);
		}

		for (String dirType : dirTypes) {
			
			System.out.println(
					"Numbering headers for files in ../" + docDir + "/articles" +
							dirType + " ...");
			
			List<String> fileList = getFileList(docDir, dirType);

			if (fileList.isEmpty()) {
				throw new Exception(
						"FAILURE - no markdown files found in ../" + docDir +
						"/articles" + dirType);
			}

			for (int i = 0; i < fileList.size(); i++) {
				String filename = fileList.get(i);
				File inFile = new File(filename);
				File outFile = new File(filename);
				String outFileTmp = outFile + ".tmp";
				
				overrideFile = isOverrideFile(filename, duplicateFiles);

				Map<String, Integer> secondaryIds = new HashMap<String, Integer>();

				try {
					LineNumberReader in =
							new LineNumberReader(new FileReader(inFile));
					BufferedWriter out =
							new BufferedWriter(new FileWriter(outFileTmp));

					String line;
					while ((line = in.readLine()) != null) {
						if (line.startsWith("#") && !line.contains(token)) {
						
							line = line.trim();

							String newHeadingLine = handleHeaderLine(line,
									filename, in.getLineNumber(), secondaryIds);
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
							new File(filename));

					FileUtils.forceDelete(new File(outFileTmp));
				} catch (IOException e) {
					throw new Exception(e.getLocalizedMessage());
				}
			}

			if (foundDuplicateIds && !overrideFile) {
				throw new Exception("FAILURE - Duplicate header IDs exist");
			}
		}
		
		// Make sure override files have same header IDs as original
		
		if(!duplicateFiles.isEmpty()) {
			for(String duplicateFile : duplicateFiles) {
				String duplicateFileDxp = duplicateFile;
				duplicateFileDxp = duplicateFileDxp.replace(
						"\\articles\\", "\\articles-dxp\\");
				duplicateFileDxp = duplicateFileDxp.replace(
						"/articles/", "/articles-dxp/");
				
				checkOverrideHeaders(duplicateFile, duplicateFileDxp);
			}
		}
	}

	private static String assembleId(String heading, int idCount) {

		String count = "";
		if (idCount > -1) {
			count = "-" + idCount;
		}

		int idLength = heading.length() + count.length();
		if (idLength >  MAX_ID_LEN) {
			heading = heading.substring(
				0,
				(heading.length() - Math.abs(idLength -  MAX_ID_LEN)));
		}

		StringBuffer sb = new StringBuffer(heading);
		sb.append(count);
	
		String finalHeaderId = sb.toString();
		
		if (finalHeaderId.contains("--")) {
			finalHeaderId = finalHeaderId.replaceAll("--", "-");
		}

		return finalHeaderId;
	}
	
	private static void checkOverrideHeaders(String duplicateFile, String duplicateFile2) 
			throws IOException {
		
		File inFile = new File(duplicateFile);
		File outFile = new File(duplicateFile);
		File inFile2 = new File(duplicateFile2);
		File outFile2 = new File(duplicateFile2);
		String outFileTmp = outFile + ".tmp";
		String outFileTmp2 = outFile2 + ".tmp";
		File outFileTmpFile = new File(outFileTmp);
		File outFileTmpFile2 = new File(outFileTmp2);
		
		LineNumberReader in =
				new LineNumberReader(new FileReader(inFile));
		BufferedWriter out =
				new BufferedWriter(new FileWriter(outFileTmp));
		LineNumberReader in2 =
				new LineNumberReader(new FileReader(inFile2));
		BufferedWriter out2 =
				new BufferedWriter(new FileWriter(outFileTmp2));
		
		String header = in.readLine();
		String headerDxp = in2.readLine();
		boolean equalHeaders = false;
		
		if (filenamesWithPresetHeader.contains(duplicateFile) && 
				filenamesWithPresetHeader.contains(duplicateFile2)) {
			
			if (header.equals(headerDxp)) {
				equalHeaders = true;
			}
			else {
				headerDxp = header;
			}
			
		}
		else if (filenamesWithPresetHeader.contains(duplicateFile) && 
				filenamesWithoutPresetHeader.contains(duplicateFile2)) {
			
			headerDxp = header;
		}
		else if (filenamesWithoutPresetHeader.contains(duplicateFile) && 
				filenamesWithPresetHeader.contains(duplicateFile2)) {
			
			header = headerDxp;
		}
		else {
			headerDxp = header;
		}
		
		if (!equalHeaders) {
			String line;
			int i = 0;
			
			while ((line = in.readLine()) != null) {
				
				if (i == 0) {
					out.append(header);
					out.append("\n\n");
				}
				else {
					out.append(line);
					out.append("\n");
				}
				i = i+1;
			}

			in.close();	
			out.flush();
			out.close();

			FileUtils.copyFile(
					outFileTmpFile,
					new File(duplicateFile));

			FileUtils.forceDelete(outFileTmpFile);

			i = 0;
			while ((line = in2.readLine()) != null) {
				if (i == 0) {
					out2.append(headerDxp);
					out2.append("\n\n");
				}
				else {
					out2.append(line);
					out2.append("\n");
				}
				i = i+1;
			}

			in2.close();
			out2.flush();
			out2.close();

			FileUtils.copyFile(
					outFileTmpFile2,
					new File(duplicateFile2));

			FileUtils.forceDelete(outFileTmpFile2);
		}
		else {
			in.close();	
			out.flush();
			out.close();
			
			FileUtils.forceDelete(outFileTmpFile);
			
			in2.close();	
			out2.flush();
			out2.close();
			
			FileUtils.forceDelete(outFileTmpFile2);
		}
	}

	private static String extractHeading(String line, int indexOfFirstHeaderChar) {
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
	
	private static String getArticlesDirName(String filename) {
		int index1 = filename.indexOf("articles");
		int index2;
		if (filename.contains("\\")) {
			index2 = filename.indexOf("\\", index1);
		}
		else {
			index2 = filename.indexOf("/", index1);
		}
		
		filename = filename.substring(index2 + 1);
		
		return filename;
	}

	private static List<String> getDuplicateFiles(String docDir,
			List<String> dirTypes)
		throws Exception {
		
		List<String> cefileList = getFileList(docDir, dirTypes.get(0));
		List<String> convertedFileList = new ArrayList<String>();
		List<String> duplicateFiles = new ArrayList<String>();
		
		List<String> dxpfileList = getFileList(docDir, dirTypes.get(1));
		
		for (String f : dxpfileList) {
			f = f.replace("\\articles-dxp\\", "\\articles\\");
			f = f.replace("/articles-dxp/", "/articles/");
			convertedFileList.add(f);
		}
		
		duplicateFiles = new ArrayList<String>(cefileList);
		duplicateFiles.retainAll(convertedFileList);
		
		return duplicateFiles;
	}
	
	private static List<String> getFileList(String docDir, String dirType)
			throws Exception {
		
		File articlesDir = new File("../" + docDir + "/articles" + dirType);
		File docSetDir = new File("../" + docDir);

		if (!articlesDir.exists() || !articlesDir.isDirectory()) {
			throw new Exception(
					"FAILURE - bad articles directory " + articlesDir);
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
		
		if (fileList.size() == 0) {
			throw new Exception(
					"FAILURE - fileList is empty");
		}
		
		return fileList;
	}

	private static String handleHeaderLine(String line, String filename,
		int lineNum,  Map<String, Integer> secondaryIds) throws Exception {

		String newHeadingLine = null;

		// Check if the header contains an ID

		if (headerIdPattern.matcher(line).matches()) {
			
			filenamesWithPresetHeader.add(filename);

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

			if (line.startsWith("##")) {

				// Handle secondary ID, checking if already used in this file.

				Integer lineNum2 = secondaryIds.get(id);
				if (lineNum2 != null) {

					//print error

					System.out.println("Dup id:" + id + " file:" +
						filename + " line:" + lineNum + " (already used at line:" +
						lineNum2 + ")");
					return newHeadingLine;
				}
				else {

					// Add the ID

					secondaryIds.put(id, new Integer(lineNum));
					newHeadingLine = line;
				}
			}
			else {

				// Handle primary ID, checking if already used in this file or other files.

				String filename2 = IDS.get(id);
				
				if (filename2 != null && !overrideFile) {

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
		}
		else {
			
			// Generate an ID based on the header text and counter

			filenamesWithoutPresetHeader.add(filename);

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

			int idCount = -1;
			String newHeading = null;
			while (true) {

				newHeading = assembleId(heading, idCount);

				if (line.startsWith("##")) {
					if (secondaryIds.get(newHeading) == null) {

						// Heading is unique for the article

						// Map the ID to the line number

						secondaryIds.put(newHeading, lineNum);

						break;
					}
				}
				else {
					if (IDS.get(newHeading) == null) {

						// Heading is unique for the document set

						// Map the ID to the filename

						IDS.put(newHeading, filename);

						break;
					}
				}

				idCount++;
			}

			newHeadingLine = line + " [](id=" + newHeading + ")";
		}

		return newHeadingLine;
	}

	private static boolean isOverrideFile(String filename, List<String> duplicateFiles) {
		
		overrideFile = false;
		
		filename = getArticlesDirName(filename);
		
		for (String f : duplicateFiles) {
			if (f.contains(filename)) {
				overrideFile = true;
			}
		}

		return overrideFile;
	}

	private static final int MAX_ID_LEN = 75;

	private static HashMap<String, String> IDS = new HashMap<String, String>();

	private static Pattern headerIdPattern;

	private static List<String> filenamesWithPresetHeader = new ArrayList<String>();
	
	private static List<String> filenamesWithoutPresetHeader = new ArrayList<String>();
	
	private static boolean overrideFile;

	static {
		String patternArg = "(#)+([^\\\\\\[\\]\\|%<>]*)" +
				Pattern.quote("[") + Pattern.quote("]") +
				Pattern.quote("(") + "id" + Pattern.quote("=") +
				"([^\\\\\\[\\]\\|:;%]+)" + Pattern.quote(")") + 
				"([ \\t\\n\\x0B\\f\\r]*?)";

		headerIdPattern = Pattern.compile(patternArg);
	}

}
