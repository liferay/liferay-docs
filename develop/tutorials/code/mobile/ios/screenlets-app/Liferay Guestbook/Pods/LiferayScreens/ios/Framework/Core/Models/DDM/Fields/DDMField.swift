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

@objc(DDMField)
open class DDMField: NSObject, NSCoding {

	open var onPostValidation: ((Bool) -> Void)?
	open var lastValidationResult: Bool?

	open var currentValue: AnyObject? {
		didSet {
			onChangedCurrentValue()
		}
	}

	open var currentValueAsString: String? {
		get {
			return convert(fromCurrentValue: currentValue)
		}
		set {
			currentValue = convert(fromString: newValue)
		}
	}

	open var currentValueAsLabel: String? {
		get {
			return convertToLabel(fromCurrentValue: currentValue)
		}
		set {
			currentValue = convert(fromLabel: newValue)
		}
	}

	open override var debugDescription: String {
		let currentValue = self.currentValueAsString
		var str = "DDMField[" +
			" name=\( self.name )" +
			" type=\( self.dataType.rawValue )" +
		" editor=\( self.editorType.rawValue )"
		if currentValue != nil {
			if currentValue! == "" {
				str += " value='' ]"
			}
			else {
				str += " value=\( currentValue! ) ]"
			}
		}
		else {
			str += " ]"
		}

		return str
	}

	open override var description: String {
		return currentValueAsLabel ?? super.description
	}

	open var currentLocale: Locale

	internal(set) var dataType: DataType
	internal(set) var editorType: Editor

	internal(set) var name: String
	internal(set) var label: String
	internal(set) var tip: String

	internal(set) var predefinedValue: AnyObject?

	internal(set) var readOnly: Bool
	internal(set) var repeatable: Bool
	internal(set) var required: Bool
	internal(set) var showLabel: Bool

	public init(attributes: [String:AnyObject], locale: Locale) {
		dataType = DataType(rawValue: valueAsString(attributes, key:"dataType")) ?? .Unsupported
		editorType = Editor.from(attributes: attributes)

		name = valueAsString(attributes, key:"name")
		label = valueAsString(attributes, key:"label")
		tip = valueAsString(attributes, key:"tip")

		readOnly = Bool.from(any: attributes["readOnly"] ?? "false" as AnyObject)
		repeatable = Bool.from(any: attributes["repeatable"] ?? "false" as AnyObject)
		required = Bool.from(any: attributes["required"] ?? "true" as AnyObject)
		showLabel = Bool.from(any: attributes["showLabel"] ?? "false" as AnyObject)

		currentLocale = locale

		super.init()

		if dataType == .Unsupported && editorType != .Unsupported {
			dataType = editorType.defaultDataType
		}

		predefinedValue = attributes["predefinedValue"]
		if let predefinedValueStr = predefinedValue as? String {
			predefinedValue = convert(fromString: predefinedValueStr)
		}
		else {
			let predefinedValueStr = convert(fromCurrentValue: predefinedValue)
			predefinedValue = convert(fromString: predefinedValueStr)
		}

		currentValue = predefinedValue
	}

	public required init?(coder aDecoder: NSCoder) {
		let dataTypeValue = aDecoder.decodeObject(forKey: "dataType") as? String
		dataType = DataType(rawValue: dataTypeValue ?? "") ?? .Unsupported

		let editorTypeValue = aDecoder.decodeObject(forKey: "editorType") as? String
		editorType = Editor(rawValue: editorTypeValue ?? "") ?? .Unsupported

		name = aDecoder.decodeObject(forKey: "name") as! String
		label = aDecoder.decodeObject(forKey: "label") as! String
		tip = aDecoder.decodeObject(forKey: "tip") as! String

		readOnly = aDecoder.decodeBool(forKey: "readOnly")
		repeatable = aDecoder.decodeBool(forKey: "repeatable")
		required = aDecoder.decodeBool(forKey: "required")
		showLabel = aDecoder.decodeBool(forKey: "showLabel")

		currentLocale = aDecoder.decodeObject(forKey: "currentLocale") as! Locale

		super.init()

		let predefinedValueString = aDecoder.decodeObject(forKey: "predefinedValue") as? String
		predefinedValue = convert(fromString: predefinedValueString)

		let currentValueString = aDecoder.decodeObject(forKey: "currentValue") as? String
		currentValue = convert(fromString: currentValueString)
	}

	open func encode(with aCoder: NSCoder) {
		aCoder.encode(currentLocale, forKey:"currentLocale")
		aCoder.encode(dataType.rawValue, forKey:"dataType")
		aCoder.encode(editorType.rawValue, forKey:"editorType")
		aCoder.encode(name, forKey:"name")
		aCoder.encode(label, forKey:"label")
		aCoder.encode(tip, forKey:"tip")
		aCoder.encode(readOnly, forKey:"readOnly")
		aCoder.encode(repeatable, forKey:"repeatable")
		aCoder.encode(required, forKey:"required")
		aCoder.encode(showLabel, forKey:"showLabel")
		aCoder.encode(convert(fromCurrentValue: currentValue), forKey:"currentValue")
		aCoder.encode(convert(fromCurrentValue: predefinedValue), forKey:"predefinedValue")
	}

	open func validate() -> Bool {
		var valid = !(currentValue == nil && required)

		if valid {
			valid = doValidate()
		}

		onPostValidation?(valid)

		lastValidationResult = valid

		return valid
	}

	// MARK: Internal methods

	internal func doValidate() -> Bool {
		return true
	}

	internal func convert(fromString value: String?) -> AnyObject? {
		return value as AnyObject?
	}

	internal func convert(fromLabel value: String?) -> AnyObject? {
		return value as AnyObject?
	}

	internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		if let value = value {
			return "\(value)"
		}

		return nil
	}

	internal func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		if let value = value {
			return "\(value)"
		}

		return nil
	}

	internal func onChangedCurrentValue() {
	}

}

// MARK: Equatable

public func == (left: DDMField, right: DDMField) -> Bool {
	return left.name == right.name
}

// MARK: Util func

private func valueAsString(_ dict: [String:AnyObject], key: String) -> String {
	return dict[key] as? String ?? ""
}
