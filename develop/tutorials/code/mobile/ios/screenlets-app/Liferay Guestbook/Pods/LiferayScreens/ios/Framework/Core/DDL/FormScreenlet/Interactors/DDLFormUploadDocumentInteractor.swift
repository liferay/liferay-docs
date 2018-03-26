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

class DDLFormUploadDocumentInteractor: ServerWriteConnectorInteractor {

	typealias OnProgress = DDLFormUploadLiferayConnector.OnProgress

	let filePrefix: String

	let repositoryId: Int64

	let groupId: Int64

	let folderId: Int64

	let document: DDMFieldDocument

	let onProgressClosure: OnProgress?

	var resultResponse: [String:AnyObject]?

	var lastCacheKey: String?

	// MARK: Initializers

	init(screenlet: BaseScreenlet?,
			document: DDMFieldDocument,
			onProgressClosure: @escaping OnProgress) {

		let formScreenlet = screenlet as! DDLFormScreenlet

		self.groupId = (formScreenlet.groupId != 0)
			? formScreenlet.groupId
			: LiferayServerContext.groupId

		self.filePrefix = formScreenlet.filePrefix
		self.folderId = formScreenlet.folderId
		self.repositoryId = (formScreenlet.repositoryId != 0)
			? formScreenlet.repositoryId
			: self.groupId

		self.document = document
		self.onProgressClosure = onProgressClosure

		super.init(screenlet: screenlet)
	}

	init(filePrefix: String,
			repositoryId: Int64,
			groupId: Int64,
			folderId: Int64,
			document: DDMFieldDocument) {

		self.groupId = (groupId != 0)
			? groupId
			: LiferayServerContext.groupId

		self.filePrefix = filePrefix
		self.folderId = folderId
		self.repositoryId = (repositoryId != 0)
			? repositoryId
			: self.groupId

		self.document = document
		self.lastCacheKey = document.cachedKey
		self.onProgressClosure = nil

		super.init(screenlet: nil)
	}

	// MARK: ServerConnectorInteractor

	override func createConnector() -> DDLFormUploadLiferayConnector {
		return LiferayServerContext.connectorFactory.createDDLFormUploadConnector(
			document: document,
			filePrefix: filePrefix,
			repositoryId: repositoryId,
			folderId: folderId,
			onProgress: self.onProgressClosure)
	}

	override func completedConnector(_ c: ServerConnector) {
		if let lastErrorValue = c.lastError {
			if lastErrorValue.code == ScreensErrorCause.notAvailable.rawValue {
				let cacheResult = DDMFieldDocument.UploadStatus.CachedStatusData(cacheKey())
				self.resultResponse = cacheResult
				document.uploadStatus = .uploaded(cacheResult)
			}
			else {
				document.uploadStatus = .failed(lastErrorValue)
			}
		}
		else if let uploadCon = c as? DDLFormUploadLiferayConnector {
			self.resultResponse = uploadCon.uploadResult
			document.uploadStatus = .uploaded(uploadCon.uploadResult!)
		}
	}

	// MARK: Cache methods

	override func writeToCache(_ c: ServerConnector) {
		guard let cacheManager = SessionContext.currentContext?.cacheManager else {
			return
		}

		// cache only supports images (right now)
		if let image = document.currentValue as? UIImage {
			let cacheFunction = (cacheStrategy == .cacheFirst || c.lastError != nil)
				? cacheManager.setDirty
				: cacheManager.setClean

			cacheFunction(
				ScreenletName(DDLFormScreenlet.self),
				cacheKey(),
				image,
				cacheAttributes(),
				nil)
		}
	}

	// MARK: Interactor

	override func callOnSuccess() {
		if cacheStrategy == .cacheFirst {
			SessionContext.currentContext?.cacheManager.setClean(
				collection: ScreenletName(DDLFormScreenlet.self),
				key: cacheKey(),
				attributes: cacheAttributes())
		}

		super.callOnSuccess()
	}

	// MARK: Private methods

	fileprivate func cacheKey() -> String {
		lastCacheKey = lastCacheKey ?? "document-\(Date().timeIntervalSince1970)"
		return lastCacheKey!
	}

	fileprivate func cacheAttributes() -> [String:AnyObject] {
		return [
			"document": self.document,
			"filePrefix": self.filePrefix as AnyObject,
			"folderId": self.folderId.description as AnyObject,
			"groupId": self.groupId.description as AnyObject,
			"repositoryId": self.repositoryId.description as AnyObject
		]
	}

}
