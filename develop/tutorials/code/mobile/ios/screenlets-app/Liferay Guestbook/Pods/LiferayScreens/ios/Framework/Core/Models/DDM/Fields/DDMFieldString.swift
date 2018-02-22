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

@objc(DDMFieldString)
open class DDMFieldString: DDMField {

	override internal func doValidate() -> Bool {
		var result = super.doValidate()

		if result && currentValueAsString != nil && required {
			let trimmedString = currentValueAsString?.trimmingCharacters(
					in: .whitespaces)

			result = (trimmedString != "")
		}

		return result
	}

}
