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

@objc(ImageGalleryView_default)
open class ImageGalleryView_default: ImageGalleryCollectionViewBase {

	open static let DefaultColumns = 4

	open var spacing: CGFloat = 1.0

	open var columnNumber = ImageGalleryView_default.DefaultColumns {
		didSet {
			changeLayout()
		}
	}

	internal var lastOffset: CGPoint?
	internal var currentOrientation: UIInterfaceOrientation?

	// MARK: UIView

	override open func layoutSubviews() {
		super.layoutSubviews()

		if let orientation = currentOrientation {
			let actualOrientation = UIApplication.shared.statusBarOrientation

			if orientation != actualOrientation {
				changeLayout()
				currentOrientation = actualOrientation
			}
		}
	}

	// MARK: BaseScreenletView

	override open func onShow() {
		super.onShow()

		currentOrientation = UIApplication.shared.statusBarOrientation

		if let lastOffset = lastOffset {
			collectionView?.contentOffset = lastOffset
		}
	}

	override open func onHide() {
		lastOffset = collectionView?.contentOffset
	}

	// MARK: BaseListCollectionView

	override open func doConfigureCollectionView(_ collectionView: UICollectionView) {
		collectionView.backgroundColor = .white
	}

	override open func doRegisterCellNibs() {
		if let imageGalleryGridCellNib = Bundle.nibInBundles(
			name: "ImageGalleryGridCell",
			currentClass: type(of: self)) {

			collectionView?.register(
					imageGalleryGridCellNib,
					forCellWithReuseIdentifier: imageCellId)
		}
	}

	override open func doCreateLayout() -> UICollectionViewLayout {
		// When the theme is changed dinamically the collection view hasn't the correct bounds at
		// this time so we use the screenlet (which is also a view) to calculate the itemSize
		screenlet?.layoutIfNeeded()

		return createCustomLayout()
	}

	override open func doFillLoadedCell(
			indexPath: IndexPath,
			cell: UICollectionViewCell,
			object: AnyObject) {

		guard let imageCell = cell as? ImageGalleryGridCell, let entry = object as? ImageEntry else {
			return
		}

		if let image = entry.image {
			imageCell.image = image
		}
		else {
			imageCell.imageUrl = entry.thumbnailUrl
		}
	}

	override open func doFillInProgressCell(indexPath: IndexPath, cell: UICollectionViewCell) {

		cell.backgroundColor = .gray
	}

	override open func doGetCellId(indexPath: IndexPath, object: AnyObject?) -> String {
		return imageCellId
	}

	// MARK: Public methods

	open func changeLayout() {
		if let collectionView = collectionView {
			let newLayout = createCustomLayout()
			collectionView.setCollectionViewLayout(newLayout, animated: true)
		}
	}

	// MARK: Internal methods

	internal func createCustomLayout() -> UICollectionViewLayout {
		let layout = UICollectionViewFlowLayout()

		layout.minimumLineSpacing = spacing
		layout.minimumInteritemSpacing = spacing
		layout.sectionInset = UIEdgeInsets(top: 10, left: 20, bottom: 10, right: 20)

		let cellWidth = cellWidthForNumberOfColumns(columnNumber)
		layout.itemSize = CGSize(width: cellWidth, height: cellWidth)

		return layout
	}

	internal func cellWidthForNumberOfColumns(_ numCols: Int) -> CGFloat {

		let horizontalMargins: CGFloat = 40
		let viewWidth = screenlet!.bounds.width

		let cellWidth =  (viewWidth - horizontalMargins) / CGFloat(numCols) - CGFloat(spacing)

		return cellWidth
	}

}
