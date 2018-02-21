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

open class CommentUpdateInteractor: ServerWriteConnectorInteractor {

	let commentId: Int64

	let body: String

	open var resultComment: Comment?

	// MARK: Initializers

	init(screenlet: CommentDisplayScreenlet, body: String) {
		self.commentId = screenlet.commentId
		self.body = body

		super.init(screenlet: screenlet)
	}

	init(commentId: Int64, body: String) {
		self.commentId = commentId
		self.body = body

		super.init(screenlet: nil)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> CommentUpdateLiferayConnector? {
		return LiferayServerContext.connectorFactory.createCommentUpdateConnector(
				commentId: commentId,
				body: body)
	}

	open override func completedConnector(_ c: ServerConnector) {
		if let updateCon = (c as? CommentUpdateLiferayConnector),
				let comment = updateCon.resultComment {
			self.resultComment = comment
		}
	}

	// MARK: Cache methods

	override open func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}
		guard let updateCon = c as? CommentUpdateLiferayConnector else {
			return
		}

		let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
			? cacheManager.setDirty
			: cacheManager.setClean

		cacheFunction(
			"CommentsScreenlet",
			"update-commentId-\(updateCon.commentId)",
			"" as NSCoding,
			[
				"commentId": NSNumber(value: updateCon.commentId),
				"body": updateCon.body as AnyObject
			],
			nil)
	}

	// MARK: Interactor

	override open func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			SessionContext.currentContext?.cacheManager.setClean(
				collection: "CommentsScreenlet",
				key: "update-commentId-\(commentId)",
				value: "" as NSCoding,
				attributes: [
					"commentId": NSNumber(value: commentId),
					"body": body as AnyObject
				],
				onCompletion: nil)
		}

		super.callOnSuccess()
	}

}
