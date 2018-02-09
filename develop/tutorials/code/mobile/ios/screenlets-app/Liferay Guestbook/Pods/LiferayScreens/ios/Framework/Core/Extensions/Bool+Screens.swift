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

extension Bool {

	public static func from(string value: String) -> Bool {
		let boolValue = value.lowercased().trimmingCharacters(
				in: .whitespaces)

		return (boolValue == "true" || boolValue == "1")
	}

	public static func from(any value: AnyObject) -> Bool {
		if let stringValue = value as? String {
			return Bool.from(string: stringValue)
		}
		else if let numberValue = value as? NSNumber {
			return numberValue.boolValue
		}

		return false
	}

}
