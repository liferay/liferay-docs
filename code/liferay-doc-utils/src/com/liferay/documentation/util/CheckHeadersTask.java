package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckHeadersTask extends Task {

	@Override
	public void execute() throws BuildException {

		String docDir = _docdir;
		String productType = _productType;

		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}

		for (String dirType : dirTypes) {

			File articlesDir = new File("../" + docDir + "/articles" + dirType);
			File docSetDir = new File("../" + docDir);

			if (!articlesDir.exists()) {
				if (!dirType.contains("dxp")) {
					throw new BuildException("FAILURE - no articles directory " + articlesDir);
				}
				else {
					continue;
				}
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

			for (int i = 0; i < fileList.size(); i++) {
				String filename = fileList.get(i);
				File inFile = new File(filename);

				try {
					LineNumberReader in = new LineNumberReader(
							new FileReader(inFile));

					String line;
					String titleLine = null;
					String titleLineError1 = null;
					String titleLineError2 = null;
					int counter = 0;
					boolean headerSyntaxExists = false;

					while ((line = in.readLine()) != null) {
						if (counter == 2) {
							headerSyntaxExists = true;

							titleLine = Files.readAllLines(Paths.get(filename)).get(in.getLineNumber());
							titleLineError1 = Files.readAllLines(Paths.get(filename)).get(in.getLineNumber() - 1);
							titleLineError2 = Files.readAllLines(Paths.get(filename)).get(in.getLineNumber() + 1);

							counter = 0;
						}
						if (line.startsWith("---")) {
							counter++;
						}
					}

					if (titleLine != null) {

						// Check whether the markdown file starts with the proper single #
						// header. 
						// If it doesn't, throw an exception identifying the file

						if (!titleLine.startsWith("# ")) {

							String message;

							if (titleLineError1.startsWith("# ") || titleLineError2.startsWith("# ")) {
								message = "FAILURE - " + filename +
										": File's single # header is spaced incorrectly.";
							}
							else {
								message = "FAILURE - " + filename +
										": File does not start with a single # for a header";
							}

							if (titleLine.startsWith("<!--")) {

								in.close();

								throw new BuildException(message);
							}
							else if (titleLine.startsWith("<")) {

								// Allow non-comment tags

								continue;
	
							}

							in.close();

							throw new BuildException(message);
						}
					}

					if (!headerSyntaxExists) {
						String message = "FAILURE - " + filename +
								": File does not start with proper header syntax.";
						in.close();
						throw new BuildException(message);
					}

					in.close();

					// Throw exception

				} catch (IOException e) {
					throw new BuildException(e.getLocalizedMessage());
				}
			}

			System.out.println("Finished checking headers in articles" + dirType);
		}
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private String _docdir;
	private String _productType;
}
