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

@objc(DDMFieldBoolean)
open class DDMFieldBoolean: DDMField {

	override internal func convert(fromString value: String?) -> AnyObject? {
		return value.map {
				Bool.from(string: $0) as AnyObject
			}
	}

	override func convert(fromLabel label: String?) -> AnyObject? {
		let trueLabel = LocalizedString("core", key: "yes", obj: self).lowercased()
		let falseLabel = LocalizedString("core", key: "no", obj: self).lowercased()

		if label?.lowercased() == trueLabel {
			return true as AnyObject?
		}
		else if label?.lowercased() == falseLabel {
			return false as AnyObject?
		}

		return nil
	}

	override internal func convert(fromCurrentValue value: AnyObject?) -> String? {
		guard let boolValue = value as? Bool else {
			return nil
		}

		return boolValue ? "true" : "false"
	}

	override func convertToLabel(fromCurrentValue value: AnyObject?) -> String? {
		guard let boolValue = value as? Bool else {
			return nil
		}

		return LocalizedString("core", key: boolValue ? "yes" : "no", obj: self)
	}

}
