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

open class DDLFormUploadLiferayConnector: ServerConnector, LRCallback, LRFileProgressDelegate {

	public typealias OnProgress = (DDMFieldDocument, UInt64, UInt64) -> Void

	let document: DDMFieldDocument
	let filePrefix: String

	let repositoryId: Int64
	let folderId: Int64

	let onUploadedBytes: OnProgress?

	var uploadResult: [String:AnyObject]?

	fileprivate var requestSemaphore: DispatchSemaphore?

	fileprivate var bytesToSend: Int64 = 0

	// MARK: Initializers

	public init(
			document: DDMFieldDocument,
			filePrefix: String,
			repositoryId: Int64,
			folderId: Int64,
			onProgress: OnProgress?) {
		self.document = document
		self.filePrefix = filePrefix
		self.repositoryId = repositoryId
		self.folderId = folderId
		self.onUploadedBytes = onProgress

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if document.currentValue == nil {
				return ValidationError("ddlform-screenlet", "undefined-current-value")
			}

			if filePrefix == "" {
				return ValidationError("ddlform-screenlet", "undefined-fileprefix")
			}
		}

		return error
	}

	override open func doRun(session: LRSession) {
		session.callback = self

		let fileName = "\(filePrefix)\(UUID().uuidString)"
		let stream = document.getStream(&bytesToSend)
		let uploadData = LRUploadData(
			inputStream: stream,
			length: bytesToSend,
			fileName: fileName,
			mimeType: document.mimeType,
			progressDelegate: self)
		uploadData?.progressDelegate = self

		requestSemaphore = DispatchSemaphore(value: 0)

		do {
			try doSendFile(session, name: fileName, data: uploadData!)
		}
		catch {
			// ignore the error because this is an async call
			// (the ObjC-Swift bridge is not working well in this scenario)
		}

		_ = requestSemaphore!.wait(timeout: .distantFuture)
	}

	open func doSendFile(_ session: LRSession, name: String, data: LRUploadData) throws {
		fatalError("Override doSendFile method")
	}

	// MARK: LRFileProgressDelegate

	open func onProgress(_ data: Data!, totalBytes: Int64) {
		let totalBytesSent = UInt64(totalBytes)
		let totalBytesToSend = UInt64(self.bytesToSend)

		document.uploadStatus = .uploading(totalBytesSent, totalBytesToSend)
		onUploadedBytes?(document, totalBytesSent, totalBytesToSend)
	}

	// MARK: LRCallback

	open func onFailure(_ error: Error!) {
		lastError = error as NSError
		uploadResult = nil

		requestSemaphore!.signal()
	}

	open func onSuccess(_ result: Any!) {
		lastError = nil
		uploadResult = result as? [String:AnyObject]

		requestSemaphore!.signal()
	}

}

open class Liferay62DDLFormUploadConnector: DDLFormUploadLiferayConnector {

	// MARK: DDLFormUploadLiferayConnector

	override open func doSendFile(_ session: LRSession, name: String, data: LRUploadData) throws {
		let service = LRDLAppService_v62(session: session)

		try service?.addFileEntry(withRepositoryId: repositoryId,
			folderId: folderId,
			sourceFileName: name,
			mimeType: document.mimeType,
			title: name,
			description: LocalizedString("ddlform-screenlet", key: "upload-metadata-description", obj: self),
			changeLog: LocalizedString("ddlform-screenlet", key: "upload-metadata-changelog", obj: self),
			file: data,
			serviceContext: nil)
	}

}

open class Liferay70DDLFormUploadConnector: DDLFormUploadLiferayConnector {

	// MARK: DDLFormUploadLiferayConnector

	override open func doSendFile(_ session: LRSession, name: String, data: LRUploadData) throws {
		let service = LRDLAppService_v7(session: session)

		try service?.addFileEntry(withRepositoryId: repositoryId,
			folderId: folderId,
			sourceFileName: name,
			mimeType: document.mimeType,
			title: name,
			description: LocalizedString("ddlform-screenlet", key: "upload-metadata-description", obj: self),
			changeLog: LocalizedString("ddlform-screenlet", key: "upload-metadata-changelog", obj: self),
			file: data,
			serviceContext: nil)
	}

}
