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
import UIKit

open class WebContentLoadBaseLiferayConnector: ServerConnector {

	open let groupId: Int64
	open let articleId: String

	// MARK: Initializers

	public init(groupId: Int64, articleId: String) {
		self.groupId = groupId
		self.articleId = articleId

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if groupId == 0 {
				return ValidationError("webcontentdisplay-screenlet", "undefined-group")
			}
		}

		return error
	}

}
