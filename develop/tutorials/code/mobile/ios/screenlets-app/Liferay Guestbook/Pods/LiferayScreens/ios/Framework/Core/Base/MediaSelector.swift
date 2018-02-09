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

@objc public enum LiferayMediaType: Int {
	case camera
	case video
	case image
	case imageEdited
}

@objc open class MediaSelector: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {

	public typealias SelectedMediaClosure = ((UIImage?, URL?) -> Void)?

	let pickerController = UIImagePickerController()
	let viewController: UIViewController?
	let types: [LiferayMediaType : String]
	let selectedMediaClosure: SelectedMediaClosure
	let cancelMessage: String
	var alertTitle: String?
	var selfRetain: MediaSelector?

	public init(
			viewController: UIViewController,
			types: [LiferayMediaType : String],
			cancelMessage: String,
			alertTitle: String? = nil,
			selectedMediaClosure: SelectedMediaClosure) {

		self.viewController = viewController
		self.types = types
		self.selectedMediaClosure = selectedMediaClosure
		self.cancelMessage = cancelMessage
		self.alertTitle = alertTitle
	}

	open func show() {
		selfRetain = self
		pickerController.delegate = self

		if #available(iOS 10.0, *) {
			showErrorIfAbsentKeys()
		}

		let alertMode: UIAlertControllerStyle =
				UIDevice.current.userInterfaceIdiom == .pad ? .alert : .actionSheet

		let alert = UIAlertController(
				title: nil,
				message: nil,
				preferredStyle: alertMode)

		if types.keys.contains(.camera) {
			let action = UIAlertAction(title: types[.camera], style: .default) { (_) in
				self.pickerController.sourceType = .camera
				self.viewController?.present(self.pickerController, animated: true) {}
			}

			alert.addAction(action)
		}

		if types.keys.contains(.video) {
			let action = UIAlertAction(title: types[.video], style: .default) { (_) in
				self.pickerController.sourceType = .savedPhotosAlbum
				self.pickerController.mediaTypes = [kUTTypeMovie as NSString as String]
				self.viewController?.present(self.pickerController, animated: true) {}
			}

			alert.addAction(action)
		}

		if types.keys.contains(.image) {
			let action = UIAlertAction(title: types[.image], style: .default) { (_) in
				self.pickerController.sourceType = .savedPhotosAlbum
				self.viewController?.present(self.pickerController, animated: true) {}
			}

			alert.addAction(action)
		}

		if types.keys.contains(.imageEdited) {
			let action = UIAlertAction(title: types[.imageEdited], style: .default) { (_) in
				self.pickerController.sourceType = .savedPhotosAlbum
				self.pickerController.allowsEditing = true
				self.viewController?.present(self.pickerController, animated: true) {}
			}

			alert.addAction(action)
		}

		let cancelAction = UIAlertAction(title: cancelMessage, style: .cancel, handler: nil)

		alert.addAction(cancelAction)

		if let title = alertTitle {
			alert.title = title
		}

		viewController?.present(alert, animated: true) {}
	}

	open func imagePickerController(
		_ picker: UIImagePickerController,
		didFinishPickingMediaWithInfo info: [String : Any]) {

		let selectedImage = info[UIImagePickerControllerOriginalImage] as? UIImage
		let selectedURL = info[UIImagePickerControllerMediaURL] as? URL

		pickerController.dismiss(animated: true) {
			self.selectedMediaClosure?(selectedImage, selectedURL)
			self.selfRetain = nil
		}
	}

	open func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
		pickerController.dismiss(animated: true) {
			self.selfRetain = nil
		}
	}

	fileprivate func showErrorIfAbsentKeys() {
		if Bundle.main.object(forInfoDictionaryKey: "NSPhotoLibraryUsageDescription") == nil ||
			Bundle.main.object(forInfoDictionaryKey: "NSCameraUsageDescription") == nil {

			print(LocalizedString("core", key: "NSPhotoLibraryUsageDescription-key-not-present",
				obj: type(of: self)))
		}
	}
}
