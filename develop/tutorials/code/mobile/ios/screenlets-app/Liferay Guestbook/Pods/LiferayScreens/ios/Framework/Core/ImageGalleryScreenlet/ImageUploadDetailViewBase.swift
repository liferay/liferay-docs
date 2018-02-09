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

@objc(ImageUploadDetailViewBase)
open class ImageUploadDetailViewBase: UIView {

	open var image: UIImage?

	open var imageTitle: String?

	open weak var screenlet: ImageGalleryScreenlet?

	// MARK: Outlets

	@IBOutlet open weak var imagePreview: UIImageView?

	@IBOutlet open weak var titleText: UITextField?

	@IBOutlet open weak var descripText: UITextView?

	// MARK: Public methods

	open func startUpload() {
		self.startUpload(
			self.titleText?.text ?? "",
			notes: self.descripText?.text ?? "")
	}

	open func startUpload(_ title: String, notes: String, thumbnail: UIImage? = nil) {
		let nonEmptyTitle = title.isEmpty
				? "\(self.screenlet!.filePrefix)\(UUID().uuidString)"
				: title

		let actionClosure: (UIImage?) -> Void = { thumbnailImage in
			let imageUpload = ImageEntryUpload(
				image: self.image!,
				thumbnail: thumbnailImage,
				title: nonEmptyTitle,
				notes: notes)

			self.screenlet?.performAction(
				name: ImageGalleryScreenlet.EnqueueUploadAction,
				sender: imageUpload)
		}

		if let thumbnail = thumbnail {
			actionClosure(thumbnail)
		}
		else {
			image?.resizeImage(toWidth: 300, completion: actionClosure)
		}
	}

}
