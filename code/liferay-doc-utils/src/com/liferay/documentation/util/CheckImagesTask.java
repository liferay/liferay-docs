package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckImagesTask extends Task {

	@Override
	public void execute() throws BuildException {
		List<String> errors = new ArrayList<String>();

		System.out.println("Start checking image sources ...");

		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" could not be found");
		}

		if (!dir.isDirectory()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" is not a directory");
		}

		File[] files = dir.listFiles();

		if (files.length == 0) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
				" is empty");
		}

		File langDir = null;
		for (File file : files) {
			if (file.isDirectory() && file.getName().equals(_lang)) {
				langDir = file;
				break;
			}
		}

		if (langDir == null) {
			throw new BuildException("Missing language directory (" + _lang +
				") within doc.dir " + _docdir);
		}

		try {
			File chDir = new File(langDir.getPath()+ "/chapters");
			if (!chDir.exists() || !chDir.isDirectory()) {
				throw new BuildException("Missing chapters directory " +
						chDir.getAbsolutePath());
			}

			boolean foundChapterFile = false;

			for (File chFile : chDir.listFiles()) {
				if (!chFile.getName().endsWith("markdown")) {
					continue;
				}

				if (_chapter != null &&
					!chFile.getName().startsWith(_chapter)) {
					continue;
				}

				foundChapterFile = true;

				System.out.println("inside " + chFile.getPath());

				LineNumberReader in =
					new LineNumberReader(new FileReader(chFile));
				String line;
				try {
					while ((line = in.readLine()) != null) {
						List<String> newErrors = _checkImgSrc(
								line, chFile.getName(), in.getLineNumber());

						if (!newErrors.isEmpty()) {
							errors.addAll(newErrors);
						}
					}
				} catch (IOException e) {
					throw new BuildException(e.getLocalizedMessage());
				}
			}

			if (_chapter != null && !foundChapterFile) {
				throw new BuildException("ERROR - Missing chapter: " +
					_chapter);
			}

		} catch (FileNotFoundException e) {
			throw new BuildException(e.getLocalizedMessage());
		}

		// Check for errors

		if (!errors.isEmpty()) {

			// Print errors
			
			System.out.println("ERROR - Missing image source files ...");
			for (String error : errors) {
				System.out.println(error);
			}

			throw new BuildException("Missing image source files");
		}

		System.out.println("Finished checking image sources.");
	}

	public void setChapter(String chapter) {
		if (!chapter.startsWith("$")) {
			_chapter = chapter;
		}
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setLang(String lang) {
		_lang = lang;
	}

	private static List<String> _checkImgSrc(
			String content, String file, int line) {

		List<String> warnings = new ArrayList<String>();

		int pos = -1;
		while ((pos=content.indexOf("![")) != -1) {
			int x = content.indexOf("](", pos);
			int y = content.indexOf(")", x);

			if (y != -1) {
				String imgSrc = content.substring(x + 2, y);

				imgSrc = imgSrc.replace("../../", "./");

				if (!imgSrc.contains("CDATA") &&
					!(new File(imgSrc).exists())) {

					StringBuffer sb = new StringBuffer("\t");
					sb.append(imgSrc);
					sb.append(" in ");
					sb.append(file);
					sb.append(":");
					sb.append(line);
					warnings.add(sb.toString());
				}

				content = content.substring(y + 1);
			}
			else if (x != -1){
				content = content.substring(x + 2);
			}
			else {
				content = content.substring(pos + 2);
			}
		}

		return warnings;
	}

	private String _chapter;
	private String _docdir;
	private String _lang;
}
