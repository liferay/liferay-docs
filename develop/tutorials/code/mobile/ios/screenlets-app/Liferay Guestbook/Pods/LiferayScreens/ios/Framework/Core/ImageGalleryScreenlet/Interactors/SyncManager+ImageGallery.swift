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

	func imageGallerySynchronizer(
			_ key: String,
			attributes: [String : AnyObject])
			-> (@escaping Signal) -> Void {

		return { signal in
			let folderId = attributes["folderId"]!.int64Value
			let repositoryId = attributes["repositoryId"]!.int64Value
			let page = attributes["page"]!.description.asNumber!.intValue

			self.cacheManager.getAny(collection: ScreenletName(ImageGalleryScreenlet.self), key: key) {

				if let imageEntryUpload = $0 as? ImageEntryUpload {

					let interactor = ImageGalleryUploadInteractor(
							screenlet: nil,
							imageUpload: imageEntryUpload,
							repositoryId: repositoryId!,
							folderId: folderId!,
							page: page,
							onUploadedBytes: nil,
							cacheKeyUsed: key)

					self.prepareInteractorForSync(
							interactor, key: key,
							attributes: attributes,
							signal: signal,
							screenletClassName: ScreenletName(ImageGalleryScreenlet.self))

					if !interactor.start() {
						signal()
					}
				}
				else {

					self.delegate?.syncManager?(self,
							onItemSyncScreenlet: ScreenletName(ImageGalleryScreenlet.self),
							failedKey: key,
							attributes: attributes,
							error: NSError.errorWithCause(.notAvailable,
									message: "Synchronizer for image gallery not available."))

					signal()
				}

			}
		}
	}

}
