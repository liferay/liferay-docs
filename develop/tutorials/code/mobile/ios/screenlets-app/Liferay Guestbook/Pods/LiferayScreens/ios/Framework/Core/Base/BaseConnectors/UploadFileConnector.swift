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
import LRMobileSDK

open class UploadFileConnector: ServerConnector, LRCallback, LRFileProgressDelegate {

	public typealias OnProgress = (Any?, UInt64, UInt64) -> Void

	var inputStream: InputStream?
	var bytesToSend: Int64?
	var image: UIImage?
	open let fileName: String
	open let mimeType: String
	let parameter: Any?
	let onUploadedBytes: OnProgress?

	var requestSemaphore: DispatchSemaphore?

	var uploadResult: [String:AnyObject]?

	// MARK: Initializers

	public init(
		inputStream: InputStream,
		bytesToSend: Int64,
		fileName: String,
		mimeType: String,
		parameter: Any? = nil,
		onUploadedBytes: OnProgress?) {

		self.inputStream = inputStream
		self.bytesToSend = bytesToSend
		self.fileName = fileName
		self.mimeType = mimeType
		self.parameter = parameter
		self.onUploadedBytes = onUploadedBytes

		super.init()
	}

	public init(
		image: UIImage,
		fileName: String,
		mimeType: String,
		parameter: Any? = nil,
		onUploadedBytes: OnProgress?) {

		self.image = image
		self.fileName = fileName
		self.mimeType = mimeType
		self.parameter = parameter
		self.onUploadedBytes = onUploadedBytes

		super.init()
	}

	// MARK: ServerConnector

	override open func doRun(session: LRSession) {
		if inputStream == nil {
			if let imageData = UIImagePNGRepresentation(image!) {
				bytesToSend = Int64(imageData.count)
				inputStream = InputStream(data: imageData)
			}
			else {
				print("ERROR: could not create inputstream from image")
			}
		}

		session.callback = self
		let uploadData = LRUploadData(
			inputStream: inputStream,
			length: bytesToSend!,
			fileName: fileName,
			mimeType: mimeType,
			progressDelegate: self)
		uploadData?.progressDelegate = self

		requestSemaphore = DispatchSemaphore(value: 0)

		do {
			try doSendFile(session, data: uploadData!)
		}
		catch {

		}

		_ = requestSemaphore!.wait(timeout: .distantFuture)
	}

	// MARK: Public methods

	open func onProgress(_ data: Data!, totalBytes: Int64) {
		let totalBytesSent = UInt64(totalBytes)
		let totalBytesToSend = UInt64(self.bytesToSend ?? 0)

		onUploadedBytes?(parameter, totalBytesSent, totalBytesToSend)
	}

	open func onFailure(_ error: Error!) {
		lastError = error as NSError
		requestSemaphore!.signal()
	}

	open func doSendFile(_ session: LRSession, data: LRUploadData) throws {
		fatalError("Override doSendFile method")
	}

	open func onSuccess(_ result: Any!) {
		lastError = nil

		uploadResult = result as? [String:AnyObject]
		requestSemaphore!.signal()
	}

}
