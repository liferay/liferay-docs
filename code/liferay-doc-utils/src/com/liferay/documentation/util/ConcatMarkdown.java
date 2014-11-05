package com.liferay.documentation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ConcatMarkdown extends Task {
	
	public void execute() throws BuildException {
		
		File docDir = new File("../" + _docdir + "/articles");
		
		String book = ""; 
		
		try {
			
			List<File>files = collectFiles(docDir);
			
			for (File file : files) {
				
				if (!file.isDirectory()) {
					book = book + FileUtils.readFileToString(file);
					book = book + "\n";
				}
				
			}
			
			File fullBook = new File("book.markdown");
			
			FileUtils.writeStringToFile(fullBook, book);
			
		
		} catch (IOException e) {
		
			System.out.println("Error: " + e.getMessage());
		}
		
		
	}
	
	public List collectFiles(File dir) throws FileNotFoundException, IOException {
		validateDirectory(dir);
		List<File> result = getFileListingNoSort(dir);
		Collections.sort(result);

		ArrayList<File> results = new ArrayList();
		for (File file : result) {
			String fileName = file.getCanonicalPath();
			if (!fileName.contains(".svn")) {
				results.add(file);
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
