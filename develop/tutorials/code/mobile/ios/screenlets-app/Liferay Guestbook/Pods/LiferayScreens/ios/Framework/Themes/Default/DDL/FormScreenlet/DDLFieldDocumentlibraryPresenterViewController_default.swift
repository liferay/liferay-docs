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
import MobileCoreServices

private let xibName = "DDLFieldDocumentlibraryPresenterViewController_default"

@objc(DDMFieldDocumentlibraryPresenterViewController_default)
open class DDMFieldDocumentlibraryPresenterViewController_default: UIViewController,
		UIImagePickerControllerDelegate, UINavigationControllerDelegate {

	// MARK: Outlets

	@IBOutlet open var takeNewButton: UIButton?

	@IBOutlet open var selectPhotoButton: UIButton?

	@IBOutlet open var selectVideoButton: UIButton?

	@IBOutlet open var cancelButton: UIButton?

	open var selectedDocumentClosure: ((UIImage?, URL?) -> Void)?

	fileprivate let imagePicker = UIImagePickerController()

	// MARK: Initializers

	override public init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
		super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
	}

	public convenience init() {
		self.init(
			nibName: xibName,
			bundle: Bundle.bundleForNibName(xibName, currentClass: type(of: self)))

		imagePicker.delegate = self
		imagePicker.allowsEditing = false
		imagePicker.modalPresentationStyle = .currentContext

		takeNewButton?.replaceAttributedTitle(
				LocalizedString("default", key: "ddlform-upload-picker-take-new", obj: self),
				forState: .normal)
		selectPhotoButton?.replaceAttributedTitle(
				LocalizedString("default", key: "ddlform-upload-picker-select-photo", obj: self),
				forState: .normal)
		selectVideoButton?.replaceAttributedTitle(
				LocalizedString("default", key: "ddlform-upload-picker-select-video", obj: self),
				forState: .normal)
		cancelButton?.replaceAttributedTitle(
				LocalizedString("default", key: "ddlform-upload-picker-cancel", obj: self),
				forState: .normal)
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	// MARK: Actions

	@IBAction fileprivate func cancelButtonAction(_ sender: AnyObject) {
		selectedDocumentClosure?(nil, nil)
	}

	@IBAction fileprivate func takePhotoAction(_ sender: AnyObject) {
		imagePicker.sourceType = .camera

		present(imagePicker, animated: true) {}
	}

	@IBAction fileprivate func selectPhotosAction(_ sender: AnyObject) {
		imagePicker.sourceType = .savedPhotosAlbum

		// Reset media types to default
		imagePicker.mediaTypes = [kUTTypeImage as String]

		present(imagePicker, animated: true) {}
	}

	@IBAction fileprivate func selectVideosAction(_ sender: AnyObject) {

		imagePicker.sourceType = .savedPhotosAlbum
		imagePicker.mediaTypes = [kUTTypeMovie as NSString as String]

		present(imagePicker, animated: true) {}
	}

	// MARK: UIImagePickerControllerDelegate

    open func imagePickerController(
			_ picker: UIImagePickerController,
			didFinishPickingMediaWithInfo info: [String : Any]) {

		let selectedImage = info[UIImagePickerControllerOriginalImage] as? UIImage
		let selectedURL = info[UIImagePickerControllerMediaURL] as? URL

		selectedDocumentClosure?(selectedImage, selectedURL)

		imagePicker.dismiss(animated: true) {}
	}

    open func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
		cancelButtonAction(picker)
		imagePicker.dismiss(animated: true) {}
	}

}
