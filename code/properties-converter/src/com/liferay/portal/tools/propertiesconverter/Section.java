
package com.liferay.portal.tools.propertiesconverter;

import java.util.List;

public class Section {

	public Section(List<String> descriptionParagraphs, boolean isSectionTitle, String name, String paragraph, List<String> properties, String propertiesParagraph, List<String> sectionProperties) {

		_descriptionParagraphs = descriptionParagraphs;
		_isSectionTitle = isSectionTitle;
		_name = name;
		_paragraph = paragraph;
		_properties = properties;
		_propertiesParagraph = propertiesParagraph;
		_sectionProperties = sectionProperties;
		
	}
	
	public List<String> getDescriptionParagraphs() {
	
		return _descriptionParagraphs;
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
	
	public String getPropertiesParagraph() {
	
		return _propertiesParagraph;
	}
	
	public List<String> getSectionProperties() {
	
		return _sectionProperties;
	}
	
	public void setDescriptionParagraphs(List<String> descriptionParagraphs) {
	
		_descriptionParagraphs = descriptionParagraphs;
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
	
	public void setPropertiesParagraph(String propertiesParagraph) {
	
		_propertiesParagraph = propertiesParagraph;
	}
	
	public void setSectionProperties(List<String> sectionProperties) {
	
		_sectionProperties = sectionProperties;
	}
	
	private List<String> _descriptionParagraphs;
	private boolean _isSectionTitle;
	private String _name;
	private String _paragraph;
	private List<String> _properties;
	private String _propertiesParagraph;
	private List<String> _sectionProperties;
	
}