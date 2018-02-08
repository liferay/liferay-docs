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

open class CommentAddInteractor: ServerWriteConnectorInteractor {

	let className: String

	let classPK: Int64

	let body: String

	var cacheKeyUsed: String?

	open var resultComment: Comment?

	// MARK: Initializers

	public init(screenlet: CommentAddScreenlet, body: String) {
		self.className = screenlet.className
		self.classPK = screenlet.classPK
		self.body = body

		super.init(screenlet: screenlet)
	}

	public init(
		className: String,
		classPK: Int64,
		body: String) {

		self.className = className
		self.classPK = classPK
		self.body = body

		super.init(screenlet: nil)
	}

	public convenience init(
		className: String,
		classPK: Int64,
		body: String,
		cacheKeyUsed: String) {

		self.init(
				className: className,
				classPK: classPK,
				body: body)

		self.cacheKeyUsed = cacheKeyUsed
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> CommentAddLiferayConnector? {
		return LiferayServerContext.connectorFactory.createCommentAddConnector(
				className: className,
				classPK: classPK,
				body: body)
	}

	override open func completedConnector(_ c: ServerConnector) {
		if let addCon = (c as? CommentAddLiferayConnector),
				let comment = addCon.resultComment {
			self.resultComment = comment
		}
	}

	// MARK: Cache methods

	override open func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}
		guard let addCon = c as? CommentAddLiferayConnector else {
			return
		}

		let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
			? cacheManager.setDirty
			: cacheManager.setClean

		let cacheKey: String

		if let cacheKeyUsed = cacheKeyUsed {
			cacheKey = cacheKeyUsed
		}
		else {
			cacheKey = "add-comment-\(UUID().uuidString)"
			cacheKeyUsed = cacheKey
		}

		cacheFunction(
			"CommentsScreenlet",
			cacheKey,
			"" as NSCoding,
			[
				"className": addCon.className as AnyObject,
				"classPK": NSNumber(value: addCon.classPK as Int64),
				"body": addCon.body as AnyObject
			],
			nil)
	}

	override open func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			SessionContext.currentContext?.cacheManager.setClean(
				collection: "CommentsScreenlet",
				key: cacheKeyUsed!,
				value: "" as NSCoding,
				attributes: [
					"className": className as AnyObject,
					"classPK": NSNumber(value: classPK as Int64),
					"body": body as AnyObject
				],
				onCompletion: nil)
		}

		super.callOnSuccess()
	}

}
