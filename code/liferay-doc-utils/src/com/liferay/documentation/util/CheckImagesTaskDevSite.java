package com.liferay.documentation.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckImagesTaskDevSite extends Task {

	/**
	 * Checks for missing images, extra images, or faulty file paths in the
	 * document directory.
	 * 
	 * Any of these problems results in a build failure.
	 */
	@Override
	public void execute() throws BuildException {
		System.out.println("Start checking image sources ...");

		File docDir = new File("../" + _docdir);
		if (!docDir.exists()) {
			throw new BuildException("docdir " + docDir.getAbsolutePath() +
				" could not be found");
		}
		if (!docDir.isDirectory()) {
			throw new BuildException("docdir " + docDir.getAbsolutePath() +
				" is not a directory");
		}

		File[] docDirFiles = docDir.listFiles();
		if (docDirFiles.length == 0) {
			throw new BuildException("docdir " + docDir.getAbsolutePath() +
				" is empty");
		}
		
		// Get articles
		String productType = _productType;
		List<String> dirTypes = new ArrayList<String>();
		dirTypes.add("");

		if (productType.equals("dxp")) {
			dirTypes.add("-dxp");
		}
		if (productType.equals("dist")) {
			try {
				ReplaceImagesPath.replaceImagePaths(docDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (String dirType : dirTypes) {

			File articleDir = new File(docDir.getAbsolutePath() + "/articles" + dirType);
			File[] articleDirFiles = articleDir.listFiles();
			List<File> articles = new ArrayList<File>();

			if ((!articleDir.exists() || !articleDir.isDirectory())) {
				throw new BuildException("Missing articles directory " +
						articleDir.getAbsolutePath());
			}
		
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
		
			// Get a map of articles to lists of referenced images
			Map<File, List<String>> imagePathsMap = new HashMap<File, List<String>>();
		
			for (File article : articles) {
				List<String> imagePaths = getImagePaths(article);
			
				imagePathsMap.put(article, imagePaths);
			}
		
			// Get list of images
			File imgDir = new File(docDir.getAbsolutePath() + "/images" + dirType);
			if (!imgDir.exists()) {
				throw new BuildException("imgdir " + imgDir.getAbsolutePath() +
						" could not be found");
			}
			if (!docDir.isDirectory()) {
				throw new BuildException("imgdir " + imgDir.getAbsolutePath() +
						" is not a directory");
			}
		
			File[] imagesArray = imgDir.listFiles();
		
			List<File> images = Arrays.asList(imagesArray);
		
			String imageDestination = ".." + File.separator + _docdir + File.separator + "images";

			checkImages(images, imagePathsMap, _resolveImages, imageDestination);

			System.out.println("Finished checking image sources in articles" + dirType);
		}
	}

	public void setResolveImages(boolean resolveImages) {
		_resolveImages = resolveImages;
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}
	
	public void setProductType(String productType) {
		_productType = productType;
	}
	
	/**
	 * Compares the actual images in the document directory to the images
	 * reference by the Markdown files in the document directory.
	 * 
	 * Missing images, extra images, or faulty file paths result in a build
	 * failure.
	 * 
	 * @param images the actual images in the document directory
	 * @param imagePathsMap a map from each Markdown file in the document
	 *        directory to a list of the image references in the Markdown file
	 * @param resolveImages whether to attempt to copy images from the
	 *        document's <code>images</code> folder into the images destination
	 *        folder
	 * @param imageDestination the destination folder for images
	 */
	private static void checkImages(List<File> images, Map<File,
			List<String>> imagePathsMap, boolean resolveImages,
			String imageDestination) {
		List<String> imagePaths = new ArrayList<String>();
		
		Collection<List<String>> imagePathsLists = imagePathsMap.values();

		for (List<String> imagePathsList : imagePathsLists) {
			imagePaths.addAll(imagePathsList);
		}
		
		List<String> referencedImageNames = new ArrayList<String>();

		for (String imagePath : imagePaths) {
			imagePath = getFileName(imagePath);
			
			referencedImageNames.add(imagePath);
		}
		
		List<String> imageNames = new ArrayList<String>();
		
		for (File image : images) {
			String imageName = image.getName();

			if (imageName.startsWith(".")) {
				continue;
			}

			imageNames.add(imageName);
		}
		
		List<String> errors = new ArrayList<String>();
		List<String> warnings = new ArrayList<String>();

		Set<File> articles = imagePathsMap.keySet();

		// Report missing images
		Set<String> missingImages = new HashSet<String>();

		for (File article : articles) {
			List<String> imagePathsList = imagePathsMap.get(article);

			for (String imagePath : imagePathsList) {
				String imageFileName = getFileName(imagePath);

				if (!imageNames.contains(imageFileName)) {

					File source = new File("images" + File.separator + imageFileName);

					File dest = new File(imageDestination + File.separator + imageFileName);

					if (resolveImages && source.exists()) {

							try {
								System.out.println("Copying " + imageFileName + " from " + source + " to " + dest);
							    FileUtils.copyFile(source, dest);
							} catch (IOException e) {
							    e.printStackTrace();
							}
					}

					if (!dest.exists()) {
						StringBuilder sb = new StringBuilder();
						sb.append(article.getName());
						sb.append(": References missing image: ");
						sb.append(imageFileName);

						errors.add(sb.toString());

						missingImages.add(imagePath);
					}
				}
			}
		}
		
		// Report extra images
		for (String imageName : imageNames) {
			if (!referencedImageNames.contains(imageName)) {
				warnings.add("Extra image: " + imageName);
			}
		}
		
		// Report faulty image paths 
		for (File article : articles) {
			String parentPath = article.getParent();
			
			List<String> imagePathsList = imagePathsMap.get(article);
			
			for (String imagePath : imagePathsList) {
				if (missingImages.contains(imagePath)) {
					// Already reported as missing
					continue;
				}

				File image = new File(parentPath + "/" + imagePath);
				
				if (!image.exists() || image.isDirectory()) {
					StringBuilder sb = new StringBuilder();
					sb.append(article.getName());
					sb.append(": Faulty image path: ");
					sb.append(imagePath);

					errors.add(sb.toString());

					System.out.println("image: " + image.toString());
				}
			}
		}
		
		if (!errors.isEmpty()) {
			for (String error : errors) {
				System.err.println("ERROR - " + error);
			}
			
			throw new BuildException("Missing images, extra images, or faulty image paths");
		}
		
		if (!warnings.isEmpty()) {
			for (String warning : warnings) {
				System.err.println("WARNING - " + warning);
			}
			
		}
	}
	
	private static String getFileName(String path) {
		if (!path.contains("/")) {
			return path;
		}
		
		int index = path.lastIndexOf("/");
		
		String fileName = path.substring(index);

		fileName = fileName.replace("/", "");
		
		return fileName;
	}
	
	/**
	 * Returns a list of the relative paths of the images referenced in a
	 * Markdown file.
	 * 
	 * @param  article the Markdown file
	 * @return a list of the relative paths of the images referenced in a
	 *         Markdown file (may be empty)
	 */
	private static List<String> getImagePaths(File article) {
		List<String> imagePaths = new ArrayList<String>();
		
		if (article.isDirectory()) {
			return imagePaths;
		}
		
		if (!article.getName().endsWith(".markdown")) {
			return imagePaths;
		}
		
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(article.getCanonicalPath()), StandardCharsets.UTF_8);
		}
		catch (IOException ioe) {
			System.err.println(ioe.getLocalizedMessage());

			return imagePaths;
		}
		
		if (lines == null) {
			return imagePaths;
		}
		
		// Match lines containing expressions of the form ![...](...)
		String regex = ".*!\\[.*\\]\\(.*\\).*";

		for (String line : lines) {
			line = line.trim();
			
			if (line.matches(regex)) {
				int begin = line.lastIndexOf("(");
				line = line.substring(begin);

				int end = line.indexOf(")");
				line = line.substring(0, end);
				
				line = line.replace("(", "");
				line = line.replace(")", "");
				
				imagePaths.add(line);
			}

			// Check for <img> elements

			if (line.contains("<img") && !line.contains("<![CDATA[")) {
				int begin = line.indexOf("<img");
				int end = line.indexOf(">", begin);
				int src = line.indexOf("src", begin);
				int images = line.indexOf("images");

				if (src > 0 &&
					images > src) {

					if (end < 0 ||
						(end > 1 && images < end)) {

						int quote1 = line.indexOf("\"", src);
						int quote2 = line.indexOf("\"", quote1 +1);

						String srcImage = line.substring(quote1 + 1, quote2);

						System.out.println("<img> tag references: " + srcImage);

						imagePaths.add(srcImage);
					}
				}
			}
		}
		
		return imagePaths;
	}


	private String _docdir;
	private String _productType;
	private boolean _resolveImages;
}
