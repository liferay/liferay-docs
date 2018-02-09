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

@objc(ImageDisplayView_default)
open class ImageDisplayView_default: BaseScreenletView, ImageDisplayViewModel {

	// MARK: Outlets

	@IBOutlet open weak var imageView: UIImageView?

	// MARK: ImageDisplayViewModel

	open var imageMode: UIViewContentMode = .scaleAspectFit

	open var placeholderImageMode: UIViewContentMode = .center

	open var placeholder: UIImage?

	// MARK: FileDisplayViewModel

	open var url: URL? {
		didSet {
			if let url = url, let imageData = try? Data(contentsOf: url) {
				imageView?.contentMode = self.imageMode
				imageView?.image = UIImage(data: imageData)
			}
		}
	}

	open var title: String? {
		didSet {
			self.presentingViewController?.title = title
		}
	}

	// MARK: BaseScreenletView

	override open func onStartInteraction() {
		imageView?.contentMode = placeholderImageMode
		imageView?.image = placeholder
	}
}
