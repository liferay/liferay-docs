
package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class AddIndex {

	public static void addIndex(
		String texFilePath, String lexiconFilePath, String indexedTexFilePath) {

		File texFile = new File(texFilePath);
		File lexiconFile = new File(lexiconFilePath);
		String texString = fileToString(texFile);
		String lexiconString = fileToString(lexiconFile);

		texString = insertIndexDirectives(texString);

		ArrayList<String> docParagraphs =
			new ArrayList<String>(Arrays.asList(texString.split("\n\n")));
		ArrayList<String> lexicon = buildLexiconEntries(lexiconString);

		int paragraphNum = 0;
		int i = 0;
		for (String paragraph : docParagraphs) {
			if (paragraph.startsWith("\\chapter{What is Liferay?")) {
				paragraphNum = i;
				break;
			}
			i++;
		}

		for (String lexEntry : lexicon) {
			int counter = 0;
			for (String paragraph : docParagraphs) {
				if (counter < paragraphNum) {
					// do nothing
				}
				else {
					if (!(paragraph.startsWith("\\"))) {
						if (paragraph.contains(lexEntry)) {

							paragraph =
								paragraph + "\n\\index{" + lexEntry.trim() +
									"}";
							docParagraphs.set(counter, paragraph);

						}
					}
				}
				counter++;
			}
		}

		writeIndexedDocument(docParagraphs, indexedTexFilePath);

	}

	private static void writeIndexedDocument(
		ArrayList<String> docParagraphs, String indexedTexFilePath) {

		StringBuilder indexBuilder = new StringBuilder();
		for (String paragraph : docParagraphs) {
			indexBuilder.append(paragraph + "\n\n");
		}

		String texString = indexBuilder.toString();

		File indexedTexFile = new File(indexedTexFilePath);
		try {
			BufferedWriter indexedTexFileWriter =
				new BufferedWriter(new FileWriter(indexedTexFile));
			System.out.print("Writing " + indexedTexFile.getCanonicalPath() +
				"...");
			indexedTexFileWriter.write(texString);
			if (indexedTexFileWriter != null) {
				indexedTexFileWriter.flush();
			}
			indexedTexFileWriter.close();
			System.out.print("done!");
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	private static String fileToString(File file) {

		String string = null;
		try {
			string = FileUtils.readFileToString(file);
		}
		catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return string;
	}

	/**
	 * Return an ArrayList of Lexicon entries. This method filters out any
	 * comments (lines that begin with a # symbol) or any blank lines from the
	 * original file. Additionally, to prevent finding entries that are included
	 * inside other words (such as "tag" inside "advantage"), we add a space to
	 * the beginning of each entry.
	 * 
	 * @param lexiconString
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> buildLexiconEntries(String lexiconString) {

		String[] lexiconEntries = lexiconString.split("\n");
		ArrayList<String> lexicon = new ArrayList<String>();
		// Add a space to the beginning of each
		// entry to prevent entries from
		// being found inside large words, and remove
		// comments and blank lines
		for (String s : lexiconEntries) {
			if ((s.startsWith("#")) || (s.length() == 0)) {
				// do nothing
			}
			else {
				s = " " + s;
				lexicon.add(s);
			}
		}
		return lexicon;
	}

	private static String insertIndexDirectives(String texString) {

		StringBuilder indexDirectivesBuilder = new StringBuilder(texString);
		indexDirectivesBuilder.insert(
			texString.indexOf("\n\n"), "\n\n\\usepackage{makeidx}\n\\makeindex");
		texString = indexDirectivesBuilder.toString();

		indexDirectivesBuilder = new StringBuilder(texString);
		indexDirectivesBuilder.insert(
			texString.lastIndexOf("\\end{document}"), "\\printindex\n\n");
		texString = indexDirectivesBuilder.toString();
		return texString;
	}
}

