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

open class ImageGalleryDeleteInteractor: ServerWriteConnectorInteractor {

	let imageEntryId: Int64

	let repositoryId: Int64

	let folderId: Int64

	let page: Int

	public init(
			screenlet: BaseScreenlet?,
			imageEntryId: Int64,
			repositoryId: Int64,
			folderId: Int64,
			page: Int) {

		self.imageEntryId = imageEntryId
		self.page = page
		self.repositoryId = repositoryId
		self.folderId = folderId

		super.init(screenlet: screenlet)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> ServerConnector? {
		return LiferayServerContext.connectorFactory.createImageGalleryDeleteConnector(imageEntryId)
	}

	// MARK: Cache methods

	override open func writeToCache(_ c: ServerConnector) {
		let cacheKey = "\(ImageGalleryLoadInteractor.CacheKey)-\(repositoryId)-\(folderId)"

		SessionContext.currentContext?.cacheManager.remove(
			collection: ScreenletName(ImageGalleryScreenlet.self),
			key: "\(cacheKey)-\(page)")

	}
}
