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
import java.util.Queue;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberHeadersTask extends Task {

	@Override
	public void execute() throws BuildException {

		String docDir = _docdir;
		String productType = _productType;

		boolean dxpBuild = false;

		ceFileList = getFileList(docDir, "");
		
		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			File articlesDxpDir = new File("../" + docDir + "/articles-dxp");
			
			if (articlesDxpDir.exists()) {
				dirTypes.add("-dxp");
				dxpBuild = true;
			
				dxpFileList = getFileList(docDir, "-dxp");
			}

		}
		
		if (ceFileList.size() == 0 && dxpFileList.size() == 0) {
			throw new BuildException(
					"FAILURE - No files in this directory");
		}

		List<String> duplicateFiles = new ArrayList<String>();

		if (dxpBuild) {
			duplicateFiles = getDuplicateFiles(docDir, dirTypes);
		}

		for (String dirType : dirTypes) {
			
			System.out.println(
					"Numbering headers for files in ../" + docDir + "/articles" +
							dirType + " ...");
			
			List<String> fileList = new ArrayList<String>();
			
			if (dirType.contains("dxp")) {
				fileList = dxpFileList;
			}
			else {
				fileList = ceFileList;
			}

			for (int i = 0; i < fileList.size(); i++) {
				String filename = fileList.get(i);
				File inFile = new File(filename);
				File outFile = new File(filename);
				String outFileTmp = outFile + ".tmp";
				
				foundDuplicateIds = false;
				
				if (dirType.contains("dxp")) {
					overrideFile = isOverrideFile(filename, duplicateFiles);
				}

				try {
					LineNumberReader in =
							new LineNumberReader(new FileReader(inFile));
					BufferedWriter out =
							new BufferedWriter(new FileWriter(outFileTmp));

					String line;
					int titleHeaderLineNum = -2;
					boolean headerValidated = false;
					while ((line = in.readLine()) != null) {

						int lineNum = in.getLineNumber();

						if (line.startsWith("#") && !line.startsWith("##") && !headerValidated) {
						
							titleHeaderLineNum = in.getLineNumber();
							line = line.trim();
							
							// search for header ID; if header DNE, generate header
							String headerIdLine = getHeaderIdLine(inFile);
								
							if (headerIdLine == null) {
								String headerId = generateNewHeader(filename, line, in.getLineNumber());
								out.append("---\n");
								out.append(headerIdPrefix + headerId + "\n");
								out.append("---\n\n");
							}
							// validate existing header
							else {
								validateHeaderId(filename, headerIdLine, in.getLineNumber(), true);
							}

							headerValidated = true;
						}
						if (lineNum == titleHeaderLineNum + 1) {
							if (!line.equals("")) {
								throw new BuildException("Filename: " + filename +
										" Line following main title header must be blank.");
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
					throw new BuildException(e.getLocalizedMessage());
				}

				if (foundDuplicateIds && !overrideFile) {
					throw new BuildException("FAILURE - Duplicate header IDs exist");
				}
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
				
				try {
					checkOverrideHeaders(duplicateFile, duplicateFileDxp);
					if (foundDuplicateIds) {
						throw new BuildException("FAILURE - Duplicate header IDs exist");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// This clears the header IDs stored; this avoids false reports of
		// duplicated IDs if the task is run again during the same process.
		IDS.clear();
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
		
		String headerIdLineCe = getHeaderIdLine(inFile);
		String headerIdLineDxp = getHeaderIdLine(inFile2);

		boolean equalHeaders = false;
		
		if (filenamesWithPresetHeader.contains(duplicateFile) && 
				filenamesWithPresetHeader.contains(duplicateFile2)) {
			
			if (headerIdLineCe.equals(headerIdLineDxp)) {
				equalHeaders = true;
			}
			else {
				headerIdLineDxp = headerIdLineCe;
			}
			
		}
		else if (filenamesWithPresetHeader.contains(duplicateFile) && 
				filenamesWithoutPresetHeader.contains(duplicateFile2)) {
			
			headerIdLineDxp = headerIdLineCe;
		}
		else if (filenamesWithoutPresetHeader.contains(duplicateFile) && 
				filenamesWithPresetHeader.contains(duplicateFile2)) {
			
			headerIdLineCe = headerIdLineDxp;

			// Check cases where there are two DXP override files with matching
			// header IDs. This case is not checked with the general validation
			// logic. Add new CE header to ID list and validate it.
			IDS.put(getHeaderId(headerIdLineCe), inFile.getName());

			// Disable overrideFile flag, so validation for the new header ID can
			// process.
			overrideFile = false;
			validateHeaderId(inFile.getName(), headerIdLineCe, in.getLineNumber(), false);
		}
		else {
			headerIdLineDxp = headerIdLineCe;
		}
		
		if (!equalHeaders) {
			String line;
			
			while ((line = in.readLine()) != null) {
				if (line.startsWith(headerIdPrefix)) {
					out.append(headerIdLineCe);
					out.append("\n");
				}
				else {
					out.append(line);
					out.append("\n");
				}
			}

			in.close();	
			out.flush();
			out.close();

			FileUtils.copyFile(
					outFileTmpFile,
					new File(duplicateFile));

			FileUtils.forceDelete(outFileTmpFile);

			while ((line = in2.readLine()) != null) {
				if (line.startsWith(headerIdPrefix)) {
					out2.append(headerIdLineDxp);
					out2.append("\n");
				}
				else {
					out2.append(line);
					out2.append("\n");
				}
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

		// Applicable for legacy IDs from 7.1 and earlier.
		// This ignores old header ID syntax.
		if (heading2.contains(" [](id=")) {
			heading2 = line.substring(indexOfFirstHeaderChar, line.indexOf(" [](id="));
		}
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
	
	private static String generateNewHeader(String filename, String line, int lineNum) {

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
			throw new BuildException("WARNING - "  + filename + ":" +
				lineNum + " is missing header text.");
		}

		int idCount = -1;
		String newHeading = null;
		while (true) {

			newHeading = assembleId(heading, idCount);

				if (IDS.get(newHeading) == null) {

					// Heading is unique for the document set

					// Map the ID to the filename

					IDS.put(newHeading, filename);

					break;
				}

			idCount++;
		}

		return newHeading;
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
			List<String> dirTypes) {
		
		List<String> convertedFileList = new ArrayList<String>();
		List<String> duplicateFiles = new ArrayList<String>();
		
		for (String f : dxpFileList) {
			f = f.replace("\\articles-dxp\\", "\\articles\\");
			f = f.replace("/articles-dxp/", "/articles/");
			convertedFileList.add(f);
		}
		
		duplicateFiles = new ArrayList<String>(ceFileList);
		duplicateFiles.retainAll(convertedFileList);
		
		return duplicateFiles;
	}
	
	private static List<String> getFileList(String docDir, String dirType)
			throws BuildException {
		
		File articlesDir = new File("../" + docDir + "/articles" + dirType);
		File docSetDir = new File("../" + docDir);

		if (!articlesDir.exists() || !articlesDir.isDirectory()) {
			throw new BuildException(
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
		
		return fileList;
	}
	
	private static String getHeaderId(String headerIdLine) {

		int idStartIndex = headerIdLine.indexOf(headerIdPrefix) + headerIdPrefix.length();
		int idEndIndex = headerIdLine.length();

		String id = null;
		if (idStartIndex > 0 && idEndIndex > (idStartIndex + 1)) {
			id = headerIdLine.substring(idStartIndex, idEndIndex);
		}
		
		return id;
	}

	private static String getHeaderIdLine(File file) throws IOException {

		String headerIdLine;

		LineNumberReader in = new LineNumberReader(new FileReader(file));

		while ((headerIdLine = in.readLine()) != null) {
			if (headerIdLine.startsWith(headerIdPrefix)) {
				break;
			}
		}
		in.close();

		if (headerIdLine != null) {
			headerIdLine = headerIdLine.trim();
		}

		return headerIdLine;
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
	
	private static void validateHeaderId(String filename, String headerIdLine, int lineNum, boolean presetHeader) {

		if (presetHeader) {
			filenamesWithPresetHeader.add(filename);
		}
		// Extract the header ID

		String id = getHeaderId(headerIdLine);

		if (id.length() > MAX_ID_LEN) {
			StringBuilder sb =
				new StringBuilder("FAILURE - ID longer than ");
			sb.append(MAX_ID_LEN);
			sb.append(" chars in ");
			sb.append(filename);
			sb.append(" - ");
			sb.append(id);
			throw new BuildException(sb.toString());
		}
		
		// Check if the primary ID is already in use

		String filename2 = IDS.get(id);

		if (filename2 != null && !overrideFile) {

			//print error

			System.out.println("Dup id:" + id + " file:" +
					filename + " line:" + lineNum + " (already used by file:" +
					filename2 + ")");

			foundDuplicateIds = true;
		}
		else {

			// Add the ID

			IDS.put(id, filename);
		}
	}

	private static boolean foundDuplicateIds;

	private static boolean overrideFile;

	private static final int MAX_ID_LEN = 75;

	private static HashMap<String, String> IDS = new HashMap<String, String>();
	
	private static List<String> ceFileList = new ArrayList<String>();
	
	private static List<String> dxpFileList = new ArrayList<String>();

	private static List<String> filenamesWithPresetHeader = new ArrayList<String>();
	
	private static List<String> filenamesWithoutPresetHeader = new ArrayList<String>();

	private static String headerIdPrefix = "header-id: ";

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private String _docdir;
	private String _productType;
}
