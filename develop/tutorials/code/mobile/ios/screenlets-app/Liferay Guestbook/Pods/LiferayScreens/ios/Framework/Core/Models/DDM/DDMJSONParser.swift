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

public typealias JSONObject = [String:AnyObject]
public typealias JSONArray = [AnyObject]

open class DDMJSONParser {

	// MARK: Public methods

	open func parse(_ json: String, locale: Locale) -> [DDMField]? {
		guard let data = json.data(using: String.Encoding.utf8) else {
			return nil
		}

		guard let object = try? JSONSerialization.jsonObject(with: data, options: []) else {
			return nil
		}

		guard let jsonObject = object as? JSONObject else {
			return nil
		}

		return processDocument(jsonObject, locale: locale)
	}

	// MARK: Private methods

	fileprivate func processDocument(_ json: JSONObject, locale: Locale) -> [DDMField]? {
		guard let fields = json["fields"] as? [JSONObject] else {
			return []
		}

		let defaultLocaleId = (json["defaultLanguageId"] as? String) ?? "en_US"
		let defaultLocale = Locale(identifier: defaultLocaleId)

		var result = [DDMField]()

		fields.forEach {
			let formField = createFormField($0,
					locale: locale,
					defaultLocale: defaultLocale)

			if let formField = formField {
				result.append(formField)
			}
		}

		return result
	}

	fileprivate func createFormField(_ jsonField: JSONObject,
			locale: Locale,
			defaultLocale: Locale)
			-> DDMField? {

		let dataType = DDMField.DataType.from(json: jsonField)

		let localizedMetadata = processLocalized(jsonField,
				locale: locale,
				defaultLocale: defaultLocale)

		let mergedAttributes = jsonField.copyAndMerge(localizedMetadata)

		return dataType.createField(
			attributes: mergedAttributes,
			locale: locale,
			version: LiferayServerVersion.v70)
	}

	fileprivate func processLocalized(_ jsonField: JSONObject,
			locale: Locale,
			defaultLocale: Locale)
			-> JSONObject {

		var result = JSONObject()

		for (key, value) in jsonField {
			if let innerObject = value as? JSONObject {
				if let localeFound = findLocale(innerObject,
						locale: locale, defaultLocale: defaultLocale) {
					result[key] = innerObject[localeFound]
				}
				else {
					result[key] = innerObject as AnyObject?
				}
			}
			else if let innerArray = value as? [JSONObject] {

				result[key] = innerArray.map {
					processLocalized($0,
						locale: locale, defaultLocale: defaultLocale)
				} as AnyObject
			}
			else {
				result[key] = value
			}
		}

		return result
	}

	fileprivate func findLocale(_ localizedObject: JSONObject,
			locale: Locale, defaultLocale: Locale)
			-> String? {

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

		let localeId = locale.identifier
		let defaultLocaleId = defaultLocale.identifier
		let currentLanguageCode = (locale as NSLocale).object(forKey: NSLocale.Key.languageCode) as! String
		let currentCountryCode = (locale as NSLocale).object(forKey: NSLocale.Key.countryCode) as? String

		// cases 'a1' and 'b1'
		func findFullMatch() -> String? {
			return localizedObject.filter { entry in
					entry.0 == localeId
				}
				.first?
				.0
		}

		// cases 'a2'
		func findLanguageOnlyMatch() -> String? {
			return localizedObject.filter { entry in
					entry.0 == currentLanguageCode
				}
				.first?
				.0
		}

		// Pre-final fallback (a3, b2): match language with any country
		func findLanguageAndAnyCountryMatch() -> String? {
			return localizedObject.filter { entry in
					let entryLocale = Locale(identifier: entry.0)
					let entryLanguage = (entryLocale as NSLocale).object(forKey: NSLocale.Key.languageCode) as! String
					return entryLanguage == currentLanguageCode
				}
				.first?
				.0
		}

		// Final fallback (a4, b3): find default metadata
		func findDefaultLanguageMatch() -> String? {
			return localizedObject.filter { entry in
					entry.0 == defaultLocaleId
				}
				.first?
				.0
		}

		// cases 'a1' and 'b1'
		if let match = findFullMatch() {
			return match
		}

		// case 'a2'
		if let match = findLanguageOnlyMatch() {
			return match
		}

		// Pre-final fallback (a3, b2): match language with any country
		if let match = findLanguageAndAnyCountryMatch() {
			return match
		}

		// Final fallback (a4, b3): find default metadata
		if let match = findDefaultLanguageMatch() {
			return match
		}

		// Default language isn't found: return the first one
		return localizedObject.first?.0
	}

}
