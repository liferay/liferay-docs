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

	func userPortraitSynchronizer(
			_ key: String,
			attributes: [String:AnyObject])
			-> (@escaping Signal) -> Void {

		return { signal in
			let userId = attributes["userId"]!.int64Value

			self.cacheManager.getImage(
					collection: ScreenletName(UserPortraitScreenlet.self),
					key: key) {

				if let image = $0 {
					let interactor = UploadUserPortraitInteractor(
						screenlet: nil,
						userId: userId!,
						image: image)

					self.prepareInteractorForSync(interactor,
						key: key,
						attributes: attributes,
						signal: signal,
						screenletClassName: ScreenletName(UserPortraitScreenlet.self))

					if !interactor.start() {
						signal()
					}
				}
				else {
					self.delegate?.syncManager?(self,
						onItemSyncScreenlet: ScreenletName(UserPortraitScreenlet.self),
						failedKey: key,
						attributes: attributes,
						error: NSError.errorWithCause(.notAvailable,
								message: "Synchronizer for user portrait not available."))

					signal()
				}
			}
		}
	}

}
