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

package com.liferay.portal.http.service.test;

import com.liferay.portal.kernel.util.StringPool;

import java.io.File;
import java.io.InputStream;

import java.net.URL;

import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

/**
 * @author Miguel Pastor
 */
public class MockBundle implements Bundle {


	public <A> A adapt(Class<A> clazz) {
		return null;
	}


	public int compareTo(Bundle bundle) {
		return 0;
	}


	public Enumeration<URL> findEntries(
		String path, String filePattern, boolean recurse) {

		return Collections.enumeration(new ArrayList<URL>());
	}


	public BundleContext getBundleContext() {
		return null;
	}


	public long getBundleId() {
		return 0;
	}


	public File getDataFile(String fileName) {
		return null;
	}


	public URL getEntry(String path) {
		return null;
	}


	public Enumeration<String> getEntryPaths(String path) {
		return Collections.enumeration(new ArrayList<String>());
	}


	public Dictionary<String, String> getHeaders() {
		return new Hashtable<String, String>();
	}


	public Dictionary<String, String> getHeaders(String locale) {
		return new Hashtable<String, String>();
	}


	public long getLastModified() {
		return 0;
	}


	public String getLocation() {
		return StringPool.BLANK;
	}


	public ServiceReference<?>[] getRegisteredServices() {
		return new ServiceReference<?>[0];
	}


	public URL getResource(String name) {
		return null;
	}


	public Enumeration<URL> getResources(String name) {
		return Collections.enumeration(new ArrayList<URL>());
	}


	public ServiceReference<?>[] getServicesInUse() {
		return new ServiceReference<?>[0];
	}


	public Map<X509Certificate, List<X509Certificate>> getSignerCertificates(
		int signersType) {

		return Collections.emptyMap();
	}


	public int getState() {
		return Bundle.ACTIVE;
	}


	public String getSymbolicName() {
		return "bundle";
	}


	public Version getVersion() {
		return Version.emptyVersion;
	}


	public boolean hasPermission(Object permission) {
		return false;
	}


	public Class<?> loadClass(String className) throws ClassNotFoundException {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return classLoader.loadClass(className);
	}


	public void start() {
		start(0);
	}


	public void start(int options) {
	}


	public void stop() {
	}


	public void stop(int options) {
	}


	public void uninstall() {
	}


	public void update() {
	}


	public void update(InputStream inputStream) {
	}

}