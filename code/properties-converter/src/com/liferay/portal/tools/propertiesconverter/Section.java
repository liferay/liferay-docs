
package com.liferay.portal.tools.propertiesconverter;

import java.util.ArrayList;
import java.util.List;

public class Section {
	
	public Section(String descriptionSection, int position) {

		_descriptionParagraphs = extractDescriptionParagraphs(descriptionSection);
	}

	public Section(String propertiesSection, int position, boolean dummy) {

		_propertiesParagraphs = extractPropertiesParagraphs(propertiesSection);
		
		_activeProperties = extractActiveProperties(propertiesSection);
		
		_inactiveProperties = extractInactiveProperties(propertiesSection);
	}

	public Section(String titleSection, int position, boolean dummy, boolean dummy2) {

		_title = extractSectionTitle(titleSection);
	}
	
	public List<String> get_descriptionParagraphs() {
		
		return _descriptionParagraphs;
	}

	public List<String> get_propertiesParagraphs() {
		
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
			List<String> propertiesParagraphs) {
		
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

	private static List<String> extractPropertiesParagraphs(String propertiesSection) {

		List<String> propertiesParagraphs = new ArrayList<String>();

		String[] lines = propertiesSection.split("\n");

		StringBuilder currentParagraph = new StringBuilder();

		for (int i = 0; i < lines.length; i++) {

			if (lines[i].trim().startsWith("# ")) {
				if (!(currentParagraph.length() == 0)) {
					currentParagraph.append(" " + lines[i].replaceFirst("#", "").trim());
				}
				else {
					currentParagraph.append(lines[i].replaceFirst("#", "").trim());
				}
			}
			else if (lines[i].trim().length() < 2) {
				if (currentParagraph.length() == 0) {
					continue;
				} else {
					propertiesParagraphs.add(currentParagraph.toString());
					currentParagraph = new StringBuilder();
				}
			}
			
		}

		return propertiesParagraphs;
	}

	private static String extractActiveProperties(String propertiesSection) {
		
		StringBuilder activeProperties = new StringBuilder();
		
		String[] lines = propertiesSection.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			
			if (lines[i].trim().startsWith("# ") || lines[i].length() < 2) {
				continue;
			}
			
			if (lines[i].contains("=") && !lines[i].trim().startsWith("#")) {
				activeProperties.append(lines[i] + "\n");
			}
		}
		
		return activeProperties.toString();
	}

	private static String extractInactiveProperties(String propertiesSection) {
		
		StringBuilder activeProperties = new StringBuilder();
		
		String[] lines = propertiesSection.split("\n");
		
		for (int i = 0; i < lines.length; i++) {
			
			if (lines[i].trim().startsWith("# ") || lines[i].length() < 2) {
				continue;
			}
			
			if (lines[i].contains("=") && lines[i].trim().startsWith("#")) {
				activeProperties.append(lines[i].replaceFirst("#", "") + "\n");
			}
		}
		
		return activeProperties.toString();	
	}

	private static String extractSectionTitle(String titleSection) {
		
		String[] lines = titleSection.split("\n");
		
		return lines[1].replaceFirst("##", "").trim();
	
	}
	
	// descriptionSection
	
	private List<String> _descriptionParagraphs;
	
	// propertiesSection
	
	private List<String> _propertiesParagraphs;
	
	private String _activeProperties;
	
	private String _inactiveProperties;
	
	// titleSection
	
	private String _title;

}