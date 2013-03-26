
package com.liferay.portal.tools.propertiesconverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PropertiesBundler {

	/**
	 * @param args
	 */
	public static void bundleProperties() {

		// Create a data model for Freemarker

		Map root = new HashMap();

		ArrayList properties = new ArrayList<String>();

		String distDir = System.getProperty("user.dir") + "/dist";

		File distFolder = new File(distDir);

		File[] files = distFolder.listFiles();

		for (File file : files) {
			if (file.getName().endsWith(".properties.html")) {
				properties.add(file.getName());
			}
		}

		root.put("properties", properties);

		// Get the Freemarker template and merge it with the data model

		try {
			Configuration configuration = new Configuration();

			File file =
				new File(
					"code/properties-converter/src/com/liferay/portal/tools/"
						+ "propertiesconverter/dependencies");

			try {
				configuration.setDirectoryForTemplateLoading(file);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

			configuration.setObjectWrapper(new DefaultObjectWrapper());

			Template template = configuration.getTemplate("index.ftl");

			String indexFilePath = distDir + "/index.html";

			System.out.println("Writing " + indexFilePath);

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

}
