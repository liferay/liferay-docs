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

@objc(ImageGalleryCell)
open class ImageGalleryCell: UITableViewCell {

	// MARK: Outlets

	@IBOutlet fileprivate weak var imagePreview: UIImageView?

	@IBOutlet fileprivate weak var imageTitle: UILabel?

	fileprivate var placeholderImage: UIImage?

    open var title: String? {
        get {
            return imageTitle?.text
        }
        set {
            imageTitle?.text = newValue
        }
    }

    open var imageUrl: String? {
        get {
            return ""
        }
        set {
            imagePreview?.lr_setImageWithURL(URL(string: newValue ?? "")!)
        }
    }

	open var img: UIImage? {
		get {
			return imagePreview?.image
		}
		set {
			imagePreview?.image = newValue
		}
	}

    override open func awakeFromNib() {
        super.awakeFromNib()

		imagePreview?.kf.indicatorType = .activity

		placeholderImage = Bundle.imageInBundles(
			name: "default-placeholder-image",
			currentClass: type(of: self))

    }

	override open func prepareForReuse() {
		super.prepareForReuse()

		imagePreview?.image = placeholderImage
	}
}
