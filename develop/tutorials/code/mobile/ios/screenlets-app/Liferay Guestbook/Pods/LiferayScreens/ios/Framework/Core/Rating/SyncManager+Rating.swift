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

extension SyncManager {

	func ratingsSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {

		if key.hasPrefix("delete-") {
			return deleteRatingSynchronizer(key, attributes: attributes)
		}
		else if key.hasPrefix("update-") {
			return updateRatingSynchronizer(key, attributes: attributes)
		}

		return { _ in }
	}

	func deleteRatingSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {
		return { signal in
			let className = attributes["className"] as! String
			let classPK = (attributes["classPK"] as! NSNumber).int64Value
			let ratingsGroupCount = Int32(attributes["ratingsGroupCount"] as! Int)

			let interactor = DeleteRatingInteractor(
					className: className,
					classPK: classPK,
					ratingsGroupCount: ratingsGroupCount)

			self.prepareInteractorForSync(interactor,
					key: key,
					attributes: attributes,
					signal: signal,
					screenletClassName: "RatingsScreenlet")

			if !interactor.start() {
				self.delegate?.syncManager?(self,
						onItemSyncScreenlet: "RatingsScreenlet",
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for delete rating not available."))
				signal()
			}
		}
	}

	func updateRatingSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {
		return { signal in
			let className = attributes["className"] as! String
			let classPK = (attributes["classPK"] as! NSNumber).int64Value
			let ratingsGroupCount = Int32(attributes["ratingsGroupCount"] as! Int)
			let score = (attributes["score"] as! NSNumber).doubleValue

			let interactor = UpdateRatingInteractor(
					className: className,
					classPK: classPK,
					score: score,
					ratingsGroupCount: ratingsGroupCount)

			self.prepareInteractorForSync(interactor,
					key: key,
					attributes: attributes,
					signal: signal,
					screenletClassName: "RatingsScreenlet")

			if !interactor.start() {
				self.delegate?.syncManager?(self,
						onItemSyncScreenlet: "RatingsScreenlet",
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for update rating not available."))
				signal()
			}
		}
	}

}
