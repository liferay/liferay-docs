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

open class ImageGalleryUploadInteractor: ServerWriteConnectorInteractor {

	public typealias OnProgress = ImageGalleryUploadConnector.OnProgress

	let imageUpload: ImageEntryUpload

	let repositoryId: Int64

	let folderId: Int64

	let page: Int

	let onUploadedBytes: OnProgress?

	// Initialized only when created from synchronizer
	var cacheKeyUsed: String?

	var result: [String : AnyObject]?

	public init(
			screenlet: ImageGalleryScreenlet?,
			imageUpload: ImageEntryUpload,
			repositoryId: Int64,
			folderId: Int64,
			page: Int,
			onUploadedBytes: OnProgress? ) {

		self.imageUpload = imageUpload
		self.repositoryId = repositoryId
		self.folderId = folderId
		self.page = page
		self.onUploadedBytes = onUploadedBytes

		super.init(screenlet: screenlet)
	}

	public convenience init(
			screenlet: ImageGalleryScreenlet?,
			imageUpload: ImageEntryUpload,
			repositoryId: Int64,
			folderId: Int64,
			page: Int,
			onUploadedBytes: OnProgress?,
			cacheKeyUsed: String) {

		self.init(
				screenlet: screenlet,
				imageUpload: imageUpload,
				repositoryId: repositoryId,
				folderId: folderId,
				page: page,
				onUploadedBytes: onUploadedBytes)

		self.cacheKeyUsed = cacheKeyUsed
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> ServerConnector? {

		return LiferayServerContext.connectorFactory.createImageGalleryUploadConnector(
				repositoryId: repositoryId,
				folderId: folderId,
				sourceFileName: imageUpload.title,
				mimeType: "image/png",
				title: imageUpload.title,
				descrip: imageUpload.notes,
				changeLog: "",
				image: imageUpload.image,
				onUploadBytes:  onUploadedBytes)
	}

	override open func completedConnector(_ c: ServerConnector) {
		if let c = c as? ImageGalleryUploadConnector, let uploadResult = c.uploadResult {
			result = uploadResult
		}
		else if cacheStrategy == .cacheOnly {
			createModelFromUploadData()
		}
	}

	// MARK: Cache methods

	override open func writeToCache(_ c: ServerConnector) {
		let uploadFailed = (c.lastError != nil)

		if cacheStrategy == .cacheFirst || uploadFailed {
			storeParatemetersToResyncLater()
		}
		else {
			saveResultAndCountOnCache()
			deleteSyncParameters()
		}
	}

	// MARK: Interactor

	override open func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			deleteSyncParameters()
			saveResultAndCountOnCache()
		}

		super.callOnSuccess()
	}

	// MARK: Private methods

	fileprivate func createModelFromUploadData() {
		// Construct an object with the uploadEntry data
		result = ["title": imageUpload.title as AnyObject,
		          "fileEntryId": NSNumber(value: Int64(CFAbsoluteTimeGetCurrent()) as Int64),
		          "image": imageUpload.thumbnail!]
	}

	fileprivate func saveResultAndCountOnCache() {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		if let entry = result {
			let cacheKey = "\(ImageGalleryLoadInteractor.CacheKey)-\(repositoryId)-\(folderId)"
			let cacheKeyList = "\(cacheKey)-\(page)"
			let cacheKeyCount = "\(cacheKey)-count"

			cacheManager.getSome(
					collection: ScreenletName(ImageGalleryScreenlet.self),
					keys: [cacheKeyList, cacheKeyCount], result: {

				var newPage = [[String: AnyObject]]()

				if let oldPage = $0.first as? [[String:AnyObject]] {
					newPage.append(contentsOf: oldPage)
				}

				newPage.append(entry)

				self.storeNewImageEntry(newPage, cacheKey: cacheKeyList)

				if let count = $0.last as? Int {
					self.incrementAndStoreImageEntriesCount(count, cacheKey: cacheKeyCount)
				}
			})
		}
	}

	fileprivate func storeParatemetersToResyncLater() {
		if cacheKeyUsed == nil {
			// Not stored yet
			cacheKeyUsed = UUID().uuidString

			SessionContext.currentContext?.cacheManager.setDirty(
					collection: ScreenletName(ImageGalleryScreenlet.self),
					key: cacheKeyUsed!,
					value: imageUpload,
					attributes: [
						"folderId": NSNumber(value: folderId),
						"repositoryId": NSNumber(value: repositoryId),
						"page": NSNumber(value: page)
					])
		}
	}

	fileprivate func deleteSyncParameters() {
		// Exists if the parameters have been saved
		if let cacheKeyUsed = self.cacheKeyUsed {
			SessionContext.currentContext?.cacheManager.remove(
				collection: ScreenletName(ImageGalleryScreenlet.self),
				key: cacheKeyUsed)
		}
	}

	fileprivate func storeNewImageEntry(_ page: [[String:AnyObject]], cacheKey: String) {
		SessionContext.currentContext?.cacheManager.setClean(
				collection: ScreenletName(ImageGalleryScreenlet.self),
				key: cacheKey,
				value: page as NSCoding,
				attributes: [:])
	}

	fileprivate func incrementAndStoreImageEntriesCount(_ count: Int, cacheKey: String) {
		let newCount = count + 1

		SessionContext.currentContext?.cacheManager.setClean(
				collection: ScreenletName(ImageGalleryScreenlet.self),
				key: cacheKey,
				value: newCount as NSCoding,
				attributes: [:])
	}
}
