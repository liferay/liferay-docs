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

@objc(DDMFieldDate)
open class DDMFieldDate: DDMField {

	static let GMTTimeZone = TimeZone(abbreviation: "GMT")

	open var clientDateFormatter: DateFormatter {
		let result = DateFormatter()
		result.dateStyle = .long
		result.timeStyle = .none
		result.locale = currentLocale
		return result
	}

	open var serverDateFormat: String {
		return "MM/dd/yyyy"
	}

	open func formatterWithFormat(_ format: String) -> DateFormatter {
		let formatter = DateFormatter()

		formatter.timeZone = DDMFieldDate.GMTTimeZone
		formatter.dateFormat = format

		return formatter
	}

	// MARK: DDMField

	override fileprivate init(attributes: [String:AnyObject], locale: Locale) {
		super.init(attributes: attributes, locale: locale)
	}

	public required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	override internal func convert(fromString value: String?) -> AnyObject? {
		func convertFromDateStr(_ str: String) -> Date? {
			let separator = str[str.characters.index(str.endIndex, offsetBy: -3)]
			let format = separator == "/" ? "MM/dd/yy" : serverDateFormat

			return formatterWithFormat(format).date(from: str)
		}

		func convertFromJavaEpoch(_ str: String) -> Date? {
			guard let epoch = Double(str) else {
				return nil
			}

			return Date(timeIntervalSince1970: epoch/1000)
		}

		guard let stringValue = value else {
			return nil
		}
		guard !stringValue.isEmpty else {
			return nil
		}

		return convertFromDateStr(stringValue) as AnyObject?? ?? convertFromJavaEpoch(stringValue) as AnyObject?
	}

	override func convert(fromLabel label: String?) -> AnyObject? {
		guard let label = label else {
			return nil
		}
		guard !label.isEmpty else {
			return nil
		}

		return clientDateFormatter.date(from: label) as AnyObject?
	}

	override func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		guard let date = currentValue as? Date else {
			return nil
		}

		return clientDateFormatter.string(from: date)
	}

}

open class DDMFieldDate_v62: DDMFieldDate {

	override public init(attributes: [String:AnyObject], locale: Locale) {
		super.init(attributes: attributes, locale: locale)
	}

	public required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		guard let date = value as? Date else {
			return nil
		}

		// Java uses milliseconds instead of seconds
		let epoch = Int64(date.timeIntervalSince1970 * 1000)

		return "\(epoch)"
	}

}

open class DDMFieldDate_v70: DDMFieldDate {

	override public init(attributes: [String:AnyObject], locale: Locale) {
		super.init(attributes: attributes, locale: locale)
	}

	public required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	override open var serverDateFormat: String {
		return "yyyy'-'MM'-'dd"
	}

	override internal func convert(fromString value: String?) -> AnyObject? {
		guard let stringValue = value else {
			return nil
		}

		// Liferay 7 is not consistent in date format.
		// It uses MM/dd/YYYY in predefinedValue field.

		return super.convert(fromString: value)
			?? formatterWithFormat("M'/'d'/'yyyy").date(from: stringValue) as AnyObject?
	}

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		guard let date = value as? Date else {
			return nil
		}

		return formatterWithFormat(serverDateFormat).string(from: date)
	}

}
