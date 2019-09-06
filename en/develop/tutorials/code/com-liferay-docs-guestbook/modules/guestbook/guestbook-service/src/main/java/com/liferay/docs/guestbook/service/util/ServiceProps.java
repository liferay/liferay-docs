/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.docs.guestbook.service.util;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.configuration.Filter;

import java.util.Properties;

/**
 * @author liferay
 * @generated
 */
@ProviderType
public class ServiceProps {
	public static void addProperties(Properties properties) {
		_instance._configuration.addProperties(properties);
	}

	public static boolean contains(String key) {
		return _instance._configuration.contains(key);
	}

	public static String get(String key) {
		return _instance._configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		return _instance._configuration.get(key, filter);
	}

	public static String[] getArray(String key) {
		return _instance._configuration.getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		return _instance._configuration.getArray(key, filter);
	}

	public static Properties getProperties() {
		return _instance._configuration.getProperties();
	}

	public static void removeProperties(Properties properties) {
		_instance._configuration.removeProperties(properties);
	}

	public static void set(String key, String value) {
		_instance._configuration.set(key, value);
	}

	private ServiceProps() {
		_configuration = ConfigurationFactoryUtil.getConfiguration(getClass()
																	   .getClassLoader(),
				"service");
	}

	private static ServiceProps _instance = new ServiceProps();
	private Configuration _configuration;
}