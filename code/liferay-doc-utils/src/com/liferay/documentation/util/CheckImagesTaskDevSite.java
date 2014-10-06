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

public class CheckImagesTaskDevSite extends Task {

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
		
		File articleDir = new File(dir.getAbsolutePath() + "/articles");
		File[] articles = articleDir.listFiles();
		List<File> chFiles = new ArrayList<File>();

		if ((!articleDir.exists() || !articleDir.isDirectory())) {
			throw new BuildException("Missing articles directory " +
					articleDir.getAbsolutePath());
		}
		
		// If there is an article limit, convert the limit from the article's
		// directory structure to the exact article name.

		if (_chapter != null) {
			
			if (!_chapter.contains("articles") ||
					!_chapter.contains(".markdown")) {
				throw new BuildException("Did not specify article directory "
						+ "structure correctly: " + _chapter);
			}
			
			if (_chapter.contains("\\")) {
				int firstBackslash = _chapter.indexOf("\\");
				int secondBackslash = _chapter.indexOf("\\", firstBackslash + 1);
				int dot = _chapter.indexOf(".");

				_chapter = _chapter.substring(secondBackslash + 1, dot);
			}
			
			else if (_chapter.contains("/")) {
				int firstSlash = _chapter.indexOf("/");
				int secondSlash = _chapter.indexOf("/", firstSlash + 1);
				int dot = _chapter.indexOf(".");

				_chapter = _chapter.substring(secondSlash + 1, dot);
			}
		}
		
		try {
			for (File article : articles) {

				if (article.getName().contains(".")) {
					continue;
				}

				File[] allFiles = article.listFiles();

				for (File file : allFiles) {
					chFiles.add(file);
				}

			}
			boolean foundChapterFile = false;

			for (File chFile : chFiles) {
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
}
