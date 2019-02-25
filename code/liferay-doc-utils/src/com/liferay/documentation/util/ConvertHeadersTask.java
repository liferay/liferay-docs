package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ConvertHeadersTask extends Task {

	public void execute() throws BuildException {

		String docDir = _docdir;

		List<String> ceFileList = getFileList(docDir, "");
		List<String> dxpFileList = getFileList(docDir, "-dxp");
		
		List<String> fileList = new ArrayList<String>();
		fileList.addAll(ceFileList);
		fileList.addAll(dxpFileList);
		
		for (int i = 0; i < fileList.size(); i++) {
			String filename = fileList.get(i);
			File inFile = new File(filename);
			File outFile = new File(filename);
			String outFileTmp = outFile + ".tmp";

			try {
				LineNumberReader in =
						new LineNumberReader(new FileReader(inFile));
				BufferedWriter out =
						new BufferedWriter(new FileWriter(outFileTmp));

				String line;
				String headerPrefix = " [](id=";
				String newHeaderIdPrefix = "header-id: ";
				while ((line = in.readLine()) != null) {
					
					if (line.startsWith(newHeaderIdPrefix)) {
						throw new BuildException(filename + ": File already contains "
								+ "new header syntax.");
					}
					
					if (line.startsWith("#") && !line.startsWith("##") &&
							line.contains(headerPrefix)) {
						
						int idStartIndex = line.indexOf(headerPrefix);
						int idEndIndex = line.indexOf(")", idStartIndex);
						
						String id = line.substring(idStartIndex + headerPrefix.length(), idEndIndex);

						line = line.substring(0, idStartIndex);
						
						out.append("---\n");
						out.append(newHeaderIdPrefix + id + "\n");
						out.append("---\n\n");
						
						
					}
					else if (line.startsWith("##") && line.contains(headerPrefix)) {
						int idStartIndex = line.indexOf(" [](id=");
						line = line.substring(0, idStartIndex);
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
		}
		
	}

	private static List<String> getFileList(String docDir, String dirType)
			throws BuildException {
		
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

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	private String _docdir;
}
