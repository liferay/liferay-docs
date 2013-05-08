
package com.liferay.portal.tools.propertiesconverter;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Section {

	public Section(String section, boolean title, boolean description, boolean propertyText, boolean activeProperties, boolean inactiveProperties) {
	
		if (title) {
			_title = extractSectionTitle(section);
		}

		if (description) {
			_descriptionParagraphs = extractDescriptionParagraphs(section);
		}

		if (propertyText) {
			_propertiesParagraphs = extractPropertiesParagraphs(section);
		}

		if (activeProperties) {
			_activeProperties = extractActiveProperties(section);
		}

		if (inactiveProperties) {
			_inactiveProperties = extractInactiveProperties(section);
		}
	}
	
	public List<String> get_descriptionParagraphs() {
		
		return _descriptionParagraphs;
	}

	public List<PropertiesParagraph> get_propertiesParagraphs() {
		
		return _propertiesParagraphs;
	}

	public String get_activeProperties() {
		
		return _activeProperties;
	}

	public String get_inactiveProperties() {
		
		return _inactiveProperties;
	}

	public String get_title() {
		
		return _title;
	}

	public void set_descriptionParagraphs(
			List<String> descriptionParagraphs) {
		
		_descriptionParagraphs = descriptionParagraphs;
	}

	public void set_propertiesParagraphs(
			List<PropertiesParagraph> propertiesParagraphs) {
		
		_propertiesParagraphs = propertiesParagraphs;
	}

	public void set_activeProperties(
			String activeProperties) {
		
		_activeProperties = activeProperties;
	}

	public void set_inactiveProperties(
			String inactiveProperties) {
		
		_inactiveProperties = inactiveProperties;
	}

	public void set_title(String title) {
		
		_title = title;
	}
	
	private static List<String> extractDescriptionParagraphs(String descriptionSection) {
		
		List<String> descriptionParagraphs = new ArrayList<String>();
		
		String[] lines = descriptionSection.split("\n");
		
		StringBuilder currentParagraph = new StringBuilder();
		
		for (int i = 0; i < lines.length; i++) {
			
			if (lines[i].trim().startsWith("## ")) {
				currentParagraph.append(lines[i].substring(2).trim());
			}

			if (lines[i].trim().length() < 3) {

				if (currentParagraph.length() == 0) {
					continue;
				} else {
					descriptionParagraphs.add(currentParagraph.toString());
					currentParagraph = new StringBuilder();
				}

			}

		}

		return descriptionParagraphs;
	}

	private static List<PropertiesParagraph> extractPropertiesParagraphs(String propertiesSection) {

		List<PropertiesParagraph> propertiesParagraphs = new ArrayList<PropertiesParagraph>();

		String[] lines = propertiesSection.split("\n");

		StringBuilder currentParagraph = new StringBuilder();

		boolean isPreformatted = false;

		// Add property comments as paragraphs. Stop on the first property
		// assignment.

		for (int i = 0; i < lines.length; i++) {
			String line = StringUtil.trimTrailing(lines[i]);

			if (line.startsWith("        #")) {
				// Comment embedded in value list

				break;
			}
			else if (line.trim().startsWith("#     ")) {
				// Found pre-formatted text

				if (isPreformatted) {
					// Append to previous pre-formatted text block
					currentParagraph.append(line.trim().replaceFirst("#", ""));
				}
				else {
					isPreformatted = true;

					// Add previous pre-formated text block to list
					propertiesParagraphs.add(new PropertiesParagraph(currentParagraph.toString()));

					// Start a new regular paragraph
					currentParagraph = new StringBuilder();
					currentParagraph.append(line.trim().replaceFirst("#", ""));
				}

				currentParagraph.append("\n");
			}
			else if (line.trim().startsWith("# ")) {
				// Found a regular comment

				if (isPreformatted) {
					isPreformatted = false;

					// Add previous pre-formated text block to list
					propertiesParagraphs.add(new PropertiesParagraph(currentParagraph.toString()));

					// Start a new regular paragraph
					currentParagraph = new StringBuilder();
					currentParagraph.append(line.trim().replaceFirst("#", "").trim());
				}
				else {
					// Append to current paragraph
					if (currentParagraph.length() > 0) {
						currentParagraph.append(" ");
					}

					currentParagraph.append(line.replaceFirst("#", "").trim());
				}

				currentParagraph.append("\n");
			}
			else if (line.trim().startsWith("#")) {
				if (line.trim().length() < 2) {
					if (i == 0) {
						// Continue past initial leading empty comment line
						continue;
					}

					propertiesParagraphs.add(new PropertiesParagraph(currentParagraph.toString()));

					currentParagraph = new StringBuilder();

					continue;
				}
				else {
					// Inactive property assignment

					// Add current paragraph to list and cease processing
					if (currentParagraph.length() > 0) {
						propertiesParagraphs.add(new PropertiesParagraph(currentParagraph.toString()));
					}

					break;
				}
			}
			else {
				// Active property assignment

				// Add current paragraph to list and cease processing
				if (currentParagraph.length() > 0) {
					propertiesParagraphs.add(new PropertiesParagraph(currentParagraph.toString()));
				}

				break;
			}
			
		}

		return propertiesParagraphs;
	}

	private static String extractActiveProperties(String propertiesSection) {
		
		StringBuilder activeProperties = new StringBuilder();
		
		String[] lines = propertiesSection.split("\n");

		boolean isActiveProperty = false;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (!isActiveProperty) {
				if (line.startsWith("#") || line.startsWith("    #")) {
					continue;
				}
				else {
					isActiveProperty = true;
					activeProperties.append(line + "\n");
				}
			}
			else {
				if (line.startsWith("#") || line.startsWith("    #")) {
					isActiveProperty = false;

					continue;
				}
				else {
					activeProperties.append(line + "\n");
				}
			}
		}
		
		return activeProperties.toString();
	}

	private static String extractInactiveProperties(String propertiesSection) {
		
		StringBuilder inactiveProperties = new StringBuilder();
		
		String[] lines = propertiesSection.split("\n");

		boolean isInactiveProperty = false;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (!isInactiveProperty) {
				if (line.startsWith("    # ") || line.trim().equals("#")) {
					continue;
				}
				else if (line.startsWith("    #")) {
					isInactiveProperty = true;
					inactiveProperties.append(line.replaceFirst("#", "") + "\n");
				}
			}
			else {
				if (!line.trim().startsWith("#")) {
					isInactiveProperty = false;

					continue;
				}
				else {
					inactiveProperties.append(line.replaceFirst("#", "") + "\n");
				}
			}
		}
		
		return inactiveProperties.toString();	
	}

	private static String extractSectionTitle(String titleSection) {
		
		String[] lines = titleSection.split("\n");
		
		return lines[1].replaceFirst("##", "").trim();
	
	}
	
	// descriptionSection
	
	private List<String> _descriptionParagraphs;
	
	// propertiesSection
	
	private List<PropertiesParagraph> _propertiesParagraphs;
	
	private String _activeProperties;
	
	private String _inactiveProperties;
	
	// titleSection
	
	private String _title;

}