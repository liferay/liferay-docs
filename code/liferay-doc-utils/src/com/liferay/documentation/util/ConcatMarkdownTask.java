package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ConcatMarkdownTask extends Task {
	
	public void execute() throws BuildException {
		
		File docDir = new File("../" + _docdir + "/articles");
		
		String book = ""; 
		int chapter = 0;
		int count=1;
		
		try {
			
			List<File>files = collectFiles(docDir);
			
			for (File file : files) {
				
				if (file.isDirectory()) {
					
					// save the chapter number
					chapter = Integer.parseInt(file.getName().substring(0,2));
					System.out.println("Chapter " + chapter);
					
				} else {
					LineNumberReader in =
							new LineNumberReader(new FileReader(file));
					
					String line;
					
							;
					while ((line = in.readLine()) != null) {

						if (line.startsWith("#")) {
							
							if (!file.getName().startsWith("00")) {
								
								line = "#" + line;
							}
								
						}
						
						if (line.contains("+$")) {
							
							line = "+sidebar";
						}
						
						if (line.contains("$$")) {
							
							line = "-sidebar";
						}
						
						if (line.startsWith("![Figure")) {
							
							line = numberImage(line, chapter, count);
							count = count + 1;
						}
						
						book = book + line + "\n";
						
					}
					
					in.close();
					count = 1;
					
					//book = book + FileUtils.readFileToString(file);
					book = book + "\n";
				}
				
			}
			
			File fullBook = new File("book.markdown");
			
			FileUtils.writeStringToFile(fullBook, book);
			
			stripHeaderIds(fullBook.getAbsolutePath());
			
		
		} catch (IOException e) {
		
			System.out.println("Error: " + e.getMessage());
		}
		
		
	}
	
	private String numberImage (String line, int chapter, int imageNum) {
		
		//find the colon
		//int colon = line.indexOf(":");
		//find the e
		int e = line.indexOf("e");
		//find the period
		int period = line.indexOf(".");
		
		String prefix = line.substring(0, e+1);
		System.out.println(prefix);
		String suffix = line.substring(period, line.length());
		System.out.println(suffix);
		line = prefix + " " + chapter + suffix;
	
		return line;
	}
	
	public List<File> collectFiles(File dir) throws FileNotFoundException, IOException {
		validateDirectory(dir);
		List<File> result = getFileListingNoSort(dir);
		Collections.sort(result);

		ArrayList<File> results = new ArrayList<File>();
		for (File file : result) {
			String fileName = file.getCanonicalPath();
			if (!fileName.contains(".svn")) {
				results.add(file);
				System.out.println(file.getName());
			}
		}
		return results;

	}
	
	private List<File> getFileListingNoSort(
			File aStartingDir) throws FileNotFoundException {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for (File file : filesDirs) {
			result.add(file); //always add, even if directory
			if (!file.isFile()) {
				//must be a directory
				//recursive call!
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	private void stripHeaderIds(String markdownFilePath) throws IOException {
		File markdownFile = new File(markdownFilePath);
		String source = FileUtils.readFileToString(markdownFile);

		String find = "\\[\\]\\(id=[^\\s]+?\\)";
		System.out.println("find: " + find);
		String replace = "";

		Pattern pattern = Pattern.compile(find);
		Matcher matcher = pattern.matcher(source);
		String output = matcher.replaceAll(replace);

		FileUtils.writeStringToFile(markdownFile, output);
	}

	private void validateDirectory(
			File aDirectory) throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: " + aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: " + aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: " + aDirectory);
		}
	}
	
	public void setDocdir(String docdir) {
		_docdir = docdir;
	}
	
	private String _docdir; 

}
