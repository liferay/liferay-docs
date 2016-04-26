package com.liferay.documentation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
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
import org.apache.commons.lang3.ArrayUtils;
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

		String txtPath;
		
		if (System.getProperty("user.dir").contains(_purposedir + "\\learning-paths") ||
				System.getProperty("user.dir").contains(_purposedir + "/learning-paths")) {
			txtPath = _purposedir + "/learning-paths/" + _docdir + "/";
		}

		else {
			txtPath = _purposedir + "/" + _docdir + "/";
		}

		Set<String> files = new HashSet<String>();
		Iterator<String> it = diffs.iterator();

		// Remove lines in the .txt file that are not related to diff entries,
		// or are diff entry deletions
		while (it.hasNext()) {
			String s = it.next();

			if (!s.contains("DiffEntry") || s.contains("DiffEntry[DELETE")) {
				it.remove();
			}
		}

		// Grab readable directory paths and add them to list of strings
		for (String diff : diffs) {
			int x = diff.indexOf(txtPath);
			int y = x + txtPath.length();
			int z = diff.indexOf("]", x);
			String fileDir = diff.substring(y, z);

			files.add(fileDir);
		}

		Set<String> images = new HashSet<String>();
		Set<String> markdownArticles = new HashSet<String>();

		// Separate modified/new MD files and images
		for (String file : files) {
			if (file.endsWith(".png")) {
				images.add(file);
			}
			else if (file.endsWith(".markdown") || file.endsWith(".md")) {
				markdownArticles.add(file);
			}
			else {
				continue;
			}
		}

		Set<File> chFiles = new HashSet<File>();
		Set<File> filesWithImageFinal = new HashSet<File>();
		List<String> filesWithImageString = new ArrayList<String>();
		List<String> filesWithImagePath = new ArrayList<String>();

		// Find all MD files in directory
		findMarkdownFiles(dir, chFiles);

		for (String img : images) {

			// Scan directory's MD files for modified/new image
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

		// Convert the list of MD files (the modified images' MD files) to
		// readable directory paths
		for (String file : filesWithImageString) {
			int x = file.indexOf(_docdir, file.indexOf(_docdir) + 1);
			int y = x + _docdir.length() + 1;
			String filePath = file.substring(y, file.length());
			filesWithImagePath.add(filePath);
		}

		markdownArticles.addAll(filesWithImagePath);

		Set<String> markdownArticlesFinal = new HashSet<String>();

		// Make sure all dashes are the same, so MD sets are unique
		for (String article : markdownArticles) {
			article = article.replace("\\", "/");
			markdownArticlesFinal.add(article);
		}

		Set<File> markdownImages = new HashSet<File>();
		Set<File> markdownFiles = new HashSet<File>();
		Set<String> markdownImagesString = new HashSet<String>();

		// Convert list of MD String files to regular files so we can scan for images
		for (String file : markdownArticles) {
			File markdownFile = new File(file);
			markdownFiles.add(markdownFile);				
		}
		
		// Find and add all modified/new MD files' intro file
		findIntroFiles(markdownFiles, markdownArticlesFinal);

		// Scan each MD file for remainder of images to include in ZIP file. When
		// re-importing a new MD file, all of its images must also be re-imported.
		scanMarkdownForAllImages(markdownFiles, markdownImages);

		for (File markdownImage : markdownImages) {
			markdownImagesString.add(markdownImage.toString());
		}

		markdownImagesString.addAll(images);

		Set<String> markdownImagesFinal = new HashSet<String>();

		// Make sure all dashes are the same, so image sets are unique
		for (String image : markdownImagesString) {
			image = image.replace("\\", "/");
			markdownImagesFinal.add(image);
		}

		if (files.isEmpty()) {
			System.out.println("No zip file was produced because there are no available diffs");
		}
		else {
			try {
				System.out.println("Creating ../dist/diffs.zip file");
				FileOutputStream fileOutputStream = new FileOutputStream("dist/diffs.zip");
				ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

				for (String markdown : markdownArticlesFinal) {
					addToZipFile(markdown, zipOutputStream);
				}
				for (String image : markdownImagesFinal) {
					addToZipFile(image, zipOutputStream);
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
	
	private static void findIntroFiles(Set<File> markdownFiles, Set<String> markdownArticlesFinal) {
		
		Set<File> introFiles = new HashSet<File>();

		for (File file : markdownFiles) {
			if (!file.toString().contains("intro.markdown") ||
					!file.toString().contains("introduction.markdown")) {

				File parentDir = file.getParentFile();
				File[] dirIntroFiles = parentDir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.equals("intro.markdown") ||
								name.equals("introduction.markdown");
					}
				});

				for (File introFile : dirIntroFiles) {
					introFiles.add(introFile);
				}
			}
		}

		for (File intro : introFiles) {
			markdownArticlesFinal.add(intro.toString().replace("\\", "/"));
		}
	}

	private static void findMarkdownFiles(File dir, Set<File> chFiles) {

		File articleDir = new File(dir.getAbsolutePath() + "/articles");
		File articleDxpDir = new File(dir.getAbsolutePath() + "/articles-dxp");
		File[] articles = (File[])ArrayUtils.addAll(articleDir.listFiles(), articleDxpDir.listFiles());

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

	private static void scanMarkdownForAllImages(Set<File> modifiedMarkdownFiles, Set<File> markdownImages) {
		for (File file : modifiedMarkdownFiles) {

			Scanner scanner = null;
			try {
				scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					String lineFromFile = scanner.nextLine();

					if (lineFromFile.contains(".png")) { 
						int w = lineFromFile.indexOf(".png");
						int x = w + 4;
						int y = lineFromFile.indexOf("../../images");

						if (y < 0) {
							continue;
						}

						int z = y + 6;
						String img = lineFromFile.substring(z, x);

						File markdownImage = new File(img);

						markdownImages.add(markdownImage);
					}
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
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