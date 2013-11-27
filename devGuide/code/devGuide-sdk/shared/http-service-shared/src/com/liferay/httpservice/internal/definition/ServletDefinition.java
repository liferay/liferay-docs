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

package com.liferay.httpservice.internal.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

/**
 * @author Raymond Augé
 */
public class ServletDefinition {

	public void addURLPattern(String urlPattern) {
		_urlPatterns.add(urlPattern);
	}

	public Map<String, String> getInitParameters() {
		return _initParameters;
	}

	public String getName() {
		return _name;
	}

	public Servlet getServlet() {
		return _servlet;
	}

	public List<String> getURLPatterns() {
		return _urlPatterns;
	}

	public void setInitParameter(String name, String value) {
		_initParameters.put(name, value);
	}

	public void setInitParameters(Map<String, String> initParameters) {
		_initParameters = initParameters;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setServlet(Servlet servlet) {
		_servlet = servlet;
	}

	public void setURLPatterns(List<String> urlPatterns) {
		_urlPatterns = urlPatterns;
	}

	private Map<String, String> _initParameters = new HashMap<String, String>();
	private String _name;
	private Servlet _servlet;
	private List<String> _urlPatterns = new ArrayList<String>();

}