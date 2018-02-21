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

open class CommentUpdateLiferayConnector: ServerConnector {

	open let commentId: Int64
	open let body: String

	open var resultComment: Comment?

	// MARK: Initializers

	public init(commentId: Int64, body: String) {
		self.commentId = commentId
		self.body = body

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {

			if commentId <= 0 {
				return ValidationError("comment-display-screenlet", "undefined-commentId")
			}

			if body.isEmpty {
				return ValidationError("comment-display-screenlet", "undefined-body")
			}
		}

		return error
	}

}

open class Liferay70CommentUpdateConnector: CommentUpdateLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		resultComment = nil

		let service = LRScreenscommentService_v70(session: session)

		do {
			let result = try service?.updateComment(withCommentId: commentId,
					body: Comment.plainBodyToHtml(body))

			if let result = result as? [String: AnyObject] {
				resultComment = Comment(attributes: result)
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "Could not update comment with this commentId.")
				resultComment = nil
			}
		}
		catch let error as NSError {
			lastError = error
			resultComment = nil
		}
	}

}
