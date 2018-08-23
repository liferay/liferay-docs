package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class NumberImagesTask extends Task {
	
	@Override
	public void execute() throws BuildException {
		
String productType = _productType;

		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}

		for (String dirType : dirTypes) {

			File docDir = new File("../" + _docDir);
			File articleDir = new File(docDir.getAbsolutePath() + "/articles" + dirType);
			System.out.println("Numbering images for files in "
					+ articleDir.getPath() + " ...");

			if (!articleDir.exists() || !articleDir.isDirectory()) {
				throw new BuildException("FAILURE - bad chapters directory " + articleDir);
			}

			File[] articleDirFiles = articleDir.listFiles();
			List<File> articles = new ArrayList<File>();

			Queue<File> q = new LinkedList<File>();
			for (File f : articleDirFiles) {
				q.add(f);
			}

			while (!q.isEmpty()) {
				File f = q.remove(); 

				if (f.isDirectory()) {
					File[] files = f.listFiles();

					for (File file : files) {
						q.add(file);
					}
				}
				else {
					if (f.getName().endsWith(".markdown")) {
						articles.add(f);
					}
				}
			}


			if (articles.isEmpty()) {
				throw new BuildException("FAILURE - no markdown files found in " + articleDir.getAbsolutePath());
			}

			for (File article : articles) {
				String articlePath = article.getAbsolutePath();

				try {
					resetImages(articlePath);
					numberImages(articlePath);
				}
				catch (IOException ie) {
					throw new BuildException(ie.getLocalizedMessage());
				}
			}
		}
	}

	public void setDocDir(String docDir) {
		_docDir = docDir;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	private void numberImages(String markdownFilePath) throws IOException {
		File markdownfile = new File(markdownFilePath);

		String source = FileUtils.readFileToString(markdownfile);
		String find = "\\!\\[Figure [x|X]:";
		int fignum = 1;
		String replace = "\\!\\[Figure " + fignum + ":";

		//
		// Compiles the given regular expression into a pattern
		//
		Pattern pattern = Pattern.compile(find);

		//
		// Creates a matcher that will match the given input against the pattern
		//
		Matcher matcher = pattern.matcher(source);

		//
		// Replaces the first subsequence of the input sequence that matches the
		// pattern with the given replacement string
		//
		String output = matcher.replaceFirst(replace);

		//
		// Loops through file replacing all remaining matching subsequences of
		// the input sequence with the appropriate figure numbers
		//
		while (matcher.find()) {
			matcher = pattern.matcher(output);
			fignum++;
			replace = "\\!\\[Figure " + fignum + ":";
			output = matcher.replaceFirst(replace);
		}

		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);
	}

	private void resetImages(String markdownFilePath) throws IOException {
		// Linux example: "/home/$USER/workspace/01-example-chapter.markdown"
		// Windows example: "E:\liferay-docs\discover\portal\articles\01-what-is-liferay\00-what-is-liferay-intro.markdown"
		File markdownfile = new File(markdownFilePath); // example: "/home/$USER/workspace/01-example-chapter.markdown"

		String source = FileUtils.readFileToString(markdownfile);

		String find = "\\!\\[Figure " + "[0-9]:";
		String replace = "\\!\\[Figure " + "x:";

		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);

		find = "\\!\\[Figure " + "[0-9][0-9]:";

		pattern = Pattern.compile(find);
		matcher = pattern.matcher(output);
		output = matcher.replaceAll(replace);

		//
		// Saves the updated file, replaces the original
		//
		FileUtils.writeStringToFile(markdownfile, output);

		/* This code is for saving the output to a new file so the original is not overwritten; useful for debugging

		String markdownfileoutputpath = args[1];
		File markdownfileoutput = new File(markdownfileoutputpath);
		FileUtils.writeStringToFile(markdownfileoutput, output);

		 */
	}

	private String _docDir;
	private String _productType;
}
