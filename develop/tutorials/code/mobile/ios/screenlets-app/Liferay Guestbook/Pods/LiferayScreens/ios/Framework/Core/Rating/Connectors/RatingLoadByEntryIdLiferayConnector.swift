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
import LRMobileSDK

open class RatingLoadByEntryIdLiferayConnector: ServerConnector {

	open let entryId: Int64
	open let ratingsGroupCount: Int32

	open var resultRating: RatingEntry?

	// MARK: Initializers

	public init(entryId: Int64, ratingsGroupCount: Int32) {
		self.entryId = entryId
		self.ratingsGroupCount = ratingsGroupCount
		super.init()
	}

	// MARK: ServerConnector

	open override func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if entryId == 0 {
				return ValidationError("rating-screenlet", "undefined-entryId")
			}
			else if ratingsGroupCount < 1 {
				return ValidationError("rating-screenlet", "wrong-ratingsGroupCount")
			}
		}

		return error
	}

}

open class Liferay70RatingLoadByEntryIdConnector: RatingLoadByEntryIdLiferayConnector {

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		let service = LRScreensratingsentryService_v70(session: session)

		do {
			let result = try service?.getRatingsEntries(withAssetEntryId: entryId, ratingsLength: ratingsGroupCount)
			lastError = nil
			resultRating = RatingEntry(attributes: result as! [String: AnyObject])
		}
		catch let error as NSError {
			lastError = error
			resultRating = nil
		}
	}

}
