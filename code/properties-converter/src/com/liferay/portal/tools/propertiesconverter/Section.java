
package com.liferay.portal.tools.propertiesconverter;

import java.util.List;

public class Section {

	boolean isSectionTitle;
	String name;
	String paragraph;
	List<String> properties;
	List<String> sectionProperties;
	List<String> descriptionParagraphs;
	String propertiesParagraph;

	public Section(boolean isSectionTitle, String name, String paragraph, List<String> properties, List<String> sectionProperties, List<String> descriptionParagraphs, String propertiesParagraph) {

		this.isSectionTitle = isSectionTitle;
		this.name = name;
		this.paragraph = paragraph;
		this.properties = properties;
		this.sectionProperties = sectionProperties;
		this.descriptionParagraphs = descriptionParagraphs;
		this.propertiesParagraph = propertiesParagraph;
	}
	
	public boolean getIsSectionTitle() {
	
		return isSectionTitle;
	}
	
	public String getName() {
	
		return name;
	}
	
	public String getParagraph() {
	
		return paragraph;
	}
	
	public List<String> getProperties() {
	
		return properties;
	}
	
	public List<String> getSectionProperties() {
	
		return sectionProperties;
	}
	
	public List<String> getDescriptionParagraphs() {
	
		return descriptionParagraphs;
	}
	
	public String getPropertiesParagraph() {
	
		return propertiesParagraph;
	}
	
	public void setIsSectionTitle(boolean isSectionTitle) {
	
		this.isSectionTitle = isSectionTitle;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
	
	public void setParagraph(String paragraph) {
	
		this.paragraph = paragraph;
	}
	
	public void setProperties(List<String> properties) {
	
		this.properties = properties;
	}
	
	public void setSectionProperties(List<String> sectionProperties) {
	
		this.sectionProperties = sectionProperties;
	}
	
	public void setDescriptionParagraphs(List<String> descriptionParagraphs) {
	
		this.descriptionParagraphs = descriptionParagraphs;
	}
	
	public void setPropertiesParagraph(String propertiesParagraph) {
	
		this.propertiesParagraph = propertiesParagraph;
	}
	
}
