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

open class UpdateRatingInteractor: ServerWriteConnectorInteractor {

	let className: String

	let classPK: Int64

	let score: Double

	let ratingsGroupCount: Int32

	var resultRating: RatingEntry?

	// MARK: Initializers

	init(className: String, classPK: Int64, score: Double?, ratingsGroupCount: Int32) {
		self.className = className
		self.classPK = classPK
		self.ratingsGroupCount = ratingsGroupCount
		self.score = score ?? -1

		super.init(screenlet: nil)
	}

	init(screenlet: BaseScreenlet?,
			className: String,
			classPK: Int64,
			score: Double?,
			ratingsGroupCount: Int32) {
		self.className = className
		self.classPK = classPK
		self.ratingsGroupCount = ratingsGroupCount
		self.score = score ?? -1

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> ServerConnector? {
		return LiferayServerContext.connectorFactory.createRatingUpdateConnector(
			classPK: classPK,
			className: className,
			score: score,
			ratingsGroupCount: ratingsGroupCount)
	}

	override open func completedConnector(_ c: ServerConnector) {
		if let updateCon = c as? RatingUpdateLiferayConnector {
			self.resultRating = updateCon.resultRating
		}
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
			"RatingsScreenlet",
			"update-className=\(className)-classPK=\(classPK)",
			"" as NSCoding,
			[
				"className": className as AnyObject,
				"classPK": NSNumber(value: classPK as Int64),
				"ratingsGroupCount": Int(ratingsGroupCount) as AnyObject,
				"score": NSNumber(value: score as Double)
			],
			nil)
	}

	// MARK: Interactor

	override open func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			SessionContext.currentContext?.cacheManager.setClean(
				collection: "RatingsScreenlet",
				key: "update-className=\(className)-classPK=\(classPK)",
				value: "" as NSCoding,
				attributes: [
					"className": className as AnyObject,
					"classPK": NSNumber(value: classPK as Int64),
					"ratingsGroupCount": Int(ratingsGroupCount) as AnyObject,
					"score": NSNumber(value: score as Double)
				],
				onCompletion: nil)
		}

		super.callOnSuccess()
	}

}
