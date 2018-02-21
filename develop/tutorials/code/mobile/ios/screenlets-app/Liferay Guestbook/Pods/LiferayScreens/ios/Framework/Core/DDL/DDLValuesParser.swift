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

//TODO unit tests

open class DDLUntypedValuesParser {

	public init() {
	}

	open func parse(_ values: [String:AnyObject]) -> [DDMField] {
		var result: [DDMField] = []

		let locale = Locale(identifier: NSLocale.currentLocaleString)

		for (fieldName, fieldValue) in values {
			let field = DDMFieldUntyped(
					name: fieldName,
					value: fieldValue,
					locale: locale)

			result.append(field)
		}

		return result
	}

}
