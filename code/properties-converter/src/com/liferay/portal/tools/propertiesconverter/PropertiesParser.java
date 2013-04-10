
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

		// Parse properties file and create sections and properties for the data
		// model

		File propertiesFile = new File(propertiesFilePath);

		String propertiesString = "";

		propertiesString = read(propertiesFile);

		String[] paragraphs = propertiesString.split("\n\n");

		List<Section> sections = new ArrayList<Section>();

		Section section = null;

		for (int i = 0; i < paragraphs.length; i++) {
			if (paragraphs[i].startsWith("##")) {
				String[] paragraphLines = paragraphs[i].split("\n");
				
				if (paragraphLines.length > 3) {
					List<String> description = new ArrayList<String>();
					StringBuilder paragraph = new StringBuilder();
					
					for (int j = 0; j < paragraphLines.length; j++) {
						paragraphLines[j] = paragraphLines[j].replace("##", "").trim();
					}
					
					for (int j = 0; j < paragraphLines.length; j++) {
						if (!paragraphLines[j].isEmpty()) {
							paragraph.append(paragraphLines[j] + " ");
						}
						else {
							if (!paragraph.toString().isEmpty()) {
								description.add(paragraph.toString().trim());
								paragraph = new StringBuilder();
							}
						}
					}
					
					section = new Section(description);
					
					sections.add(section);
				}
				else {
					String title =
						paragraphs[i].replace(
							"#", " ");

					section = new Section(title.trim());

					sections.add(section);
				}
			}
			else {
				String[] lines = paragraphs[i].split("\n");

				Map<String, String> properties = new HashMap<String, String>();

				for (String line : lines) {
					if (line.trim().contains("=") &&
						!line.trim().startsWith("# ")) {

						int eqlPos = line.indexOf("=");

						String propertyKey = line.substring(0, eqlPos);
						String propertyValue = line.substring(eqlPos);

						if (!properties.containsKey(propertyKey)) {
							properties.put(propertyKey, propertyValue);
						}
					}
				}

				Property property = new Property(properties, paragraphs[i]);

				section.addProperty(property);
			}
		}

		// Populate the properties of each section

		for (Section curSection : sections) {
			for (Property property : curSection.getProperties()) {
				List<String> description = new ArrayList<String>();

				String descriptionLine = " ";

				String content = property.getContent();

				String[] lines = content.trim().split("\n", 0);

				for (String line : lines) {
					line = line.trim();

					if (line.matches("#[\\s]+[^\\s].*")) {
						descriptionLine += line.substring(1);
					}
					else {
						if (!descriptionLine.isEmpty()) {
							description.add(descriptionLine);

							descriptionLine = "";
						}
					}
				}

				property.setDescription(description);

				if (property.getProperties().isEmpty()) {
					continue;
				}

				String propertiesParagraph = content.substring(
					content.lastIndexOf("#\n") + 1);

				property.setPropertiesParagraph(propertiesParagraph);
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