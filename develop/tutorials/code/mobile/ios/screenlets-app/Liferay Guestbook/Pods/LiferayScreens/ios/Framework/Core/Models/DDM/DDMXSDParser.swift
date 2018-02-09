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
import Foundation

#if LIFERAY_SCREENS_FRAMEWORK
	import SMXMLDocument
#endif

open class DDMXSDParser {

	// MARK: Public methods

	open func parse(_ xsd: String, locale: Locale) -> [DDMField]? {
		let result: [DDMField]?

		let xmlValue = xsd as NSString

		let data = xmlValue.data(using: String.Encoding.utf8.rawValue)

		do {
			let document = try SMXMLDocument(data: data)
			result = processDocument(document, locale: locale)
		}
		catch {
			result = nil
		}

		return result
	}

	// MARK: Private methods

	fileprivate func processDocument(_ document: SMXMLDocument, locale: Locale) -> [DDMField]? {
		var result: [DDMField]?

		if let elements = document.childrenNamed("dynamic-element") {
			let defaultLocale = Locale(
				identifier:document.attributeNamed("default-locale") ?? "en_US")

			result = []

			for element in elements as! [SMXMLElement] {
				if let formField = createFormField(element,
						locale: locale,
						defaultLocale: defaultLocale) {

					result!.append(formField)
				}
			}
		}

		return result
	}

	fileprivate func createFormField(_ xmlElement: SMXMLElement,
			locale: Locale,
			defaultLocale: Locale)
			-> DDMField? {

		let dataType = DDMField.DataType.from(xmlElement:xmlElement)

		let localizedMetadata = processLocalizedMetadata(xmlElement,
				locale: locale,
				defaultLocale: defaultLocale)

		let mergedAttributes = mergeDictionaries(
				dict1: xmlElement.attributes as! [String:AnyObject],
				dict2: localizedMetadata)

		return dataType.createField(
			attributes: mergedAttributes,
			locale: locale,
			version: LiferayServerVersion.v62)
	}

	fileprivate func mergeDictionaries(
			dict1: [String:AnyObject],
			dict2: [String:AnyObject])
			-> [String:AnyObject] {

		var result: [String:AnyObject] = [:]

		for (key1, value1) in dict1 {
			result.updateValue(value1, forKey: key1)
		}

		for (key2, value2) in dict2 {
			result.updateValue(value2, forKey: key2)
		}

		return result
	}

	fileprivate func processLocalizedMetadata(_ dynamicElement: SMXMLElement,
			locale: Locale,
			defaultLocale: Locale)
			-> [String:AnyObject] {

		var result: [String:AnyObject] = [:]

		func addElement(
				name elementName: String,
				metadata: SMXMLElement,
				result: inout [String:AnyObject]) {

			if let element = metadata.child(withAttribute: "name", value: elementName) {
				result[elementName] = element.value as AnyObject
			}
		}

		func findOptions(
				dynamicElement: SMXMLElement,
				locale: Locale,
				defaultLocale: Locale)
				-> [[String:AnyObject]]? {

			var options: [[String:AnyObject]] = []

			let optionElements = childrenWithAttribute("type",
					value: "option",
					parent: dynamicElement)

			for optionElement in optionElements {
				var option: [String:AnyObject] = [:]

				option["name"] = optionElement.attributeNamed("name") as AnyObject
				option["value"] = optionElement.attributeNamed("value") as AnyObject

				if let localizedMetadata = findMetadataElement(optionElement,
						locale: locale, defaultLocale: defaultLocale) {
					if let element = localizedMetadata.child(withAttribute: "name", value: "label") {
						option["label"] = element.value as AnyObject?
					}
				}

				options.append(option)
			}

			return options.isEmpty ? nil : options
		}

		if let localizedMetadata = findMetadataElement(dynamicElement,
				locale: locale,
				defaultLocale: defaultLocale) {

			addElement(name: "label", metadata: localizedMetadata, result: &result)
			addElement(name: "predefinedValue", metadata: localizedMetadata, result: &result)
			addElement(name: "tip", metadata: localizedMetadata, result: &result)
		}

		if let options = findOptions(
				dynamicElement: dynamicElement,
				locale: locale,
				defaultLocale: defaultLocale) {

			result["options"] = options as AnyObject?
		}

		return result
	}

	fileprivate func findMetadataElement(_ dynamicElement: SMXMLElement,
			locale: Locale, defaultLocale: Locale)
			-> SMXMLElement? {

		// Locale matching fallback mechanism: it's designed in such a way to return
		// the most suitable locale among the available ones. It minimizes the default 
		// locale fallback. It supports input both with one component (language) and
		// two components (language and country).
		//
		// Examples for locale = "es_ES"
		// 	a1. Matches elements with "es_ES" (full match)
		//  a2. Matches elements with "es"
		//  a3. Matches elements for any country: "es_ES", "es_AR"...
		//  a4. Matches elements for default locale

		// Examples for locale = "es"
		// 	b1. Matches elements with "es" (full match)
		//  b2. Matches elements for any country: "es_ES", "es_AR"...
		//  b3. Matches elements for default locale

		let metadataElements = dynamicElement.childrenNamed("meta-data") as? [SMXMLElement]

		if metadataElements == nil {
			return nil
		}

		let currentLanguageCode = (locale as NSLocale).object(forKey: NSLocale.Key.languageCode) as! String
		let currentCountryCode = (locale as NSLocale).object(forKey: NSLocale.Key.countryCode) as? String

		var resultElement: SMXMLElement?

		if let metadataElement = findElementWithAttribute("locale",
				value:locale.identifier, elements:metadataElements!) {
			// cases 'a1' and 'b1'

			resultElement = metadataElement
		}
		else {
			if currentCountryCode != nil {
				if let metadataElement = findElementWithAttribute("locale",
						value:currentLanguageCode,
						elements:metadataElements!) {
					// case 'a2'

					resultElement = metadataElement
				}
			}
		}

		if resultElement == nil {
			// Pre-final fallback (a3, b2): find any metadata starting with language

			let foundMetadataElements = metadataElements!.filter({ (metadataElement: SMXMLElement) -> Bool in
					let elementLocaleMetadata = metadataElement.attributes["locale"]
					if let metadataLocale = elementLocaleMetadata as? String {
						return metadataLocale.hasPrefix(currentLanguageCode + "_")
					}

					return false
				})

			resultElement = foundMetadataElements.first
		}

		if resultElement == nil {
			// Final fallback (a4, b3): find default metadata

			resultElement = findElementWithAttribute("locale",
				value:defaultLocale.identifier, elements:metadataElements!)
		}

		return resultElement
	}

	fileprivate func childrenWithAttribute(_ attribute: String, value: String, parent: SMXMLElement) ->
			[SMXMLElement] {

		var result: [SMXMLElement] = []

		for element in parent.children {
			let child = element as! SMXMLElement
			let attrValue = child.attributeNamed(attribute)
			if attrValue != nil && attrValue == value {
				result.append(child)
			}
		}

		return result
	}

	fileprivate func findElementWithAttribute(_ attribute: String, value: String, elements: [SMXMLElement])
			-> SMXMLElement? {

		for element in elements {
			let attrValue = element.attributeNamed(attribute)
			if attrValue != nil && attrValue == value {
				return element
			}
		}

		return nil
	}

}
