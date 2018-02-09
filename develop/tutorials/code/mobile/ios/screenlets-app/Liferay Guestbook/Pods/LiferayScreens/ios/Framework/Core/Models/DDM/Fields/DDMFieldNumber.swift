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

@objc(DDMFieldNumber)
open class DDMFieldNumber: DDMField {

	open var maximumDecimalDigits = 2
	open var minimumDecimalDigits = 2

	open var isDecimal: Bool {
		return dataType != DataType.DDMInteger
	}

	// MARK: DDMField

	override internal func convert(fromString value: String?) -> AnyObject? {
		if let value = value {
			// server may return the number is one format that uses , as decimal separator
			let number = value.replacingOccurrences(of: ",", with: ".").trim()

			if !isDecimal {
				return Int(round(Double(number) ?? 0)) as AnyObject?
			}

			let enFormatter = NumberFormatter()
			enFormatter.locale = Locale(identifier: "en_US")

			if let result = enFormatter.number(from: number) {
				switch CFNumberGetType(result as CFNumber) {
				case .float32Type, .float64Type, .floatType, .cgFloatType:
					return NSDecimalNumber(value: result.floatValue as Float)
				case .doubleType:
					return NSDecimalNumber(value: result.doubleValue as Double)
				default:
					return NSInteger(result.intValue) as AnyObject?
				}
			}
		}

		return nil
	}

	override internal func convert(fromLabel label: String?) -> AnyObject? {
		if label != nil {
			let formatter = NumberFormatter()

			formatter.locale = self.currentLocale

			if isDecimal {
				formatter.numberStyle = .decimal
				formatter.roundingMode = .halfUp
				formatter.maximumFractionDigits = maximumDecimalDigits
				formatter.minimumFractionDigits = minimumDecimalDigits
			}

			return formatter.number(from: label!)
		}

		return nil
	}

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		return formatNumber(value as? NSNumber,
				locale: Locale(identifier: "en_US"))
	}

	override internal func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		return formatNumber(value as? NSNumber, locale: self.currentLocale)
	}

	override internal func onChangedCurrentValue() {
		if !isDecimal && currentValue is NSInteger == false {
			let decimal = (currentValue as! NSNumber).doubleValue
			currentValue = NSNumber(value: decimal + 0.5 as Double).intValue as AnyObject?
		}
	}

	// MARK: Private methods

	fileprivate func formatNumber(_ number: NSNumber?, locale: Locale) -> String? {
		if number == nil {
			return nil
		}

		let formatter = NumberFormatter()

		formatter.locale = locale

		if isDecimal {
			formatter.numberStyle = .decimal
			formatter.roundingMode = .halfUp
			formatter.maximumFractionDigits = maximumDecimalDigits
			formatter.minimumFractionDigits = minimumDecimalDigits
		}

		return formatter.string(from: number!)
	}

}
