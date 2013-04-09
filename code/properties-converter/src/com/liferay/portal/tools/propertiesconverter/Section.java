
package com.liferay.portal.tools.propertiesconverter;

import java.util.ArrayList;
import java.util.List;

public class Section {
	
	public Section(List<String> description) {
		_description = description;
	}

	public Section(String title) {
		_title = title;
	}
	
	public void addProperty(Property property) {
		_properties.add(property);
	}
	
	public List<String> getDescription() {
		return _description;
	}
	
	public List<Property> getProperties() {
		return _properties;
	}

	public String getTitle() {
		return _title;
	}
	
	public void setDescription(List<String> description) {
		_description = description;
	}

	public void setTitle(String title) {
		_title = title;
	}
	
	private List<String> _description;
	
	private List<Property> _properties = new ArrayList<Property>();
	
	private String _title;

}