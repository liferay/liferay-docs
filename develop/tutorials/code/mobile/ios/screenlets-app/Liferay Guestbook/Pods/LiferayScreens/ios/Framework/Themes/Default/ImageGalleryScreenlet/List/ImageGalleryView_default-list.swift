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
import AFNetworking

@objc(ImageGalleryView_default_list)
open class ImageGalleryView_default_list: BaseListTableView, ImageGalleryViewModel {

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
		tableView?.deleteRows(at: [indexPath], with: .fade)
	}

	open func onImageUploaded(_ imageEntry: ImageEntry) {
		uploadProgressView?.uploadComplete()
		if let lastSection = self.sections.last {
			self.addRow(lastSection, element: imageEntry)

			let lastRow = self.rows[lastSection]!.count - 1
			let indexPath = IndexPath(row: lastRow, section: self.sections.count - 1)
			self.tableView?.insertRows(at: [indexPath], with: .top)
		}
	}

	open func onImageUploadEnqueued(_ imageEntry: ImageEntryUpload) {
		if uploadProgressView == nil {
			showUploadProgressView()
		}

		uploadProgressView?.addUpload(imageEntry.image)
	}

	open func showUploadProgressView() {
		uploadProgressView = Bundle.viewForTheme(
			name: "UploadProgressView",
			themeName: BaseScreenlet.DefaultThemeName,
			currentClass: type(of: self)) as? UploadProgressView_default

		_uploadView?.translatesAutoresizingMaskIntoConstraints = false
		_uploadView?.alpha = 0

		addSubview(_uploadView!)

		let views = ["view": self, "uploadView": _uploadView!]

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

		UIView.animate(withDuration: 0.5, animations: {
			self._uploadView?.alpha = 1
		})

		uploadProgressView?.cancelClosure = { [weak self] in
			(self?.screenlet as? ImageGalleryScreenlet)?.cancelUploads()
		}
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

    override open func onCreated() {
        super.onCreated()
        tableView?.rowHeight = 110
    }

    override open func createProgressPresenter() -> ProgressPresenter {
        return DefaultProgressPresenter()
    }

	// MARK: BaseListTableView

    override open func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
        guard let imageCell = cell as? ImageGalleryCell, let entry = object as? ImageEntry else {
            return
        }

		if let image = entry.image {
			imageCell.img = image
		}
		else {
        	imageCell.imageUrl = entry.thumbnailUrl
		}
        imageCell.title = entry.title
    }

    override open func doFillInProgressCell(row: Int, cell: UITableViewCell) {
        cell.textLabel?.text = "..."
        cell.accessoryType = .none

        if let image = Bundle.imageInBundles(
            name: "default-hourglass",
            currentClass: type(of: self)) {

            cell.accessoryView = UIImageView(image: image)
            cell.accessoryView?.frame = CGRect(x: 0, y: 0, width: image.size.width, height: image.size.height)
        }
    }

    override open func doGetCellId(row: Int, object: AnyObject?) -> String {
        if object != nil {
            return imageCellId
        }

        return super.doGetCellId(row: row, object: object)
    }

    override open func doRegisterCellNibs() {
        if let imageGalleryCellNib = Bundle.nibInBundles(
            name: "ImageGalleryCell",
            currentClass: type(of: self)) {

            tableView?.register(imageGalleryCellNib, forCellReuseIdentifier: imageCellId)
        }
    }
}
