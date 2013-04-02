
package com.liferay.portal.tools.propertiesconverter;

import java.util.List;
import java.util.Map;

public class Property {

	public Property(Map<String, String> properties, String content) {
		_properties = properties;
		_content = content;
	}

	public String getContent() {
		return _content;
	}

	public List<String> getDescription() {
		return _description;
	}

	public Map<String, String> getProperties() {
		return _properties;
	}

	public String getPropertiesParagraph() {
		return _propertiesParagraph;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setDescription(List<String> description) {
		_description = description;
	}

	public void setProperties(Map<String, String> properties) {
		_properties = properties;
	}

	public void setPropertiesParagraph(String propertiesParagraph) {
		_propertiesParagraph = propertiesParagraph;
	}

	private String _content;
	private List<String> _description;
	private Map<String, String> _properties;
	private String _propertiesParagraph;

}