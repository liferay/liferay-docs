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

open class UploadUserPortraitLiferayConnector: ServerConnector {

	fileprivate let userId: Int64
	fileprivate var image: UIImage?

	var uploadResult: [String:AnyObject]?

	fileprivate let maxSize = 300 * 1024
	fileprivate var fileTooLarge = false

	// MARK: Initializers

	public init(userId: Int64, image: UIImage) {
		self.userId = userId
		self.image = image

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if self.image == nil {
				return ValidationError("userportrait-screenlet", "undefined-image")
			}
		}

		return error
	}

	override open func doRun(session: LRSession) {
		if let imageBytes = reduceImage(self.image!, factor: 1) {
			self.image = nil
			uploadBytes(imageBytes, withSession: session)
		}
		else {
			fileTooLarge = true
			uploadResult = nil
			lastError = NSError.errorWithCause(
					.abortedDueToPreconditions, message: "User portrait image is too large")
		}
	}

	// MARK: Private methods

	fileprivate func reduceImage(_ src: UIImage, factor: Double) -> Data? {
		if (src.size.width < 100 && src.size.height < 100) || factor < 0.1 {
			return nil
		}

		if let imageReduced = src.resizeImage(toWidth: Int(src.size.width * CGFloat(factor))),
				let imageBytes = UIImageJPEGRepresentation(imageReduced, 1) {

				return (imageBytes.count < maxSize)
					? imageBytes
					: reduceImage(src, factor: factor - 0.1)
		}

		return nil
	}

	// MARK: Public methods

	open func uploadBytes(_ imageBytes: Data, withSession session: LRSession) {
	}

}

open class Liferay62UploadUserPortraitConnector: UploadUserPortraitLiferayConnector {

	// MARK: UploadUserPortraitLiferayConnector

	override open func uploadBytes(_ imageBytes: Data, withSession session: LRSession) {
		let service = LRUserService_v62(session: session)

		do {
			let result = try service?.updatePortrait(withUserId: self.userId,
				bytes: imageBytes)

			if let result = result as? [String:AnyObject] {
				uploadResult = result
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
						message: "Could not upload user portrait.")
			}
		}
		catch let error as NSError {
			lastError = error
		}
	}

}

open class Liferay70UploadUserPortraitConnector: UploadUserPortraitLiferayConnector {

	// MARK: UploadUserPortraitLiferayConnector

	override open func uploadBytes(_ imageBytes: Data, withSession session: LRSession) {
		let service = LRUserService_v7(session: session)

		do {
			let result = try service?.updatePortrait(withUserId: self.userId,
				bytes: imageBytes)

			if let result = result as? [String:AnyObject] {
				uploadResult = result
				lastError = nil
			}
			else {
				lastError = NSError.errorWithCause(.invalidServerResponse,
						message: "Could not upload user portrait.")
			}
		}
		catch let error as NSError {
			lastError = error
		}
	}

}
