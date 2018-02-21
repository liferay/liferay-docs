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

@objc(ImageGalleryCollectionViewBase)
open class ImageGalleryCollectionViewBase: BaseListCollectionView, ImageGalleryViewModel {

	open var uploadProgressView: UploadProgressViewBase? {
		get {
			return _uploadView as? UploadProgressViewBase
		}
		set {
			_uploadView = newValue as? UIView
		}
	}

	open weak var _uploadView: UIView?

	internal let imageCellId = "ImageCellId"

	// MARK: ImageGalleryViewModel

	open var totalEntries: Int {
		return rowCount
	}

	open func onImageEntryDeleted(_ imageEntry: ImageEntry) {

		var section: Int?
		var sectionKey: String?
		var rowIndex: Int?

		for (keyIndex, key) in rows.keys.enumerated() {
			for (index, row) in rows[key]!.enumerated() {
				if let row = row as? ImageEntry {
					if imageEntry == row {
						section = keyIndex
						sectionKey = key
						rowIndex = index
					}
				}
			}
		}

		guard let finalSection = section, let finalRowIndex = rowIndex, let finalSectionKey = sectionKey
		else {
			return
		}

		deleteRow(finalSectionKey, row: finalRowIndex)

		let indexPath = IndexPath(row: finalRowIndex, section: finalSection)
		collectionView?.deleteItems(at: [indexPath])
	}

	open func onImageUploaded(_ imageEntry: ImageEntry) {
		uploadProgressView?.uploadComplete()
		if let lastSection = self.sections.last {
			self.addRow(lastSection, element: imageEntry)

			let lastRow = self.rows[lastSection]!.count - 1
			let indexPath = IndexPath(row: lastRow, section: self.sections.count - 1)
			self.collectionView?.insertItems(at: [indexPath])
		}
	}

	open func onImageUploadEnqueued(_ imageEntryUpload: ImageEntryUpload) {
		if uploadProgressView == nil {
			showUploadProgressView()
		}

		uploadProgressView?.addUpload(imageEntryUpload.image)
	}

	open func onImageUploadProgress(
		_ bytesSent: UInt64,
		bytesToSend: UInt64,
		imageEntryUpload: ImageEntryUpload) {

		let progress = Float(bytesSent) / Float(bytesToSend)

		uploadProgressView?.setProgress(progress)
	}

	open func onImageUploadError(_ imageEntryUpload: ImageEntryUpload, error: NSError) {
		uploadProgressView?.uploadError()
	}

	open func indexOf(imageEntry: ImageEntry) -> Int {
		for (_, sectionEntries) in rows {
			if let idx = sectionEntries.index(where: { $0 as! ImageEntry == imageEntry }) {
				return idx
			}
		}

		return -1
	}

	// MARK: BaseScreenletView

	open override func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	open func showUploadProgressView() {
		_uploadView = Bundle.viewForThemeOrDefault(
				name: "UploadProgressView",
				themeName: self.screenlet?.themeName ?? BaseScreenlet.DefaultThemeName,
				currentClass: type(of: self))

		guard let uploadView = _uploadView else {
			return
		}

		uploadView.translatesAutoresizingMaskIntoConstraints = false
		uploadView.alpha = 0

		addSubview(uploadView)

		let views = ["view": self, "uploadView": uploadView]

		let constraintH = NSLayoutConstraint.constraints(
				withVisualFormat: "H:|-(5)-[uploadView]-(5)-|",
				options: [],
				metrics: nil,
				views: views)

		let constraintV = NSLayoutConstraint.constraints(
				withVisualFormat: "V:[uploadView(55)]-(3)-|",
				options: [],
				metrics: nil,
				views: views)

		addConstraints(constraintH)
		addConstraints(constraintV)

		UIView.animate(withDuration: 0.2, animations: {
			uploadView.alpha = 1
		})

		uploadProgressView?.cancelClosure = { [weak self] in
			(self?.screenlet as? ImageGalleryScreenlet)?.cancelUploads()
		}

	}

}
