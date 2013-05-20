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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.tools.PropertiesConverter.PropertyComment;

import java.util.List;

/**
 * Represents a text block of a properties file.
 *
 * <p>
 * A properties section consists of consecutive lines, having no line breaks
 * between them. A properties section can be handled as a section title, section
 * comments, a property comment, a group of default property assignments, or
 * a group of example property assignments.
 * </p>
 *
 * <p>
 * Example - section title:
 * </p>
 *
 * <pre>
 * <code>
 * ##
 * ## Properties Override
 * ##
 * </code>
 * </pre>
 *
 * <p>
 * Example - section comment:
 * </p>
 *
 * <pre>
 * <code>
 * ##
 * ## This paragraph describes the section ...
 * ##
 * ## This paragraph continues describing the section...
 * ##
 * </code>
 * </pre>
 *
 * <p>
 * Example - property comment:
 * </p>
 *
 * <pre>
 * <code>
 * #
 * # This paragraph describes the property(s) in this block ...
 * #
 * </code>
 * </pre>
 *
 * <p>
 * Example - group of default property assignments:
 * </p>
 *
 * <pre>
 * <code>
 * someProperty1=property1DefaultValue
 * someProperty2=property2DefaultValue
 * </code>
 * </pre>
 *
 * <p>
 * Example - group of default property assignments:
 * </p>
 *
 * <pre>
 * <code>
 * #someProperty1=property1ExampleValue
 * #someProperty2=property2ExampleValue
 * </code>
 * </pre>
 *
 * @author Jesse Rao
 * @author James Hinkey
 */
public class PropertiesSection {

	public PropertiesSection(String text) {
		_text = text;
	}

	public List<String> getComments() {
		return _comments;
	}

	public String getDefaultProperties() {
		return _defaultProperties;
	}

	public String getExampleProperties() {
		return _exampleProperties;
	}

	public List<PropertyComment> getPropertyComments() {
		return _propertyComments;
	}

	public String getText() {
		return _text;
	}

	public String getTitle() {
		return _title;
	}

	public boolean hasComments() {
		return Validator.isNotNull(_comments);
	}

	public boolean hasDefaultProperties() {
		return Validator.isNotNull(_defaultProperties);
	}

	public boolean hasExampleProperties() {
		return Validator.isNotNull(_exampleProperties);
	}

	public boolean hasPropertyComments() {
		return Validator.isNotNull(_propertyComments);
	}

	public boolean hasTitle() {
		return Validator.isNotNull(_title);
	}

	public void setComments(List<String> comments) {
		_comments = comments;
	}

	public void setDefaultProperties(String defaultProperties) {
		_defaultProperties = defaultProperties;
	}

	public void setExampleProperties(String exampleProperties) {
		_exampleProperties = exampleProperties;
	}

	public void setPropertyComments(List<PropertyComment> propertyComments) {
		_propertyComments = propertyComments;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private final String _text;

	private List<String> _comments;
	private String _defaultProperties;
	private String _exampleProperties;
	private List<PropertyComment> _propertyComments;
	private String _title;

}