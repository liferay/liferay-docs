package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

public class CheckHeadersTask {

	public static void main(String[] args) throws Exception {
		
		if (args == null || args.length < 1) {
			throw new IllegalArgumentException(
				"Requires 1 argument: ${doc.dir}");
		}

		String docDir = args[0];
		String productType = args[1];
		
		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}

		for (String dirType : dirTypes) {

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

						// Check whether the markdown file starts with the proper single #
						// header. 
						// If it doesn't, throw an exception identifying the file

						if (!line.startsWith("# ")) {

							String message = "FAILURE - " + filename +
									":Line 1 does not start with a single # for a header";

							if (line.startsWith("<!--")) {

								in.close();

								throw new Exception(message);
							}
							else if (line.startsWith("<")) {

								// Allow non-comment tags

								continue;
	
							}

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
			
			System.out.println("Finished checking headers in articles" + dirType);
		}
	}
}