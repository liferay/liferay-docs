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

open class CommentLoadLiferayConnector: ServerConnector {

	open let commentId: Int64

	open var resultComment: Comment?

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

open class Liferay70CommentLoadConnector: CommentLoadLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		resultComment = nil

		let service = LRScreenscommentService_v70(session: session)

		do {
			let result = try service?.getCommentWithCommentId(commentId)

			if let result = result as? [String: AnyObject] {
				resultComment = Comment(attributes: result)
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not load comment with this commentId.")
				resultComment = nil
			}
		}
		catch let error as NSError {
			lastError = error
			resultComment = nil
		}
	}

}
