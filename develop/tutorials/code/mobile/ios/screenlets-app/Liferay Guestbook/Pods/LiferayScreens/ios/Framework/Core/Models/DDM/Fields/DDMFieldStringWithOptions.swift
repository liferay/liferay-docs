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

@objc(DDMFieldStringWithOptions)
open class DDMFieldStringWithOptions: DDMField {

	open class Option: NSObject, NSCoding {

		open var label: String
		open var name: String?
		open var value: String

		public init(label: String, name: String?, value: String) {
			self.label = label
			self.name = name
			self.value = value
		}

		public required convenience init?(coder aDecoder: NSCoder) {
			let label = aDecoder.decodeObject(forKey: "label") as! String
			let name = aDecoder.decodeObject(forKey: "name") as? String
			let value = aDecoder.decodeObject(forKey: "value") as! String

			self.init(label: label, name: name, value: value)
		}

		open func encode(with aCoder: NSCoder) {
			aCoder.encode(label, forKey: "label")
			if let name = name {
				aCoder.encode(name, forKey: "name")
			}
			aCoder.encode(value, forKey: "value")
		}

		override open var description: String {
			return self.value
		}

	}

	//FIXME: Multiple selection not supported yet
	fileprivate(set) var multiple: Bool

	fileprivate(set) var options: [Option] = []

	override public init(attributes: [String:AnyObject], locale: Locale) {
		multiple = Bool.from(any: attributes["multiple"] ?? "false" as AnyObject)

		if let optionsArray = attributes["options"] as? [[String:AnyObject]] {
			for optionDict in optionsArray {
				let label = optionDict["label"] as? String ?? ""
				let name = optionDict["name"] as? String
				let value = optionDict["value"] as? String ?? ""

				let option = Option(label: label, name: name, value: value)

				self.options.append(option)
			}
		}

		super.init(attributes: attributes, locale: locale)
	}

	public required init?(coder aDecoder: NSCoder) {
		multiple = aDecoder.decodeBool(forKey: "multiple")
		options = aDecoder.decodeObject(forKey: "options") as! [Option]

		super.init(coder: aDecoder)
	}

	open override func encode(with aCoder: NSCoder) {
		super.encode(with: aCoder)

		aCoder.encode(multiple, forKey: "multiple")
		aCoder.encode(options, forKey: "options")
	}

	// MARK: DDMField

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		var result = "["

		if let currentOptions = value as? [NSObject] {
			var first = true
			for option in currentOptions {
				if first {
					first = false
				}
				else {
					result += ", "
				}

				result += "\"\(option.description)\""
			}
		}

		return result + "]"
	}

	override internal func convert(fromString value: String?) -> AnyObject? {
		var result = [Option]()

		let optionsValue = extractOptions(value)

		for option in optionsValue {
			if let foundOption = findOptionByLabel(option) {
				result.append(foundOption)
			}
			else if let foundOption = findOptionByValue(option) {
				result.append(foundOption)
			}
		}

		return result as AnyObject?
	}

	override func convert(fromLabel labels: String?) -> AnyObject? {
		var options =  [Option]()

		let optionsString = extractOptions(labels)

		for option in optionsString {
			if let foundOption = findOptionByLabel(option) {
				options.append(foundOption)
			}
		}

		return options as AnyObject?
	}

	override func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		if let currentOptions = currentValue as? [Option] {
			if let firstOption = currentOptions.first {
				return firstOption.label
			}
		}

		return ""
	}

	override internal func doValidate() -> Bool {
		let current = (currentValue as! [Option]?) ?? []

		return !(required && current.isEmpty)
	}

	override internal func onChangedCurrentValue() {
		if !(currentValue is [Option]) {
			if let currentValueAsString = currentValue as? String {
				currentValue = convert(fromString: currentValueAsString)
			}
		}
	}

	// MARK: Private methods

	fileprivate func extractOptions(_ optionsString: String?) -> [String] {
		var options = [String]()

		if let optionsValue = optionsString {
			var parsedOptions = optionsValue
			if optionsValue.hasPrefix("[") {
				parsedOptions = parsedOptions.removeFirstAndLastChars()
			}

			options = parsedOptions.components(separatedBy: ",")
					.map { $0.trim() }
					.map (extractOption)
		}

		return options
	}

	fileprivate func extractOption(_ option: String) -> String {
		if option.hasPrefix("\"") {
			return option.removeFirstAndLastChars()
		}

		return option
	}

	fileprivate func findOptionByValue(_ value: String) -> Option? {
		return options.filter {
				$0.value == value
			}.first
	}

	fileprivate func findOptionByLabel(_ label: String) -> Option? {
		return options.filter {
				$0.label == label
			}.first
	}

}
