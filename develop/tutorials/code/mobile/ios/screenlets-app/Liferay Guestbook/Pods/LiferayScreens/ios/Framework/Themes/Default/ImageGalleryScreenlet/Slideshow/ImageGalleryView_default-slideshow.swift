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

@objc(ImageGalleryView_default_slideshow)
open class ImageGalleryView_default_slideshow: ImageGalleryCollectionViewBase {

	// MARK: BaseListCollectionView

	override open func doConfigureCollectionView(_ collectionView: UICollectionView) {
		collectionView.backgroundColor = .black
	}

	override open func doRegisterCellNibs() {
		if let imageGalleryGridCellNib = Bundle.nibInBundles(
			name: "ImageGallerySlideshowCell",
			currentClass: type(of: self)) {

			collectionView?.register(imageGalleryGridCellNib,
			                            forCellWithReuseIdentifier: imageCellId)
		}
	}

	override open func doCreateLayout() -> UICollectionViewLayout {

		return SlideShowLayout()
	}

	override open func updateRefreshControl() {
		//No refresh control in slideshow mode
	}

	override open func doFillLoadedCell(indexPath: IndexPath,
	                                                cell: UICollectionViewCell,
	                                                object: AnyObject) {

		guard let imageCell = cell as? ImageGallerySlideshowCell,
					let entry = object as? ImageEntry else {
			return
		}
		if let image = entry.image {
			imageCell.image = image
		}
		else {
			imageCell.imageUrl = entry.imageUrl
		}
	}

	override open func doFillInProgressCell(indexPath: IndexPath,
	                                                    cell: UICollectionViewCell) {

		cell.backgroundColor = .gray
	}

	override open func doGetCellId(indexPath: IndexPath,
	                                           object: AnyObject?) -> String {
		if object != nil {
			return imageCellId
		}

		return super.doGetCellId(indexPath: indexPath, object: object)
	}

	// MARK: UICollectionViewDataSource

	open func collectionView(_ collectionView: UICollectionView,
	                           layout collectionViewLayout: UICollectionViewLayout,
	                                  insetForSectionAtIndex section: Int) -> UIEdgeInsets {

		if let layout = collectionViewLayout as? UICollectionViewFlowLayout {
			let margin = collectionView.bounds.width / 2 - layout.itemSize.width / 2

			return UIEdgeInsets(top: 10, left: margin, bottom: 10, right: margin)
		}

		return UIEdgeInsets.zero
	}

}
