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

@objc(DDLRecord)
open class DDLRecord: NSObject, NSCoding {

	open var structure: DDMStructure?
	open let untypedValues: [DDMField]?

	open var attributes: [String:AnyObject] = [:]

	open var recordId: Int64? {
		get {
			return attributes["recordId"]?.int64Value
		}
		set {
			if let newValue = newValue {
				attributes["recordId"] = NSNumber(value: newValue as Int64)
			}
			else {
				attributes.removeValue(forKey: "recordId")
			}
		}
	}

	open var fields: [DDMField] {
		return structure?.fields ?? untypedValues ?? []
	}

	open subscript(fieldName: String) -> DDMField? {
		return fieldBy(name: fieldName)
	}

	open var values: [String:AnyObject] {
		var result = [String: AnyObject]()

		for field in self.fields {
			if let value = field.currentValueAsString {
				//FIXME - LPS-49460
				// Server rejects the request if the value is empty string.
				// This way we workaround the problem but a field can't be
				// emptied when you're editing an existing row.
				if value != "" {
					result[field.name] = value as AnyObject?
				}
			}
		}

		return result
	}

	// MARK: Initializers

	public init(structure: DDMStructure) {
		self.structure = structure
		untypedValues = nil

		super.init()
	}

	public init(xsd: String, locale: Locale) {
		structure = DDMStructure(xsd: xsd, locale: locale)
		untypedValues = nil

		super.init()
	}

	public init(json: String, locale: Locale) {
		structure = DDMStructure(json: json, locale: locale)
		untypedValues = nil

		super.init()
	}

	public init(data: [String:AnyObject], attributes: [String:AnyObject]) {
		structure = nil

		let parsedFields = DDLUntypedValuesParser().parse(data)
		if parsedFields.isEmpty {
			untypedValues = nil
		}
		else {
			untypedValues = parsedFields
		}

		self.attributes = attributes

		super.init()
	}

	public init(dataAndAttributes: [String:AnyObject]) {
		structure = nil

		if let recordFields = dataAndAttributes["modelValues"] as? [String:AnyObject] {
			let parsedFields = DDLUntypedValuesParser().parse(recordFields)
		 	if parsedFields.isEmpty {
				untypedValues = nil
			}
			else {
				untypedValues = parsedFields
			}
		}
		else {
			untypedValues = nil
		}

		if let recordAttributes = dataAndAttributes["modelAttributes"] as? [String:AnyObject] {
			attributes = recordAttributes
		}

		super.init()
	}

	public required init?(coder aDecoder: NSCoder) {
		structure = aDecoder.decodeObject(forKey: "structure") as? DDMStructure
		untypedValues = aDecoder.decodeObject(forKey: "untypedValues") as? [DDMField]
		attributes = aDecoder.decodeObject(forKey: "attributes") as! [String:AnyObject]

		super.init()
	}

	// MARK: Public methods

	open func encode(with aCoder: NSCoder) {
		if let structure = structure {
			aCoder.encode(structure, forKey: "structure")
		}
		if let untypedValues = untypedValues {
			aCoder.encode(untypedValues, forKey: "untypedValues")
		}
		aCoder.encode(attributes, forKey:"attributes")
	}

	open func fieldBy(name: String) -> DDMField? {
		return structure?.fieldBy(name: name)
					??
				untypedValues?.filter {
					$0.name.lowercased() == name.lowercased()
				}.first
	}

	open func fieldsBy(type: AnyClass) -> [DDMField] {
		let typeName = NSStringFromClass(type)

		return structure?.fieldsBy(type: type)
					??
				untypedValues?.filter {
					NSStringFromClass(type(of: $0)) == typeName
				}
					??
				[]
	}

	open func updateCurrentValues(values: [String:AnyObject]) {
		self.fields.forEach {
			if let fieldValue = values[$0.name] {
				if let fieldStringValue = fieldValue as? String {
					$0.currentValueAsString = fieldStringValue
				}
				else {
					$0.currentValue = fieldValue
				}
			}
		}
	}

	@discardableResult
	open func updateCurrentValues(xmlValues: String) -> Int {
		let parser = DDMTypedValuesXMLParser()

		let count = parser.parse(xmlValues, structure: self.structure)

		if let createdStructure = parser.createdStructure, self.structure == nil {
			self.structure = createdStructure
		}

		return count
	}

	open func clearValues() {
		self.fields.forEach {
			$0.currentValue = $0.predefinedValue
		}
	}

}
