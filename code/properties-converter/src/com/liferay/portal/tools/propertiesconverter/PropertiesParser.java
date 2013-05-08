
package com.liferay.portal.tools.propertiesconverter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class PropertiesParser {

	public static void main(String[] args) {

		// Create a data model for Freemarker

		Map root = new HashMap();
		
		String pageTitle = args[0];
		
		root.put("pageTitle", pageTitle);
		
		boolean toc = Boolean.parseBoolean(args[1]);
		
		root.put("toc", toc);

		String propertiesFilePath = args[2];

		System.out.println("Converting " + propertiesFilePath + " to HTML");

		String destDir = args[3];

		int pos = propertiesFilePath.lastIndexOf("/");
		
		String propertiesFileName = "";

		if (pos != -1) {
			propertiesFileName = propertiesFilePath.substring(pos + 1);
		}
		else {
			propertiesFileName = propertiesFilePath;
		}

		root.put("propertiesFileName", propertiesFileName);

		// Parse properties file and create sections for the data model

		File propertiesFile = new File(propertiesFilePath);

		String propertiesString = "";

		propertiesString = read(propertiesFile);

		String[] sectionStrings = propertiesString.split("\n\n");
		
		List<Section> sections = new ArrayList<Section>(sectionStrings.length);
		
		Section section;
		
		for (int i = 0; i < sectionStrings.length; i++) {
			
			if (sectionStrings[i].startsWith("##")) {
				int numLines = countLines(sectionStrings[i]);
				if (numLines > 3) {
					
					// Description section
					
					section = new Section(sectionStrings[i], i);
					sections.add(section);
				}
				else if (numLines == 3) {
					
					// Title section
					
					section = new Section(sectionStrings[i], i, false, false);
					sections.add(section);
				}
				else {
					System.out.println("Error: Invalid section");
				}
			}
			else if (sectionStrings[i].trim().startsWith("#")) {
				
				// Properties section
				
				section = new Section(sectionStrings[i], i, false);
				sections.add(section);
				
			}
			
		}
		
		root.put("sections", sections);

		// Get the Freemarker template and merge it with the data model

		try {
			Configuration configuration = new Configuration();

			File file = new File(
					"code/properties-converter/src/com/liferay/portal/tools/" +
						"propertiesconverter/dependencies");

			try {
				configuration.setDirectoryForTemplateLoading(file);
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}

			configuration.setObjectWrapper(new DefaultObjectWrapper());

			Template template = configuration.getTemplate("properties.ftl");

			String htmlFilePath = destDir + "/"  + propertiesFileName + ".html";

			System.out.println("Writing " + htmlFilePath);

			File propertiesHTMLFile = new File(htmlFilePath);

			Writer writer = new FileWriter(propertiesHTMLFile);

			try {
				template.process(root, writer);
			}
			catch (TemplateException te) {
				te.printStackTrace();
			}

			writer.flush();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static int countLines(String str) {
		   String[] lines = str.split("\r\n|\r|\n");
		   return lines.length;
		}

	private static String read(File file) {

		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append("\n");
				}
			}
			finally {
				input.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}

}