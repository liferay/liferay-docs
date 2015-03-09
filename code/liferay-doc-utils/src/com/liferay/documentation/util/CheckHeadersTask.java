package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CheckHeadersTask {

	public static void main(String[] args) throws Exception {
		
		if (args == null || args.length < 1) {
			throw new IllegalArgumentException(
				"Requires 1 argument: ${doc.dir}");
		}

		String docDir = args[0];

		File articlesDir = new File("../" + docDir + "/articles");
		File docSetDir = new File("../" + docDir);

		if (!articlesDir.exists() || !articlesDir.isDirectory()) {
			throw new Exception(
				"FAILURE - bad articles directory " + articlesDir);
		}

		List<File> docSetDirFolders = new ArrayList<File>();
		File articlesDirContents[] = articlesDir.listFiles();
		for (int i = 0; i < articlesDirContents.length; i++) {
			if (articlesDirContents[i].isDirectory()) {
				docSetDirFolders.add(articlesDirContents[i]);
			}
		}

		docSetDirFolders.add(docSetDir);

		File docSetDirFoldersArray[] = docSetDirFolders.toArray(
			new File[docSetDirFolders.size()]);

		List<String> fileList = new ArrayList<String>();

		for (int i = 0; i < docSetDirFoldersArray.length; i++) {

			File files[] = docSetDirFoldersArray[i].listFiles(
					new FilenameFilter() {
						String filePatternArg =
							"([^\\\\\\[\\]\\|:;%<>]+).markdown";

						Pattern fileNamePattern = Pattern.compile(
							filePatternArg);

						public boolean accept(File file, String name) {
							return (fileNamePattern.matcher(name).matches());
						}
					}
				);

			for (int j = 0; j < files.length; j++) {
				fileList.add(files[j].getPath());
			}
		}

		if (fileList.isEmpty()) {
			throw new Exception(
				"FAILURE - no markdown files found in " + articlesDir);
		}

		for (int i = 0; i < fileList.size(); i++) {
			String filename = fileList.get(i);
			File inFile = new File(filename);

			try {
				LineNumberReader in = new LineNumberReader(
					new FileReader(inFile));

				String line = in.readLine();
				if (line != null) {

					// Check if markdown files start with the proper single #
					// header
					// if not, throw an exception identifying the file
					
					// Enhancement: check first to see if we've put some HTML at the top
					
					if (line.startsWith("<")) {
						
						continue;
						
					}
					
					if (!line.startsWith("# ")) {
						String message =
							filename + " does not start with single # header";

						in.close();

						throw new Exception(message);
					}
				}

				in.close();

				// Throw exception

			} catch (IOException e) {
				throw new Exception(e.getLocalizedMessage());
			}
		}
	}

}
