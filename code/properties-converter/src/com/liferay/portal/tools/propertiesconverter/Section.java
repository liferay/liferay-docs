
package com.liferay.portal.tools.propertiesconverter;

import java.util.List;

public class Section {

	boolean isSectionTitle;
	String name;
	String paragraph;
	List<String> properties;
	List<String> sectionProperties;

	public Section(boolean isSectionTitle, String name, String paragraph, List<String> properties, List<String> sectionProperties) {

		this.isSectionTitle = isSectionTitle;
		this.name = name;
		this.paragraph = paragraph;
		this.properties = properties;
		this.sectionProperties = sectionProperties;
	}
	
	public boolean getIsSectionTitle() {

		return isSectionTitle;
	}

	public void setIsSectionTitle(boolean isSectionTitle) {

		this.isSectionTitle = isSectionTitle;
	}


	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getParagraph() {

		return paragraph;
	}

	public void setParagraph(String paragraph) {

		this.paragraph = paragraph;
	}

	public List<String> getProperties() {

		return properties;
	}

	public void setProperties(List<String> properties) {

		this.properties = properties;
	}

	
	public List<String> getSectionProperties() {
	
		return sectionProperties;
	}

	
	public void setSectionProperties(List<String> sectionProperties) {
	
		this.sectionProperties = sectionProperties;
	}

}
