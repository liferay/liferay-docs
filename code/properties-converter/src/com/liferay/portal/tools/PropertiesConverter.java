/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.tools;

//import com.liferay.portal.freemarker.FreeMarkerUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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

/**
 * Generates an HTML file from a properties file.
 *
 * <p>
 * Usage:
 * </p>
 *
 * <pre>
 * <code>
 * java com.liferay.portal.tools.PropertiesConverter property-file-path output-dir title toc
 * </code>
 * </pre>
 *
 * <p>
 * Example:
 * </p>
 *
 * <pre>
 * <code>
 * java com.liferay.portal.tools.PropertiesConverter /home/jbloggs/liferay-portal/portal-impl/src/portal.properties properties "Portal Properties" true
 * </code>
 * </pre>
 *
 * @author Jesse Rao
 * @author James Hinkey
 */
public class PropertiesConverter {

	private static final String _INDENT = new StringBundler(
		StringPool.SPACE).append(
			StringPool.SPACE).append(
				StringPool.SPACE).append(StringPool.SPACE).toString();
	private static final String _INDENT_DOUBLE = _INDENT + _INDENT;
	private static final String _TPL_PROPERTIES_HTML =
		"com/liferay/portal/tools/dependencies/properties_html.ftl";

	public static void main(String[] args) {
		String propertiesFilePath = args[0];

		System.out.println("Converting " + propertiesFilePath + " to HTML");

		String destDir = args[1];

		// Create a data model for Freemarker

		Map root = new HashMap();

		String pageTitle = args[2];

		root.put("pageTitle", pageTitle);

		boolean toc = Boolean.parseBoolean(args[3]);

		root.put("toc", toc);

		int pos = propertiesFilePath.lastIndexOf("/");

		String propertiesFileName = "";

		if (pos != -1) {
			propertiesFileName = propertiesFilePath.substring(pos + 1);
		}
		else {
			propertiesFileName = propertiesFilePath;
		}

		root.put("propertiesFileName", propertiesFileName);

		PropertiesConverter converter = new PropertiesConverter(
			root, propertiesFilePath, propertiesFileName, destDir);
	}

	public PropertiesConverter(Map root, String propertiesFilePath,
		String propertiesFileName, String destDir) {

		File propertiesFile = new File(propertiesFilePath);

		String propertiesString = "";

		propertiesString = _read(propertiesFile);

		String[] sectionStrings = propertiesString.split("\n\n");

		List<PropertiesSection> sections = new ArrayList<PropertiesSection>(
			sectionStrings.length);

		PropertiesSection section;

		for (int i = 0; i < sectionStrings.length; i++) {

			sectionStrings[i] = StringUtil.trimLeading(sectionStrings[i], ' ');
			section = new PropertiesSection(sectionStrings[i]);

			if (sectionStrings[i].startsWith("##")) {
				int numLines = _countLines(sectionStrings[i]);

				if (numLines == 3) {
					section.setTitle(_extractTitle(section));
					sections.add(section);
				}
				else if (numLines > 3) {
					section.setComments(_extractComments(section));
					sections.add(section);
				}
				else {
					System.out.println(
						"Error: PropertiesSection should consist of 3 or " +
						"more lines:\n" +
						"##\n" +
						"## Comment(s)\n" +
						"##");

					return;
				}
			}
			else {
				section.setPropertyComments(_extractPropertyComments(section));
				section.setDefaultProperties(
					_extractDefaultProperties(section));
				section.setExampleProperties(
					_extractExampleProperties(section));
				sections.add(section);
			}

		}

		root.put("sections", sections);

		// Get the Freemarker template and merge it with the data model

		try {
			String htmlFilePath = destDir + "/"  + propertiesFileName + ".html";

			System.out.println("Writing " + htmlFilePath);

			File propertiesHTMLFile = new File(htmlFilePath);

			Writer writer = new FileWriter(propertiesHTMLFile);

			try {Configuration configuration = new Configuration();

				File file = new File(
					"code/properties-converter/src/com/liferay/portal/tools/dependencies");

				configuration.setDirectoryForTemplateLoading(file);
				configuration.setObjectWrapper(new DefaultObjectWrapper());
				Template template = configuration.getTemplate("properties_html.ftl");
				template.process(root, writer);
//				FreeMarkerUtil.process(_TPL_PROPERTIES_HTML, root, writer);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

			writer.flush();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Represents a continuous property comment. The comment can be a paragraph
	 * or series of preformatted lines.
	 *
	 * <p>
	 * The following example would be extracted into a series of paragraphs and
	 * lines of preformatted text, stopping at the first detected property
	 * assignment.
	 * </p>
	 *
	 * <p>
	 * Example - source:
	 * </p>
	 *
	 * <pre>
	 * <code>
	 * #
	 * # This paragraph describes the property(s) in this block ...
	 * #
	 * # Here is some pre-formatted text:
	 * #
	 * #     java SomeClass ...
	 * #
	 * # This paragraph continues describing the property(s) in this block ...
	 * #
	 * someProperty1=property1DefaultValue
	 * someProperty2=property2DefaultValue
	 * #someProperty1=property1ExampleValue
	 * #someProperty2=property2ExampleValue
	 * </code>
	 * </pre>
	 *
	 * <p>
	 * Example - resulting property comment instances:
	 * </p>
	 *
	 * <ol>
	 * <li>
	 * (paragraph) "This paragraph describes the property(s) in this block ..."
	 * </li>
	 * <li>
	 * (paragraph) "Here is some pre-formatted text:"
	 * </li>
	 * <li>
	 * <pre>
	 * <code>
	 *     java SomeClass ...
	 * </code>
	 * </pre>
	 * </li>
	 * <li>
	 * (paragraph) "This paragraph continues describing the property(s) in this block ..."
	 * </li>
	 * </ol>
	 *
	 * @author Jesse Rao
	 * @author James Hinkey
	 */
	public class PropertyComment {

		public PropertyComment(String comment) {
			_comment = comment;

			String[] lines = comment.split(StringPool.NEW_LINE);

			for (int i = 0; i < lines.length; i++) {
				if (lines[i].startsWith(_INDENT)) {
					_isPreFormatted = true;
					break;
				}
			}
		}

		public String getComment() {
			return _comment;
		}

		public boolean hasComment() {
			return Validator.isNotNull(_comment);
		}

		public boolean isPreFormatted() {
			return _isPreFormatted;
		}

		private boolean _isPreFormatted;
		private String _comment;

	}

	private void _appendParagraphAsPropertyComment(
		List<PropertyComment> propertyComments, StringBundler paragraph) {

		if (paragraph.length() > 0) {
			propertyComments.add(new PropertyComment(paragraph.toString()));
		}
	}

	private int _countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return lines.length;
	}

	private List<String> _extractComments(PropertiesSection section) {

		List<String> sectionComments = new ArrayList<String>();
		StringBundler paragraph = new StringBundler();

		String[] lines = section.getText().split(StringPool.NEW_LINE);

		for (int i = 0; i < lines.length; i++) {

			if (lines[i].trim().startsWith("## ")) {
				String line = lines[i].substring(2);

				paragraph.append(line.trim());
			}

			if (lines[i].trim().length() < 3) {
				if (paragraph.length() == 0) {
					continue;
				}
				else {
					sectionComments.add(paragraph.toString());
					paragraph = new StringBundler();
				}
			}
		}

		return sectionComments;
	}

	private String _extractDefaultProperties(PropertiesSection section) {

		StringBundler defaultProperties = new StringBundler();

		String[] lines = section.getText().split(StringPool.NEW_LINE);

		boolean prevIsDefaultProperty = false;

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (!prevIsDefaultProperty) {
				if (line.startsWith(StringPool.POUND) ||
					line.startsWith(_INDENT + StringPool.POUND)) {

					continue;
				}
				else {
					prevIsDefaultProperty = true;

					defaultProperties.append(line + StringPool.NEW_LINE);
				}
			}
			else {
				if (line.startsWith(StringPool.POUND) ||
					line.startsWith(_INDENT + StringPool.POUND)) {

					prevIsDefaultProperty = false;

					continue;
				}
				else {
					defaultProperties.append(line + StringPool.NEW_LINE);
				}
			}
		}

		return defaultProperties.toString();
	}

	private String _extractExampleProperties(PropertiesSection section) {
		StringBundler exampleProperties = new StringBundler();

		boolean prevIsExample = false;
		String[] lines = section.getText().split(StringPool.NEW_LINE);

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (!prevIsExample) {
				if (line.startsWith(
						_INDENT + StringPool.POUND + StringPool.SPACE) ||
					line.trim().equals(StringPool.POUND)) {

					continue;
				}
				else if (line.startsWith(_INDENT + StringPool.POUND)) {
					prevIsExample = true;

					String exampleProperty =
						line.replaceFirst(StringPool.POUND, StringPool.BLANK) +
							StringPool.NEW_LINE;

					exampleProperties.append(exampleProperty);
				}
			}
			else {
				if (!line.trim().startsWith(StringPool.POUND)) {
					prevIsExample = false;

					continue;
				}
				else {
					String exampleProperty =
						line.replaceFirst(StringPool.POUND, StringPool.BLANK) +
							StringPool.NEW_LINE;

					exampleProperties.append(exampleProperty);
				}
			}
		}

		return exampleProperties.toString();
	}

	private List<PropertyComment> _extractPropertyComments(
		PropertiesSection propertiesSection) {

		List<PropertyComment> propertyComments =
			new ArrayList<PropertyComment>();

		StringBundler paragraph = new StringBundler();

		boolean prevIsPreformatted = false;

		// Add property comments as paragraphs. Stop on the first property
		// assignment or property value.

		String[] lines = propertiesSection.getText().split(StringPool.NEW_LINE);

		for (int i = 0; i < lines.length; i++) {
			String line = StringUtil.trimTrailing(lines[i]);

			if (line.startsWith(_INDENT_DOUBLE + StringPool.POUND)) {
				break;
			}
			else if (line.trim().startsWith(
						StringPool.POUND + StringPool.SPACE + _INDENT)) {

				if (prevIsPreformatted) {
					paragraph.append(_replaceFirstPoundWithBlank(line));
				}
				else {
					_appendParagraphAsPropertyComment(
						propertyComments, paragraph);

					paragraph = new StringBundler(
						_replaceFirstPoundWithBlank(line));
				}

				paragraph.append(StringPool.NEW_LINE);
				prevIsPreformatted = true;
			}
			else if (line.trim().startsWith(
						StringPool.POUND + StringPool.SPACE)) {

				if (prevIsPreformatted) {
					_appendParagraphAsPropertyComment(
						propertyComments, paragraph);

					paragraph = new StringBundler(
						_replaceFirstPoundWithBlank(line).trim());
				}
				else {
					if (paragraph.length() > 0) {
						paragraph.append(StringPool.SPACE);
					}

					paragraph.append(
						line.replaceFirst(StringPool.POUND, StringPool.BLANK).
							trim());
				}

				paragraph.append(StringPool.NEW_LINE);
				prevIsPreformatted = false;
			}
			else {
				String trimmedLine = line.trim();

				if (trimmedLine.startsWith(StringPool.POUND)) {
					if (trimmedLine.length() < 2) {
						_appendParagraphAsPropertyComment(
							propertyComments, paragraph);

						paragraph = new StringBundler();

						continue;
					}
					else {
						_appendParagraphAsPropertyComment(
							propertyComments, paragraph);

						break;
					}
				}
				else {
					_appendParagraphAsPropertyComment(
						propertyComments, paragraph);

					break;
				}
			}
		}

		return propertyComments;
	}

	private String _extractTitle(PropertiesSection section) {
		String[] lines = section.getText().split(StringPool.NEW_LINE);

		String title = null;

		if ((lines != null) && (lines.length > 1)) {
			title = lines[1].replaceFirst("##", StringPool.BLANK).trim();
		}

		return title;
	}

	private String _read(File file) {

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

	private String _replaceFirstPoundWithBlank(String line) {
		return line.trim().replaceFirst(StringPool.POUND, StringPool.BLANK);
	}

}