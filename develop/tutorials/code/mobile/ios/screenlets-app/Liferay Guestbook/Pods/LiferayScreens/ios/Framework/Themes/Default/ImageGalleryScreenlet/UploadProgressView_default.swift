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

@objc(UploadProgressView_default)
open class UploadProgressView_default: UIView, UploadProgressViewBase {

	// MARK: Outlets

	@IBOutlet weak fileprivate var previewImage: UIImageView?

	@IBOutlet weak fileprivate var progressView: UIProgressView?

	@IBOutlet weak fileprivate var informationText: UILabel?

	open var cancelClosure: (() -> Void)?

	fileprivate var images: [UIImage] = []
	fileprivate var uploadsCount = 0

	override open func awakeFromNib() {
		progressView?.progress = 0
		addShadow()
	}

	// MARK: Public methods

	open func addShadow() {
		layer.masksToBounds = false
		layer.shadowOffset = CGSize(width: 0, height: 0)
		layer.shadowRadius = 5
		layer.shadowOpacity = 0.3
	}

	// MARK: Actions

	@IBAction func cancelButton() {
		cancelClosure?()
		hide()
	}

	// MARK: UploadProgressViewBase

	open func setProgress(_ progress: Float) {
		progressView?.progress = progress
	}

	open func addUpload(_ thumbnail: UIImage? = nil) {
		uploadsCount += 1

		if let thumbnail = thumbnail {
			images.append(thumbnail)
		}

		if uploadsCount == 1 {
			previewImage?.image = images.popLast()
		}

		updateInformationText()
	}

	open func uploadComplete() {
		if uploadsCount == 1 {
			hide()
		}
		else {
			uploadsCount -= 1
			progressView?.progress = 0
			previewImage?.image = images.popLast()
			updateInformationText()
		}
	}

	open func uploadError() {
		if uploadsCount == 1 {
			hide()
		}
	}

	open func hide() {
		UIView.animate(withDuration: 0.5, animations: {
			self.alpha = 0
		}, completion: { _ in
			self.removeFromSuperview()
		})
	}

	// MARK: Private methods

	fileprivate func updateInformationText() {
		if uploadsCount == 1 {
			informationText?.text = LocalizedString(
					"default",
					key: "imagegallery-uploading-one",
					obj: self)

		}
		else {
			let message = LocalizedString(
					"default",
					key: "imagegallery-uploading-several",
					obj: self)

			informationText?.text = String(format: message, uploadsCount)
		}
	}
}
