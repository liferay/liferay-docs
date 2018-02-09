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

@objc(ImageGallerySlideshowCell)
open class ImageGallerySlideshowCell: UICollectionViewCell {

	@IBOutlet fileprivate weak var slideshowImage: UIImageView?

	fileprivate var placeholderImage: UIImage?

	open var imageUrl: String {
		get {
			return ""
		}
		set {
			slideshowImage?.lr_setImageWithURL(URL(string: newValue)!,
					placeholderImage: placeholderImage,
					optionsInfo: [.backgroundDecode])
		}
	}

	open var image: UIImage {
		get {
			return UIImage()
		}
		set {
			slideshowImage?.image = newValue
		}
	}

	open override func awakeFromNib() {
		super.awakeFromNib()
		slideshowImage?.kf.indicatorType = .activity

		placeholderImage = Bundle.imageInBundles(
			name: "default-placeholder-image",
			currentClass: type(of: self))
	}

	open override func prepareForReuse() {
		super.prepareForReuse()
		slideshowImage?.image = placeholderImage
	}
}
