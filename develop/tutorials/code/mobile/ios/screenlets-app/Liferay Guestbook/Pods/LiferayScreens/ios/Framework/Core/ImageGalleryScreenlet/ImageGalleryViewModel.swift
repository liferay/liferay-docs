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

@objc(ImageGalleryViewModel)
public protocol ImageGalleryViewModel {

	/// Total images of the image gallery.
	var totalEntries: Int { get }

	/// Call this method when an image in the list are going to be deleted.
	///
	/// - Parameters imageEntry: deleted image entry.
	@objc optional func onImageEntryDeleted(_ imageEntry: ImageEntry)

	/// Call this method when the image upload finishes.
	///
	/// - Parameters image: uploaded image entry.
	@objc optional func onImageUploaded(_ imageEntry: ImageEntry)

	/// Call this method when an image is enqueued to be uploaded.
	///
	/// - Parameter imageEntryUpload: image to be uploaded.
	@objc optional func onImageUploadEnqueued(_ imageEntryUpload: ImageEntryUpload)

	/// Call this method when the image upload progress changes.
	///
	/// - Parameters:
	///   - bytesSent: image entry bytes sent.
	///   - bytesToSend: image entry bytes to send.
	///   - imageEntryUpload: the image entry being uploaded.
	@objc optional func onImageUploadProgress(_ bytesSent: UInt64, bytesToSend: UInt64,
	                                    imageEntryUpload: ImageEntryUpload)

	/// Call this method when an error occurs in the image upload process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - imageEntryUpload: the image entry where the error is.
	///   - error: error while uploading the image entry.
	@objc optional func onImageUploadError(_ imageEntryUpload: ImageEntryUpload, error: NSError)

	/// Returns the position of the first occurrence of a specified image entry.
	@objc optional func indexOf(imageEntry: ImageEntry) -> Int

}
