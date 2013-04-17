
package com.liferay.portal.tools.propertiesconverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Generates an HTML index file listing the properties HTML files found in a
 * directory.
 *
 * @author Jesse Rao
 * @author James Hinkey
 */
public class PropertiesBundler {

	/**
	 * Generates an HTML index file listing the properties HTML files found in
	 * the directory.
	 *
	 * @param dir the directory containing the properties HTML files.
	 * @param lpVersion the version of Liferay Portal.
	 */
	public void bundleProperties(String dir, String lpVersion) {

		// Create a data model for Freemarker

		Map root = new HashMap();

		root.put("lpVersion", lpVersion);

		List<PropertiesHtmlFile> htmlFiles =
			new ArrayList<PropertiesHtmlFile>();

		File[] files = new File(dir).listFiles();

		for (File file : files) {
			if (file.getName().endsWith(".properties.html")) {
				htmlFiles.add(new PropertiesHtmlFile(file.getName()));
			}
		}

		root.put("htmlFiles", htmlFiles);

		// Get the Freemarker template and merge it with the data model

		try {
			Configuration configuration = new Configuration();

			File templateDir =
				new File(
					"code/properties-converter/src/com/liferay/portal/tools/"
						+ "propertiesconverter/dependencies");

			try {
				configuration.setDirectoryForTemplateLoading(templateDir);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

			configuration.setObjectWrapper(new DefaultObjectWrapper());

			Template template = configuration.getTemplate("index.ftl");

			String indexFilePath = dir + "/index.html";

			System.out.println("Writing index HTML file " + indexFilePath);

			File indexFile = new File(indexFilePath);

			Writer writer = new FileWriter(indexFile);

			try {
				template.process(root, writer);
			}
			catch (TemplateException te) {
				System.out.println(te.getLocalizedMessage());
			}

			writer.flush();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	/**
	 * Replaces the last occurrence of the string <code>oldSub</code> in the
	 * string <code>s</code> with the string <code>newSub</code>. (Copied from
	 * portal's StringUtil.java)
	 * 
	 * @param s
	 *            the original string
	 * @param oldSub
	 *            the string whose last occurrence in the original string is to
	 *            be searched for and replaced
	 * @param newSub
	 *            the string with which to replace the last occurrence of the
	 *            <code>oldSub</code> string
	 * @return a string representing the original string except with the last
	 *         occurrence of the string <code>oldSub</code> replaced with the
	 *         string <code>newSub</code>
	 */
	public static String replaceLast(String s, String oldSub, String newSub) {
		
		if ((s == null) || (oldSub == null) || (newSub == null)) {
			return null;
		}

		if (oldSub.equals(newSub)) {
			return s;
		}

		int y = s.lastIndexOf(oldSub);

		if (y >= 0) {
			return s.substring(0, y).concat(newSub).concat(
				s.substring(y + oldSub.length()));
		}
		else {
			return s;
		}
	}

	/**
	 * Provides the name of the properties file and it's generated HTML file.
	 *
	 * @author James Hinkey
	 */
	public class PropertiesHtmlFile {

		/**
		 * Constructs a properties file.
		 *
		 * <p>
		 * For example, pass in value "portal.properties.html" for the
		 * portal.properties file.
		 * </p>
		 *
		 * @param htmlFileName the name of the HTML file representing a
		 * properties file.
		 */
		public PropertiesHtmlFile(String fileName) {
			_fileName = fileName;

			_propertiesFileName = replaceLast(_fileName, ".html", "");
		}

		/**
		 * Returns the name of the HTML file.
		 *
		 * @return the name of the HTML file
		 */
		public String getFileName() {
			return _fileName;
		}

		/**
		 * Returns the name the properties file.
		 *
		 * @return the name of the properties file
		 */
		public String getPropertiesFileName() {
	
			return _propertiesFileName;
		}

		private final String _propertiesFileName;
		private final String _fileName;

	}

}
