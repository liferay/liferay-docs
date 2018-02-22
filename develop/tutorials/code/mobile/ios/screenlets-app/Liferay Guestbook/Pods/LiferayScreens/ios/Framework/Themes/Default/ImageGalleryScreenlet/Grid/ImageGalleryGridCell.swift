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

@objc(ImageGalleryGridCell)
open class ImageGalleryGridCell: UICollectionViewCell {

	@IBOutlet fileprivate weak var previewImage: UIImageView?

	fileprivate var placeholderImage: UIImage?

	open var imageUrl: String {
		get {
			return ""
		}
		set {
			previewImage?.lr_setImageWithURL(
					URL(string: newValue)!,
					placeholderImage:  placeholderImage,
					optionsInfo: [.backgroundDecode])
		}
	}

	open var image: UIImage {
		get {
			return UIImage()
		}
		set {
			previewImage?.image = newValue
		}
	}

	open override func awakeFromNib() {
		super.awakeFromNib()

		previewImage?.clipsToBounds = true
		previewImage?.kf.indicatorType = .activity

		placeholderImage = Bundle.imageInBundles(
			name: "default-placeholder-image",
			currentClass: type(of: self))
	}

	open override func prepareForReuse() {
		super.prepareForReuse()

		previewImage?.image = placeholderImage
	}
}
