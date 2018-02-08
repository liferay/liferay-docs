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

open class CommentDeleteLiferayConnector: ServerConnector {

	open let commentId: Int64

	// MARK: Initializers

	public init(commentId: Int64) {
		self.commentId = commentId
		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if commentId <= 0 {
				return ValidationError("comment-display-screenlet", "undefined-commentId")
			}
		}

		return error
	}

}

open class Liferay70CommentDeleteConnector: CommentDeleteLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRCommentmanagerjsonwsService_v7(session: session)

		var error: NSError? = nil

		service?.deleteComment(withCommentId: commentId, error: &error)

		lastError = error
	}

}
