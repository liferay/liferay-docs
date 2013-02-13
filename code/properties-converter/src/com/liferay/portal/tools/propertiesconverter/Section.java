
package com.liferay.portal.tools.propertiesconverter;

import java.util.List;

public class Section {

	public Section(boolean isSectionTitle, String name, String paragraph, List<String> properties, List<String> sectionProperties, List<String> descriptionParagraphs, String propertiesParagraph) {

		_isSectionTitle = isSectionTitle;
		_name = name;
		_paragraph = paragraph;
		_properties = properties;
		_sectionProperties = sectionProperties;
		_descriptionParagraphs = descriptionParagraphs;
		_propertiesParagraph = propertiesParagraph;
	}
	
	public boolean getIsSectionTitle() {
	
		return _isSectionTitle;
	}
	
	public String getName() {
	
		return _name;
	}
	
	public String getParagraph() {
	
		return _paragraph;
	}
	
	public List<String> getProperties() {
	
		return _properties;
	}
	
	public List<String> getSectionProperties() {
	
		return _sectionProperties;
	}
	
	public List<String> getDescriptionParagraphs() {
	
		return _descriptionParagraphs;
	}
	
	public String getPropertiesParagraph() {
	
		return _propertiesParagraph;
	}
	
	public void setIsSectionTitle(boolean isSectionTitle) {
	
		_isSectionTitle = isSectionTitle;
	}
	
	public void setName(String name) {
	
		_name = name;
	}
	
	public void setParagraph(String paragraph) {
	
		_paragraph = paragraph;
	}
	
	public void setProperties(List<String> properties) {
	
		_properties = properties;
	}
	
	public void setSectionProperties(List<String> sectionProperties) {
	
		_sectionProperties = sectionProperties;
	}
	
	public void setDescriptionParagraphs(List<String> descriptionParagraphs) {
	
		_descriptionParagraphs = descriptionParagraphs;
	}
	
	public void setPropertiesParagraph(String propertiesParagraph) {
	
		_propertiesParagraph = propertiesParagraph;
	}
	
	private boolean _isSectionTitle;
	private String _name;
	private String _paragraph;
	private List<String> _properties;
	private List<String> _sectionProperties;
	private List<String> _descriptionParagraphs;
	private String _propertiesParagraph;
	
}