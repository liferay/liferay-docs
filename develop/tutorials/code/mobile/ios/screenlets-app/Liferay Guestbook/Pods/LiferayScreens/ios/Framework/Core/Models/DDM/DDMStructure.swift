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

//TODO: Unit test

@objc(DDMStructure)
open class DDMStructure: NSObject, NSCoding {

	open var fields = [DDMField]()
	open let attributes: [String:AnyObject]
	open let locale: Locale

	open subscript(fieldName: String) -> DDMField? {
		return fieldBy(name: fieldName)
	}

	open override var debugDescription: String {
		return "DDMStructure[fields=\(fields)]"
	}

	// MARK: Initializers

	public init(fields: [DDMField], locale: Locale, attributes: [String:AnyObject]) {
		self.fields = fields
		self.locale = locale
		self.attributes = attributes

		super.init()
	}

	public convenience init?(xsd: String, locale: Locale, attributes: [String:AnyObject] = [:]) {
		guard let parsedFields = DDMXSDParser().parse(xsd, locale: locale) else {
			return nil
		}
		guard !parsedFields.isEmpty else {
			return nil
		}

		self.init(fields: parsedFields, locale: locale, attributes: attributes)
	}

	public convenience init?(json: String, locale: Locale, attributes: [String:AnyObject] = [:]) {
		guard let parsedFields = DDMJSONParser().parse(json, locale: locale) else {
			return nil
		}
		guard !parsedFields.isEmpty else {
			return nil
		}

		self.init(fields: parsedFields, locale: locale, attributes: attributes)
	}

	public convenience init?(structureData: [String:AnyObject], locale: Locale) {
		var newData = structureData

		if let xsd = structureData["xsd"] as? String {
			// v6.2: xml based structure
			newData.removeValue(forKey: "xsd")
			self.init(xsd: xsd, locale: locale, attributes: newData)
		}
		else if let json = structureData["definition"] as? String {
			// v7.0+: json based structure
			newData.removeValue(forKey: "definition")
			self.init(json: json, locale: locale, attributes: newData)
		}
		else {
			return nil
		}
	}

	public required init?(coder aDecoder: NSCoder) {
		fields = aDecoder.decodeObject(forKey: "fields") as! [DDMField]
		attributes = aDecoder.decodeObject(forKey: "attributes") as! [String:AnyObject]
		locale = aDecoder.decodeObject(forKey: "locale") as! Locale

		super.init()
	}

	// MARK: Public methods

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(fields, forKey:"fields")
		aCoder.encode(attributes, forKey:"attributes")
		aCoder.encode(locale, forKey:"locale")
	}

	open func fieldBy(name: String) -> DDMField? {
		return fields.filter {
			$0.name.lowercased() == name.lowercased()
		}.first
	}

	open func fieldsBy(type: AnyClass) -> [DDMField] {
		let typeName = NSStringFromClass(type)

		return fields.filter {
			NSStringFromClass(type(of: $0)) == typeName
		}
	}

}
