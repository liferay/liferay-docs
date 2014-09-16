package com.liferay.documentation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class DistDiffTask extends Task {

	@Override
	public void execute() throws BuildException {

		File dir = new File("../" + _docdir);
		if (!dir.exists()) {
			throw new BuildException("docdir " + dir.getAbsolutePath() +
					" could not be found");
		}

		String absDir = dir.getAbsolutePath();
		List<String> diffs = null;
		File modifiedList = new File(absDir + "/git-modified-list.txt");

		try {
			diffs = FileUtils.readLines(modifiedList, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String txtPath = _purposedir + "/" + _docdir + "/";	
		int i = txtPath.length();
		List<String> files = new ArrayList<String>();
		Iterator<String> it = diffs.iterator();

		while (it.hasNext()) {
			String s = it.next();

			if (!s.contains("DiffEntry") || s.contains("DiffEntry[DELETE")) {
				it.remove();
			}
		}

		// Grab readable directory paths and add them to list of strings
		for (String diff : diffs) {
			int x = diff.indexOf(txtPath);
			int y = x + i;
			int z = diff.indexOf("]", x);
			String file = diff.substring(y, z);

			files.add(file);
		}

		List<String> images = new ArrayList<String>();
		
		for (String file : files) {
			if (file.endsWith(".png")) {
				images.add(file);
			}
		}

		Set<File> chFiles = new HashSet<File>();
		Set<File> filesWithImageFinal = new HashSet<File>();
		List<String> filesWithImageString = new ArrayList<String>();
		List<String> filesWithImagePath = new ArrayList<String>();

		findMarkdownFiles(dir, chFiles);

		for (String img : images) {

			Set<File> filesWithImage = new HashSet<File>();

			scanMarkdownForImage(img, chFiles, filesWithImage);

			// Add the set of MD files that contain the image to a master set
			for (File file : filesWithImage) {
				filesWithImageFinal.add(file);
				System.out.println("New image " + img + " found in file " + file.getName());			
			}
		}

		// Convert the set of MD files to a list of strings
		for (File file : filesWithImageFinal) {
			filesWithImageString.add(file.toString());
		}

		// Convert the list of file strings (the modified images' MD files) to
		//readable directory paths
		for (String file : filesWithImageString) {
			int x = file.indexOf(_docdir, file.indexOf(_docdir) + 1);
			int y = x + _docdir.length() + 1;
			String filePath = file.substring(y, file.length());
			filesWithImagePath.add(filePath);
		}

		if (files.isEmpty()) {
			System.out.println("No zip file was produced because there are no available diffs");
		}
		else {
			try {
				System.out.println("Creating ../dist/diffs.zip file");
				FileOutputStream fileOutputStream = new FileOutputStream("dist/diffs.zip");
				ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

				for (String modFile : files) {
					addToZipFile(modFile, zipOutputStream);
				}
				for (String imgMarkdown : filesWithImagePath) {
					addToZipFile(imgMarkdown, zipOutputStream);
				}

				zipOutputStream.close();
				fileOutputStream.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	public void setPurposedir(String purposedir) {
		_purposedir = purposedir;
	}

	private static void addToZipFile(String modFile, ZipOutputStream zipOutputStream)
			throws FileNotFoundException, IOException {

		System.out.println("Adding " + modFile + " to zip file");

		File file = new File(modFile);
		FileInputStream fileInputStream = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(modFile);
		zipOutputStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bytes)) >= 0) {
			zipOutputStream.write(bytes, 0, len);
		}

		zipOutputStream.closeEntry();
		fileInputStream.close();
	}

	private static void findMarkdownFiles(File dir, Set<File> chFiles) {

		File articleDir = new File(dir.getAbsolutePath() + "/articles");
		File[] articles = articleDir.listFiles();

		for (File article : articles) {

			if (article.getName().contains(".")) {
				continue;
			}

			File[] allFiles = article.listFiles();

			for (File file : allFiles) {

				if (!file.toString().endsWith("markdown") && !file.toString().endsWith("md")) {
					continue;
				}

				chFiles.add(file);
			}
		}
	}
	
	private static void scanMarkdownForImage(String img, Set<File> chFiles, Set<File> filesWithImage) {
		for (File file : chFiles) {

			Scanner scanner = null;
			try {
				scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					String lineFromFile = scanner.nextLine();

					if (lineFromFile.contains(img)) { 
						filesWithImage.add(file);
					}
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private String _docdir;
	private String _purposedir;
}