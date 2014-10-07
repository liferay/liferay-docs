package com.liferay.documentation.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class CheckDocPropsTask extends Task {

	@Override
	public void execute() throws BuildException {
		File basedirFile = new File(_basedir);

		File[] basedirFiles = basedirFile.listFiles();

		// Build a list of files in the base directory

		List<File> dirs = new ArrayList<File>();
		for (File file : basedirFiles) {
			if (file.isDirectory()) {
				dirs.add(file);
			}
		}


		// Start a product / document

		Set <String> prodDocSet = new TreeSet<String>();

		// Keep track of invalid doc directories

		Map<String, String> docDirInvalidations = new TreeMap<String, String>();

		for (File dir : dirs) {
			File[] files = dir.listFiles();

			File langDir = null;
			for (File file : files) {
				if (file.isDirectory() && file.getName().equals(_lang)) {
					langDir = file;
					break;
				}
			}

			File docPropsFile = null;
			for (File file : files) {
				if (file.getName().equals(DOC_PROPERTIES)) {
					docPropsFile = file;
					break;
				}
			}

			if (docPropsFile == null) {
				if (langDir == null) {
					continue;
				}
				else {
					docDirInvalidations.put(dir.getName(), " missing file " +
							DOC_PROPERTIES);
					continue;
				}
			}

			// Load properties file

			Properties props = new Properties();
			try {
				props.load(new FileReader(docPropsFile));
			}
			catch (IOException io) {
				throw new BuildException(io);
			}

			// Check for missing properties

			List<String> missingProps = new ArrayList<String>();
			checkProperty(missingProps, props, DOC_NAME);
			checkProperty(missingProps, props, PRODUCT_ABBREV);
			checkProperty(missingProps, props, PRODUCT_NAME);
			checkProperty(missingProps, props, PRODUCT_VERSION);

			String prod = props.getProperty(PRODUCT_NAME);
			String doc = props.getProperty(DOC_NAME);
			if (prod != null && doc != null) {
				String prodDoc = prod + " " + doc;
				if (prodDocSet.contains(prodDoc)) {
					StringBuffer sb = new StringBuffer();
					sb.append("Prod and doc property combo ");
					sb.append(prodDoc);
					sb.append(" for ");
					sb.append(dir.getName());
					sb.append("/");
					sb.append(DOC_PROPERTIES);
					sb.append(" conflicts with that of another doc dir's ");
					sb.append(DOC_PROPERTIES);
					sb.append(". Resolve by deconflicting the doc.name property of the offending doc dirs.");
					throw new BuildException(sb.toString());
				}
				else {
					prodDocSet.add(prodDoc);
				}
			}

			// Compile message of missing properties for the current doc directory

			if (!missingProps.isEmpty()) {
				StringBuffer errorMsg = new StringBuffer();
				for (String prop : missingProps) {
					errorMsg.append(prop);
					errorMsg.append(" ");
				}

				docDirInvalidations.put(dir.getName(), errorMsg.toString());
			}
		}

		if (!docDirInvalidations.isEmpty()) {

			// Assemble error message

			StringBuffer invalidations = new StringBuffer();
			invalidations.append("Invalid doc directories: \n");
			for (Entry<String, String> entry : docDirInvalidations.entrySet()) {
				invalidations.append("\t");
				invalidations.append(entry.getKey());
				invalidations.append("/doc.properties missing: ");
				invalidations.append(entry.getValue());
				invalidations.append("\n");
			}

			throw new BuildException(invalidations.toString());
		}
	}

	private void checkProperty(List<String> missingProps,
			Properties props, String propName) {
		if (props.getProperty(propName) == null) {
			missingProps.add(propName);
		}
	}

	public void setLang(String lang) {
		_lang = lang;
	}

	public void setBasedir(String basedir) {
		_basedir = basedir;
	}

	private static final String DOC_PROPERTIES = "doc.properties";

	private static final String DOC_NAME = "doc.name";
	private static final String PRODUCT_ABBREV = "product.abbrev";
	private static final String PRODUCT_NAME = "product.name";
	private static final String PRODUCT_VERSION = "product.version";

	private String _basedir;
	private String _lang;
}
