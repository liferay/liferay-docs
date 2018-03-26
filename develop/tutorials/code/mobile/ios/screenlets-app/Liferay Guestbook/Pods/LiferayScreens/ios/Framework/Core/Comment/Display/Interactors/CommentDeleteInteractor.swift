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

open class CommentDeleteInteractor: ServerWriteConnectorInteractor {

	let commentId: Int64

	// MARK: Initializers

	override public init(screenlet: BaseScreenlet?) {
		self.commentId = (screenlet as! CommentDisplayScreenlet).commentId

		super.init(screenlet: screenlet)
	}

	public init(commentId: Int64) {
		self.commentId = commentId

		super.init(screenlet: nil)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> CommentDeleteLiferayConnector? {
		return LiferayServerContext.connectorFactory.createCommentDeleteConnector(
			commentId: commentId)
	}

	// MARK: Cache methods

	override open func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
			? cacheManager.setDirty
			: cacheManager.setClean

		cacheFunction(
			"CommentsScreenlet",
			"delete-commentId-\(commentId)",
			"" as NSCoding,
			["commentId": NSNumber(value: commentId)],
			nil)
	}

	// MARK: Interactor

	override open func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			SessionContext.currentContext?.cacheManager.setClean(
				collection: "CommentsScreenlet",
				key: "delete-commentId-\(commentId)",
				value: "" as NSCoding,
				attributes: ["commentId": NSNumber(value: commentId)],
				onCompletion: nil)
		}

		super.callOnSuccess()
	}

}
