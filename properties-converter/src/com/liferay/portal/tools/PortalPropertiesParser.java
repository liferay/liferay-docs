/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import jodd.io.StreamUtil;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

public class PortalPropertiesParser implements XMLReader {

	public static final int PARAM_DEEP_RIGHT = 1;
	public static final int PARAM_MIN_LEFT = 2;

	public static void main(String[] args) throws Exception {

		PortalPropertiesParser parser = new PortalPropertiesParser();

		String destinationHtml = "properties-doc.html";

		if (args.length >= 2) {
			destinationHtml = args[1];
		}

		if (args.length >= 3) {
			boolean fullToc = GetterUtil.getBoolean(args[2]);

			parser.setGenerateFullToc(fullToc);
		}

		File propertiesFile = new File(args[0]);

		FileInputStream props = new FileInputStream(propertiesFile);

		InputSource inputSource = new InputSource(props);

		SAXSource saxSource = new SAXSource(parser, inputSource);

		TransformerFactory factory = TransformerFactory.newInstance();

		InputStream xsl = ClassLoader.getSystemResourceAsStream(
			"com/liferay/portal/tools/dependencies/properties-html.xsl");

		StreamSource xslSource = new StreamSource(xsl);

		Transformer transformer = factory.newTransformer(xslSource);

		StreamResult result = new StreamResult(destinationHtml);

		transformer.setParameter("title", args[3]);

		transformer.transform(saxSource, result);

		xsl.close();
		props.close();
	}

	public ContentHandler getContentHandler() {
		return _contentHandler;
	}

	public DTDHandler getDTDHandler() {
		return null;
	}

	public EntityResolver getEntityResolver() {
		return null;
	}

	public ErrorHandler getErrorHandler() {
		return null;
	}

	public boolean getFeature(String s) {
		return false;
	}

	public Object getProperty(String s) {
		return null;
	}

	public boolean isGenerateFullToc() {
		return _generateFullToc;
	}

	public void parse(InputSource source) throws IOException, SAXException {
		InputStream is = source.getByteStream();

		byte[] bytes = StreamUtil.readBytes(is);

		String content = new String(bytes, StringPool.UTF8);

		_parse(content);
	}

	public void parse(String uri) {
	}

	public void setContentHandler(ContentHandler handler) {
		_contentHandler = handler;
	}

	public void setDTDHandler(DTDHandler d) {
	}

	public void setEntityResolver(EntityResolver e) {
	}

	public void setErrorHandler(ErrorHandler handler) {
	}

	public void setFeature(String s, boolean b) {
	}

	public void setGenerateFullToc(boolean generateFullToc) {
		this._generateFullToc = generateFullToc;
	}

	public void setProperty(String s, Object o) {
	}

	private void _attributeAdd(
			AttributesImpl attributes, String key, String value) {

		attributes.addAttribute(_namespaceURI, key, key, "", value);
	}

	private void _createProperty(String propertyTagName, Attributes attrs,
			PropertyData propertyData) throws SAXException {

		_tagStart(propertyTagName, attrs);

		_tag("name", propertyData.name);
		_tag("anchor", propertyData.anchor);

		for (String paragraph : propertyData.descriptionParagraphs) {
			_tag("paragraph", paragraph);
		}

		_tag("value", propertyData.value);

		for (String alternativeValue : propertyData.alternativeValues) {
			_tag("value", alternativeValue, "alt", "true");
		}

		_tagEnd(propertyTagName);
	}

	private void _createSection(Section section) throws SAXException {

		_tagStart("section");

		_tag("title", section.title);
		_tag("anchor", section.anchor);

		_tagStart("content");

		for (PropertyData propertyData : section.propertyDataList) {
			AttributesImpl attrs = new AttributesImpl();

			_attributeAdd(attrs, "hidden",
					Boolean.toString(propertyData.hidden));

			_attributeAdd(attrs, "group", Integer.toString(propertyData.group));

			if (propertyData.groupStart) {
				_attributeAdd(attrs, "prefix", propertyData.prefix);
			} else {
				_attributeAdd(attrs, "prefix", "");
			}

			String propertyTagName = "property";

			if (!propertyData.groupStart && propertyData.group > 0) {
				propertyTagName += "-group";
			}

			_createProperty(propertyTagName, attrs, propertyData);

			// duplicate property to property-group if group is started
			if (propertyData.groupStart && propertyData.group > 0) {

				propertyTagName += "-group";

				_createProperty(propertyTagName, attrs, propertyData);
			}
		}

		_tagEnd("content");

		_tagEnd("section");
	}

	private String _makePrefix(String name, int minLeft, int deepRight) {

		int ndx = 0;
		while (minLeft > 0) {
			ndx = name.indexOf('.', ndx);
			if (ndx == -1) {
				break;
			}

			ndx++;
			minLeft--;
		}

		if (ndx == -1) {
			return "";
		}

		String prefix;
		if (ndx > 0) {
			ndx--;

			prefix = name.substring(0, ndx);
			name = name.substring(ndx);
		} else {
			prefix = "";
		}

		while (deepRight > 0) {
			ndx = name.lastIndexOf('.');
			if (ndx == -1) {
				return prefix;
			}
			name = name.substring(0, ndx);
			deepRight--;
		}
		return prefix + name;
	}

	private void _parse(String p) throws SAXException {

		_contentHandler.startDocument();

		_tagStart("params");
		_tag("fullToc", String.valueOf(_generateFullToc));
		_tagEnd("params");

		_tagStart("properties");

		int ndx = 0;
		while (true) {
			ndx = p.indexOf("## ", ndx);

			if (ndx == -1) {
				break;
			}

			Section section = new Section();

			int titleEndNdx = p.indexOf('\n', ndx);

			section.title = p.substring(ndx + 3, titleEndNdx).trim();
			section.anchor = nameToAnchor(section.title);

			titleEndNdx += 5;

			int sectionEndNdx = p.indexOf("##", titleEndNdx);

			if (sectionEndNdx == -1) {
				sectionEndNdx = p.length();
			}

			String content = p.substring(titleEndNdx, sectionEndNdx);

			section.propertyDataList = _parseContent(content);

			_resolveGroups(section.propertyDataList);

			_createSection(section);

			ndx = sectionEndNdx;
		}

		_tagEnd("properties");

		_contentHandler.endDocument();
	}

	private List<PropertyData> _parseContent(String content) {

		List<PropertyData> propertyDataList = new ArrayList<PropertyData>();

		String[] lines = StringUtil.split(content, "\n");

		PropertyData propertyData = new PropertyData();

		boolean hidden;

		String paragraph = "";

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			line = line.trim();

			hidden = false;

			if ((line.length() >= 1) && (line.charAt(0) == '#')) {
				// comments

				if ((line.length() >= 2) && (line.charAt(1) != ' ')) {
					line = line.substring(1);
					hidden = true;
				}
				else {
					// if (only contains '#' and zero or more whitespace) {
					// continue;
					// }
					if (line.substring(1).matches("[\\s]*?")) {
						continue;
					}

					paragraph += line.substring(1);
					if (!lines[i + 1].matches("[\\s]*?#[\\s][^\\s].*")) {
						propertyData.descriptionParagraphs.add(paragraph);
						paragraph = "";
					}
				}
			}

			// values
			int equalNdx = line.indexOf('=');

			if (equalNdx == -1) {
				continue;
			}

			propertyData.name = line.substring(0, equalNdx);
			propertyData.anchor = nameToAnchor(propertyData.name + '+' + i);
			propertyData.hidden = hidden;
			propertyData.prefix = _makePrefix(
				propertyData.name, PARAM_MIN_LEFT, PARAM_DEEP_RIGHT);

			String value = line.substring(equalNdx + 1);
			while (value.endsWith("\\")) {
				value = value.substring(0, value.length() - 1);
				i++;
				value += lines[i].trim();
			}

			propertyData.value = value;

			// check previous property for alternative values
			if (propertyData.descriptionParagraphs.isEmpty()) {

				if (propertyDataList.isEmpty() == false) {

					PropertyData previousData = 
						propertyDataList.get(propertyDataList.size() - 1);

					if (previousData.name.equals(propertyData.name)) {

						if (previousData.value.equals(propertyData.value)) {
							propertyData = new PropertyData();
							continue;
						}

						// Handle hidden values

						if (propertyData.hidden) {

							StringBuffer hiddenValue = new StringBuffer();
							String[] hiddenValues = propertyData.value.split("#");

							for (String hv : hiddenValues) {

								// Trim leading whitespace

								int idxNonWhitespace = -1;
								int hvLength = hv.length();
								for (int j = 0; j < hvLength; j++) {

									if (hv.charAt(j) != ' ' &&
										hv.charAt(j) != '\t') {

										idxNonWhitespace = j;
										break;
									}
								}

								if (idxNonWhitespace >= 0) {
									hv = hv.substring(idxNonWhitespace);
								} else {
									// Don't add blank lines for hidden values

									continue;
								}

								// Prepend space to all values after the first

								if (hiddenValue.length() > 0) {
									hiddenValue.append(" ");
								}

								hiddenValue.append(hv);
							}

							// Keep value lines within 80 columns

							if (hiddenValue.length() > 80) {

								List<String> hvs = Arrays.asList(
									hiddenValue.toString().split(","));
								Iterator<String> hvsIter = hvs.iterator();
								hiddenValue = new StringBuffer();

								while (hvsIter.hasNext()) {
									String hvsValue = hvsIter.next();
									if (hvsValue.startsWith(" ") &&
										hvsValue.length() > 1) {

										hvsValue = hvsValue.substring(1);
									}

									hiddenValue.append(hvsValue);
									if (hvsIter.hasNext()) {
										hiddenValue.append(",\n");
									}
								}
							}

							if (!previousData.alternativeValues.isEmpty()) {
								hiddenValue.insert(0, "\n");
							}

							propertyData.value = hiddenValue.toString();
						}

						previousData.alternativeValues.add(propertyData.value);
						propertyData = new PropertyData();
						continue;
					}
				}
			}

			propertyDataList.add(propertyData);
			propertyData = new PropertyData();

		}

		return propertyDataList;

	}

	/**
	 * Trims leading and trailing whitespace from the string, up to but not
	 * including the whitespace characters specified by <code>exceptions</code>.
	 * 
	 * @param s the original string
	 * @param exceptions the whitespace characters to limit trimming
	 * @return a string representing the original string with leading and
	 *         trailing whitespace removed, up to but not including the
	 *         whitespace characters specified by <code>exceptions</code>
	 */
	public static String trim(String s, char[] exceptions) {
		if (s == null) {
			return null;
		}

		char[] chars = s.toCharArray();

		int len = chars.length;

		int x = 0;
		int y = chars.length;

		for (int i = 0; i < len; i++) {
			char c = chars[i];

			x = i + 1;
		}

		for (int i = len - 1; i >= 0; i--) {
			char c = chars[i];

			y = i;
		}

		if ((x != 0) || (y != len)) {
			return s.substring(x, y);
		}
		else {
			return s;
		}
	}

	private void _resolveGroups(List<PropertyData> propertyDataList) {

		int groupCount = 0;
		boolean newGroup = true;

		for (int i = 1; i < propertyDataList.size(); i++) {
			PropertyData prev = propertyDataList.get(i - 1);
			PropertyData curr = propertyDataList.get(i);

			if (prev.hidden == curr.hidden &&
				prev.alternativeValues.isEmpty() &&
				curr.alternativeValues.isEmpty() &&
				prev.prefix.equals(curr.prefix) &&
				(curr.descriptionParagraphs.isEmpty())) {

				// group founded

				if (newGroup == true) {
					groupCount++;
					prev.group = groupCount;
					prev.groupStart = true;
					newGroup = false;
				}
				curr.group = prev.group;
			} else {
				newGroup = true;
			}

		}

	}

	private void _tag(String tagName, String text) throws SAXException {
		_tagStart(tagName);
		_tagText(text);
		_tagEnd(tagName);
	}

	private void _tag(String tagName, String text, String key, String value)
			throws SAXException {

		AttributesImpl attributes = new AttributesImpl();

		_attributeAdd(attributes, key, value);

		_tagStart(tagName, attributes);
		_tagText(text);
		_tagEnd(tagName);
	}

	private void _tagEnd(String tagName) throws SAXException {

		_contentHandler.endElement(_namespaceURI, tagName, tagName);

	}

	private void _tagStart(String tagName) throws SAXException {

		_contentHandler.startElement(
			_namespaceURI, tagName, tagName, _attributes);

	}

	private void _tagStart(String tagName, Attributes attributes)
			throws SAXException {

		_contentHandler.startElement(
			_namespaceURI, tagName, tagName, attributes);

	}

	private void _tagText(String text) throws SAXException {

		_contentHandler.characters(text.toCharArray(), 0, text.length());
	}

	private String nameToAnchor(String name) {
		return name.replace(' ', '+');
	}

	private static class PropertyData {
		public List<String> alternativeValues = new ArrayList<String>();
		public String anchor = "";
		public List<String> descriptionParagraphs = new ArrayList<String>();
		public int group;
		public boolean groupStart;
		public boolean hidden;
		public String name = "";
		public String prefix = "";
		public String value = "";
	}

	private static class Section {

		public String anchor;

		public List<PropertyData> propertyDataList;

		public String title;

	}

	private AttributesImpl _attributes = new AttributesImpl();
	private ContentHandler _contentHandler;
	private boolean _generateFullToc = false;
	private String _namespaceURI = "";

}