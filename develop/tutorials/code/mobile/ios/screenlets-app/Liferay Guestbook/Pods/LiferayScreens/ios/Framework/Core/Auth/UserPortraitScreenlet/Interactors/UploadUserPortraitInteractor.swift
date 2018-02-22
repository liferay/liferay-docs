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

class UploadUserPortraitInteractor: ServerWriteConnectorInteractor {

	var uploadResult: [String:AnyObject]?

	let userId: Int64

	let image: UIImage

	// MARK: Initializers

	init(screenlet: BaseScreenlet?, userId: Int64, image: UIImage) {
		self.userId = userId
		self.image = image

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> UploadUserPortraitLiferayConnector {
		return LiferayServerContext.connectorFactory.createUploadUserPortraitConnector(
				userId: self.userId,
				image: self.image)
	}

	override func completedConnector(_ c: ServerConnector) {
		self.uploadResult = (c as! UploadUserPortraitLiferayConnector).uploadResult
	}

	// MARK: Cache methods

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
			? cacheManager.setDirty
			: cacheManager.setClean

		cacheFunction(
			ScreenletName(UserPortraitScreenlet.self),
			"userId-\(userId)",
			image,
			["userId": userId.description as AnyObject],
			nil)
	}

	// MARK: Interactor

	override func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			// update cache with date sent
			SessionContext.currentContext?.cacheManager.setClean(
				collection: ScreenletName(UserPortraitScreenlet.self),
				key: "userId-\(userId)",
				attributes: [
					"userId": userId.description as AnyObject])
		}

		super.callOnSuccess()
	}

}
