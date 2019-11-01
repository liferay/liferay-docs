package com.liferay.documentation.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

/**
 * Finds the changed files (added, deleted, modified, and renamed) that occurred
 * since the last publication date. This is done by comparing two Git commits
 * and finding what changed between them. These files are gathered in a Zip file
 * and formatted for publication to Liferay's Knowledge Base portlet.
 *
 * @author Cody Hoag
 */
public class CheckLatestCommitTask extends Task {

	@Override
	public void execute() throws BuildException {

		String docDir = _docDir;
		String docLocation = _docLocation;
		String distDir = _distDir;
		String zipName = _zipName;
		String dxpParam = _dxpParam;
		boolean dxp = Boolean.parseBoolean(dxpParam);

		File dir = new File("../" + docDir);
		String absDir = dir.getAbsolutePath();
		File commitFile = new File(absDir + "/last-publication-commit.txt");

		String docLanguage = docLocation.substring(0, docLocation.indexOf("/"));

		try {
		String headCommit = getHeadCommit();

		// Create new metadata file with current HEAD commit, if one doesn't exist.
		if (!commitFile.exists()) {
			System.out.println("No previous publication commit available. Creating ./last-publication-commit.txt file. "
					+ "Subsequent dists will generate Zip with only modified files.");

			generateLatestCommitFile(headCommit);
			System.exit(0);
		}

		// If a metadata file exists, copy the last published commit and find all
		// modified files since that commit's publication.

		String lastPublishedCommit = FileUtils.readFileToString(commitFile);

		if (!headCommit.equals(lastPublishedCommit)) {
			List<String> modifiedFiles = getModifiedFiles(lastPublishedCommit, docLanguage, docLocation, docDir, distDir, dxp);

			// build out Zip with these new modified file paths

			Set<String> modifiedImages = new HashSet<String>();
			Set<String> modifiedArticles = new HashSet<String>();

			// Separate modified/new MD files and images
			for (String file : modifiedFiles) {
				if (!file.contains("images") && !file.contains("articles")) {
					continue;
				}
				else if (file.endsWith(".png") || file.endsWith(".jpg") || file.endsWith(".gif")) {
					modifiedImages.add(file);
				}
				else if (file.endsWith(".markdown") || file.endsWith(".md")) {
					modifiedArticles.add(file);
				}
				else {
					continue;
				}
			}

			// Unzip dist Zip
			unzipFile(docDir, zipName);
			File unzippedDir = new File("../" + docDir + "/" + zipName);
			
			// Find all MD and image files in directory
			Set<File> allZipArticles = getMarkdownFiles(unzippedDir);
			Set<File> allZipImages = getImageFiles(unzippedDir);
			
			// Convert modified file paths to consistent paths pointing to dist zip files
			Set<String> modifiedZipArticles = mapModFilesToZipFiles(modifiedArticles, allZipArticles, "articles");
			Set<String> modifiedZipImages = mapModFilesToZipFiles(modifiedImages, allZipImages, "images");
			
			Set<String> articlesWithModifiedImages = getArticlesWithModifiedImages(allZipArticles, modifiedZipImages, docDir);

			modifiedZipArticles.addAll(articlesWithModifiedImages);
			
			// Find and add all modified/new MD files' intro file. A modified file must
			// be accompanied with hierarchy of parent intros to import correctly.
			Set<String> introFiles = getIntroFiles(modifiedZipArticles);
			modifiedZipArticles.addAll(introFiles);

			// Scan each MD file for remainder of images to include in ZIP file. When
			// re-importing a new MD file, all of its images must also be re-imported.
			Set<String> markdownImages = scanMarkdownForAllImages(modifiedZipArticles);

			modifiedZipImages.addAll(markdownImages);

			String metadataFile = zipPrePath + ".METADATA";

				try {
					System.out.println("Creating" + zipName + "-diffs-only.zip");
					FileOutputStream fileOutputStream = new FileOutputStream(zipName + "-diffs-only.zip");
					ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

					for (String markdown : modifiedZipArticles) {
						addToZipFile(markdown, zipOutputStream, distDir);
					}
					for (String image : modifiedZipImages) {
						addToZipFile(image, zipOutputStream, distDir);
					}

					addToZipFile(metadataFile, zipOutputStream, distDir);

					zipOutputStream.close();
					fileOutputStream.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

			generateLatestCommitFile(headCommit);
		}
		else {
			System.out.println("There are no new files to publish!");
			System.exit(0);
		}
		} catch (IOException e) {
			throw new BuildException(e.getLocalizedMessage());
		}
	}

	public void setDocDir(String docDir) {
		_docDir = docDir;
	}

	public void setDocLocation(String docLocation) {
		_docLocation = docLocation;
	}

	public void setDxpParam(String dxpParam) {
		_dxpParam = dxpParam;
	}

	public void setDistDir(String distDir) {
		_distDir = distDir;
	}

	public void setZipName(String zipName) {
		_zipName = zipName;
	}

	/**
	 * Adds the modified file to a distributable Zip file.
	 *
	 * @param  modFile the modified file
	 * @param  zipOutputStream the output stream used to add files to the Zip
	 * @param  distDir the folder where the Zip is generated (e.g., {@code dist})
	 * @throws FileNotFoundException if the modified file was not found
	 * @throws IOException if an IO exception occurred
	 */
	private static void addToZipFile(String modFile, ZipOutputStream zipOutputStream, String distDir)
			throws FileNotFoundException, IOException {

		String uniformDistDir = distDir.replaceAll("/", Matcher.quoteReplacement(File.separator));

		int folderIndex = modFile.indexOf(uniformDistDir) + uniformDistDir.length() + 1;
		int begIndex = modFile.indexOf(File.separator, folderIndex) + 1;
		String destModFile = modFile.substring(begIndex, modFile.length());

		System.out.println("Adding " + destModFile + " to zip file");

		File file = new File(modFile);
		FileInputStream fileInputStream = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(destModFile);
		zipOutputStream.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int len;
		while ((len = fileInputStream.read(bytes)) >= 0) {
			zipOutputStream.write(bytes, 0, len);
		}

		zipOutputStream.closeEntry();
		fileInputStream.close();
	}

	/**
	 * Returns the parent intro articles for the given Markdown files.
	 *
	 * @param markdownFiles the Markdown files to find intros for
	 * @return the parent intro articles
	 */
	private static Set<String> getIntroFiles(Set<String> markdownFiles) {

	Set<String> fileList = new HashSet<String>();
	
	for (String markdownFile : markdownFiles) {

		
		File article = new File(markdownFile);
		File parentDir = article.getParentFile();

		boolean containsIntro = true;

		while (containsIntro) {
			
			File[] parentFiles = parentDir.listFiles();

			containsIntro = false;

			for (File file : parentFiles) {
				if (file.getName().endsWith("introduction.markdown") ||
						file.getName().endsWith("intro.markdown")) {

					fileList.add(file.toString());
					containsIntro = true;
				}
			}

			parentDir = parentDir.getParentFile();
		}
	}
	
		return fileList;
	}

	/**
	 * Returns all the Markdown files in the given folder.
	 *
	 * @param  dir the folder in which to find Markdown files
	 * @return the Markdown files
	 */
	private static Set<File> getMarkdownFiles(File dir) {

		Set<File> chFiles = new HashSet<File>();
		File articleDir = new File(dir.getAbsolutePath() + "/articles");
		File[] articles = (File[])ArrayUtils.addAll(articleDir.listFiles());

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
		
		return chFiles;
	}

	/**
	 * Writes the provided head Git commit number to a {@code .txt} file stored
	 * in the {@code docDir}. This is referenced every time the associated Ant
	 * target is executed, serving as the last published Git commit.
	 *
	 * @param headCommit   the head commit on the current Git branch
	 * @throws IOException if an IO exception occurred
	 */
	private static void generateLatestCommitFile(String headCommit)
			throws IOException {

		PrintWriter writer = new PrintWriter("last-publication-commit.txt", "UTF-8");
		writer.print(headCommit);
		writer.close();
	}

	/**
	 * Returns the Markdown articles containing the given modified images.
	 *
	 * @param  zipMarkdownFiles the articles to scan for modified images
	 * @param  modifiedImages the modified images to search for
	 * @param  docDir the file's parent folder (e.g., {@code tutorials})
	 * @return the Markdown articles containing the given modified images
	 */
	private static Set<String> getArticlesWithModifiedImages(Set<File> zipMarkdownFiles, Set<String> modifiedImages, String docDir) {

		Set<String> zipMarkdownFilesWithImageFinal = new HashSet<String>();

		for (String imgPath : modifiedImages) {

			// Scan directory's MD files for modified/new image
			Set<File> zipMarkdownFilesWithImage = scanMarkdownForImage(imgPath, zipMarkdownFiles);

			// Add the set of MD files that contain the image to a master set
			for (File file : zipMarkdownFilesWithImage) {
				zipMarkdownFilesWithImageFinal.add(file.toString());
			}
		}

		return zipMarkdownFilesWithImageFinal;
		
	}

	/**
	 * Returns the most current Git commit associated with the current
	 * Git repository.
	 *
	 * @return the most current Git commit
	 * @throws IOException if an IO exception occurred
	 */
	private static String getHeadCommit() throws IOException {

		Repository repo = openGitRepository();
		Ref head = repo.getRef("HEAD");
		String headCommit = head.getObjectId().getName();
		repo.close();

        return headCommit;
	}

	/**
	 * Returns all the images residing in the given folder.
	 *
	 * @param  dir the folder to grab images from
	 * @return the images residing in the given folder
	 */
	private static Set<File> getImageFiles(File dir) {

		Set<File> imageFiles = new HashSet<File>();
		File imageDir = new File(dir.getAbsolutePath() + "/images");
		File[] images = (File[])ArrayUtils.addAll(imageDir.listFiles());
		
		for (File img : images) {
			if (img.toString().endsWith(".png") || img.toString().endsWith(".jpg") ||
					img.toString().endsWith(".gif")) {
				imageFiles.add(img);
			}
		}

		return imageFiles;
	}

	/**
	 * Returns the files that were modified since the last published Git commit.
	 *
	 * @param  commit the last published Git commit
	 * @param  docLocation the folder path following {@code liferay-docs} (e.g,
	 *         {@code develop/tutorials}
	 * @param  docDir the parent folder of where the Ant task was executed (e.g.,
	 *         {@code tutorials})
	 * @param  distDir the folder where the Zip is generated (e.g., {@code dist})
	 * @param  dxp whether to include DXP-only modified files
	 * @return the files that were modified since the last published Git commit
	 * @throws IOException if an IO exception occurred
	 */
	private static List<String> getModifiedFiles(String commit, String docLanguage, String docLocation, String docDir, String distDir, boolean dxp)
			throws IOException {

		Repository repo = openGitRepository();
		Git git = new Git(repo);
		ObjectReader reader = git.getRepository().newObjectReader();

		CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
		ObjectId oldTree = git.getRepository().resolve(commit + "^{tree}");
		oldTreeIter.reset(reader, oldTree);

		CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
		ObjectId newTree = git.getRepository().resolve("HEAD^{tree}");
		newTreeIter.reset(reader, newTree);

		DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
		diffFormatter.setRepository(git.getRepository());
		diffFormatter.setDetectRenames(true);
		List<DiffEntry> entries = diffFormatter.scan(oldTreeIter, newTreeIter);

		List<String> modifiedFiles = new ArrayList<String> ();
		List<String> deletedFiles = new ArrayList<String> ();
		//HashMap<String, String> renamedFiles = new HashMap<String, String> ();

		// Be sure to convert single nested folders (e.g., user) so they're searchable
		if (docLocation.equals(docLanguage + "/./" + docDir)) {
			docLocation = docLanguage + "/" + docDir;
		}

		for (DiffEntry entry : entries) {

			String entryPath = entry.getNewPath();
			
			if (entryPath.startsWith(docLocation)) {

				if (!dxp && 
						(entryPath.contains("/articles-dxp/") ||
						entryPath.contains("/images-dxp/"))) {
					continue;
				}
				
				if (dxp && entryPath.contains("/articles-dxp/")) {
					entryPath = entryPath.replace("/articles-dxp/", "/articles/");
				}

				if (dxp && entryPath.contains("/images-dxp/")) {
					entryPath = entryPath.replace("/images-dxp/", "/images/");
				}
				
				//if (entry.getChangeType().toString().equals("RENAME")) {
				//	renamedFiles.put(entry.getOldPath(), entryPath);
				//	modifiedFiles.add(entryPath);
				//}
				//else {
				modifiedFiles.add(entryPath);
				//}
			}
			else if (entry.getOldPath().startsWith(docLocation) &&
					entry.getChangeType().toString().equals("DELETE")) {
				deletedFiles.add(entry.getOldPath());
			}
		}

		if (modifiedFiles.isEmpty()) {
			System.out.println("There are no new files to publish!");
			System.exit(0);
		}

		if (!deletedFiles.isEmpty()) {
			writeDeletedTextFile(deletedFiles, distDir);
		}

		repo.close();

		return modifiedFiles;
	}

	/**
	 * Converts the modified file paths as they're stored in the Github repo to
	 * the regular distributable Zip's file path. This ensures that the articles
	 * have been overwritten with their DXP counterparts and/or the tokens have
	 * been applied.
	 *
	 * @param  modifiedFiles the modified files to convert
	 * @param  zipFiles the Zip files, which are used solely to extract its
	 *         general path
	 * @param  fileType the file type (article or image)
	 * @return the converted file paths
	 */
	private static Set<String> mapModFilesToZipFiles(Set<String> modifiedFiles, Set<File> zipFiles, String fileType) {
		
		// Zip:
		// .\dist\lp-ce-7.1-discover-portal\articles\210-setting-up\08-custom-fields.markdown
		
		// modified
		// discover/portal/articles/210-setting-up/08-custom-fields.markdown
		
		File zipFile = (File) zipFiles.toArray()[0];
		String zipFileString = zipFile.toString();
		int endIndex = zipFileString.indexOf(fileType + File.separator);
		zipPrePath = zipFileString.substring(0, endIndex);
		
		Set<String> convertedFiles = new HashSet<String>();
		
		for (String modifiedFile : modifiedFiles) {
			int begIndex = modifiedFile.indexOf("/" + fileType) + 1;
			String partialPath = modifiedFile.substring(begIndex, modifiedFile.length());
			String uniformPath = partialPath.replaceAll("/", Matcher.quoteReplacement(File.separator));

			convertedFiles.add(zipPrePath + uniformPath);
		}

		return convertedFiles;
	}

	/**
	 * Opens the Git repository connection.
	 *
	 * @return the Git repository
	 * @throws IOException if an IO exception occurred
	 */
	private static Repository openGitRepository() throws IOException {

		FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
		Repository repo = repoBuilder.readEnvironment().findGitDir().build();

		return repo;
	}

	/**
	 * Returns the images displayed in the given Markdown articles. When an
	 * article is republished, its images must also be included.
	 *
	 * @param  modifiedArticles the modified Markdown articles
	 * @return the images displayed in the given Markdown articles
	 */
	private static Set<String> scanMarkdownForAllImages(Set<String> modifiedArticles) {
		
		Set<File> markdownImages = new HashSet<File>();
		Set<String> markdownImagesString = new HashSet<String>();
		
		String zipFile = (String) modifiedArticles.toArray()[0];

		int endIndex = zipFile.indexOf("articles" + File.separator);
		String zipPrePath = zipFile.substring(0, endIndex);

		for (String modifiedArticle : modifiedArticles) {

			Scanner scanner = null;
			File file = new File(modifiedArticle);
			
			try {
				scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					String lineFromFile = scanner.nextLine();

					// Match lines containing expressions of the form ![...](...)
					String regex = ".*!\\[.*\\]\\(.*\\).*";

					lineFromFile = lineFromFile.trim();

					if (lineFromFile.matches(regex)) {
						int begin = lineFromFile.indexOf("../images");

						if (begin < 0) {
							continue;
						}

						begin = begin + 3;
						int end = lineFromFile.indexOf(")", begin);

						String img = lineFromFile.substring(begin, end);

						File markdownImage = new File(img);

						markdownImages.add(markdownImage);
					}
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		for (File markdownImage : markdownImages) {
			markdownImagesString.add(zipPrePath + markdownImage.toString());
		}

		return markdownImagesString;
	}

	/**
	 * Returns the Markdown articles that reference the given image.
	 *
	 * @param  imgPath the image's path to search for
	 * @param  files the files in which to search for the image
	 * @return the Markdown articles that reference the given image
	 */
	private static Set<File> scanMarkdownForImage(String imgPath, Set<File> files) {

		int imgStart = imgPath.lastIndexOf(File.separator) + 1;
		String img = imgPath.substring(imgStart, imgPath.length());

		Set<File> filesWithImage = new HashSet<File>();

		for (File file : files) {

			Scanner scanner = null;
			try {
				scanner = new Scanner(file);

				while (scanner.hasNextLine()) {
					String lineFromFile = scanner.nextLine();

					if (lineFromFile.contains(img)) { 
						filesWithImage.add(file);
						System.out.println("New image " + img + " found in file " + file.getName());
					}
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return filesWithImage;
	}

	/**
	 * Unzips the Zip file. This is used to unzip the generated distributable
	 * Zip to extract already parsed (DXP overrides and token generations
	 * applied) Markdown articles and images.
	 *
	 * @param  docDir the parent folder of where the Ant task was executed (e.g.,
	 *         {@code tutorials})
	 * @param  zipName the Zip file to unzip
	 * @throws IOException if an IO exception occurred
	 */
	private static void unzipFile(String docDir, String zipName)
			throws IOException {

		byte[] buffer = new byte[1024];
		int bytesRead = 0;

		File zipFile = new File("../" + docDir + "/" + zipName + ".zip");
		File destinationDir = new File("../" + docDir + "/" + zipName);
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<? extends ZipEntry> zipEntries = zip.entries();

		while (zipEntries.hasMoreElements()) {
			ZipEntry entry = zipEntries.nextElement();

			if (entry.isDirectory()) {
				File newDir = new File(destinationDir, entry.getName());
				newDir.mkdirs();
			} else {
				BufferedInputStream inputStream = new BufferedInputStream(zip.getInputStream(entry));
				File outputFile = new File(destinationDir, entry.getName());
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();
			}
		}
		zip.close();
		//zipFile.delete();
	}

	/**
	 * Writes deleted files to a {@code .txt} file. This is useful to notify the
	 * publisher of the files that must be manually deleted from the Knowledge
	 * Base portlet, since the import of a Zip only adds files or modifies
	 * existing files, but cannot delete them.
	 *
	 * @param  deletedFiles the files that were deleted in the Git repo since
	 *         the last publication
	 * @param  distDir the folder where the Zip is generated (e.g., {@code dist})
	 * @throws IOException if an IO exception occurred
	 */
	private static void writeDeletedTextFile(List<String> deletedFiles, String distDir)
			throws IOException {

		PrintWriter writer = new PrintWriter(distDir + "/delete-files.txt", "UTF-8");
		writer.println("DELETED:\n");

		for (String file : deletedFiles) {
			writer.println(file);
		}

		//writer.println("");
		//writer.println("\nRENAMED:\n");

		//for (Map.Entry<String, String> entry : renamedFiles.entrySet()) {
		//	writer.println("Old article to delete: " + entry.getKey() + " (renamed/moved to: " + entry.getValue() + ")");
		//}

		writer.close();
	}

	private static String zipPrePath;

	private String _distDir;
	private String _docDir;
	private String _docLocation;
	private String _dxpParam;
	private String _zipName;
}