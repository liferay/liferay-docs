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

@objc(ImageUploadDetailViewController_default)
open class ImageUploadDetailViewController_default: UIViewController {

	open var imageUploadDetailview: ImageUploadDetailViewBase?

	// MARK: Initializers

	public init(imageUploadDetailview: ImageUploadDetailViewBase) {
		super.init(nibName: nil, bundle: nil)
		self.imageUploadDetailview = imageUploadDetailview
	}

	public required init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	// MARK: UIViewController

    override open func viewDidLoad() {
        super.viewDidLoad()

		edgesForExtendedLayout = UIRectEdge()

		addNavBarButtons()

		addImageUploadView()
    }

	// MARK: Public methods

	open func addNavBarButtons() {
		let uploadButton = UIBarButtonItem(
				title: LocalizedString("default", key: "imagegallery-upload", obj: self),
				style: .plain,
				target: self,
				action: #selector(startUploadClick))

		let cancelButton = UIBarButtonItem(
				title: LocalizedString("default", key: "imagegallery-cancel", obj: self),
				style: .plain,
				target: self,
				action: #selector(cancelClick))

		navigationItem.rightBarButtonItem = uploadButton
		navigationItem.leftBarButtonItem = cancelButton
	}

	open func addImageUploadView() {
		imageUploadDetailview?.frame = view.frame
		view.addSubview(imageUploadDetailview!)

		imageUploadDetailview!.autoresizingMask = [.flexibleWidth, .flexibleHeight]
	}

	open func startUploadClick() {
		dismiss(animated: true) {
			self.imageUploadDetailview?.startUpload()
		}
	}

	open func cancelClick() {
		dismiss(animated: true) {}
	}
}
