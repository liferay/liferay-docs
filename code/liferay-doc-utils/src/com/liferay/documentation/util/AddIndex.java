package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class AddIndex {

public static void addIndex(String texFilePath, String lexiconFilePath, String indexedTexFilePath) {
		
		File texFile = new File(texFilePath);
		String texString = null;
		try {
			texString = FileUtils.readFileToString(texFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File lexiconFile = new File(lexiconFilePath);
		String lexiconString = null;
		try {
			lexiconString = FileUtils.readFileToString(lexiconFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder indexDirectivesBuilder = new StringBuilder(texString);
		indexDirectivesBuilder.insert(texString.indexOf("\n\n"), "\n\n\\usepackage{makeidx}\n\\makeindex");
		texString = indexDirectivesBuilder.toString();
		
		indexDirectivesBuilder = new StringBuilder(texString);
		indexDirectivesBuilder.insert(texString.lastIndexOf("\\end{document}"), "\\printindex\n\n");
		texString = indexDirectivesBuilder.toString();
		
		String[] texParagraphs = texString.split("\n\n");
		int paragraphNum = 0;
		for (int i = 0; i < texParagraphs.length; i++) {
			if (texParagraphs[i].startsWith("\\chapter{What is Liferay?")) {
				paragraphNum = i;
				break;
			}
		}
		StringBuilder indexBuilder = new StringBuilder();
		
		String[] lexiconArray = lexiconString.split("\n");
		
		for (int i = 0; i < lexiconArray.length; i++) {
			for (int j = paragraphNum; j < texParagraphs.length; j++) {
				if (!(texParagraphs[j].startsWith("\\"))) {
					if (texParagraphs[j].contains(lexiconArray[i])) {
						//System.out.println("Before: texParagraphs" + "[" + j + "]: " + texParagraphs[j]);
						texParagraphs[j] = texParagraphs[j] + "\n\\index{" + lexiconArray[i] + "}";
						//System.out.println("After: texParagraphs" + "[" + j + "]: " + texParagraphs[j] + "\n");
					}
				}
			}
		}
		
		for (int i = 0; i < texParagraphs.length; i++) {
			indexBuilder.append(texParagraphs[i] + "\n\n");
		}
		
		texString = indexBuilder.toString();
		
		File indexedTexFile = new File(indexedTexFilePath);
		try {
			BufferedWriter indexedTexFileWriter = new BufferedWriter(new FileWriter(indexedTexFile));
			System.out.println("Writing " + indexedTexFile.getCanonicalPath());
			indexedTexFileWriter.write(texString);
			if (indexedTexFileWriter != null) {
				indexedTexFileWriter.flush();
			}
			indexedTexFileWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
