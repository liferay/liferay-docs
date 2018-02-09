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

@objc(ImageUploadDetailView_default)
open class ImageUploadDetailView_default: ImageUploadDetailViewBase, UITextViewDelegate {

	// MARK: Outlets

	@IBOutlet weak var scroll: UIScrollView!

	@IBOutlet weak var hintLabel: UILabel!

	//MARK ImageUploadDetailVeiewBase

	override open var image: UIImage? {
		didSet {
			imagePreview?.image = image
		}
	}

	override open var imageTitle: String? {
		didSet {
			titleText?.text = imageTitle
		}
	}

	// MARK: UIView

	override open func awakeFromNib() {
		initialize()
	}

	open func initialize() {
		descripText?.layer.borderColor = UIColor.lightGray.cgColor
		descripText?.layer.borderWidth = 1.0
		descripText?.layer.cornerRadius = 4.0
		descripText?.delegate = self

		hintLabel?.text = LocalizedString("default", key: "imagegallery-description", obj: self)

		titleText?.placeholder = LocalizedString("default", key: "imagegallery-title", obj: self)

		let dismissKeyboardGesture = UITapGestureRecognizer(
			target: self,
			action: #selector(dismissKeyboard))

		scroll?.addGestureRecognizer(dismissKeyboardGesture)
	}

	override open func didMoveToWindow() {
		if window != nil {

			NotificationCenter.default.addObserver(
					self,
					selector: #selector(keyboardWillShow(_:)),
					name: NSNotification.Name.UIKeyboardWillShow,
					object: nil)

			NotificationCenter.default.addObserver(
					self,
					selector: #selector(keyboardWillHide(_:)),
					name: NSNotification.Name.UIKeyboardWillHide,
					object: nil)

		}
	}

	override open func willMove(toWindow newWindow: UIWindow?) {

		NotificationCenter.default.removeObserver(
				self, name: NSNotification.Name.UIKeyboardWillShow, object: nil)
		NotificationCenter.default.removeObserver(
				self, name: NSNotification.Name.UIKeyboardWillHide, object: nil)
	}

	// MARK: Public methods

	open func textViewDidBeginEditing(_ textView: UITextView) {
		hintLabel.alpha = 0
		textView.layer.borderColor = DefaultThemeBasicBlue.cgColor
	}

	open func textViewDidEndEditing(_ textView: UITextView) {
		if textView.text.isEmpty {
			hintLabel.alpha = 0.5
		}

		textView.layer.borderColor = UIColor.lightGray.cgColor
	}

	open func dismissKeyboard() {
		guard let descripText = descripText, let titleText = titleText else {
			return
		}
		if descripText.isFirstResponder {
			descripText.resignFirstResponder()
		}
		else if titleText.isFirstResponder {
			titleText.resignFirstResponder()
		}
	}

	// MARK: Notifications

	open func keyboardWillShow(_ notification: Notification) {

		let keyboardHeight =
			(notification.userInfo![UIKeyboardFrameBeginUserInfoKey]! as AnyObject).cgRectValue.height

		var scrollNewFrame = scroll.frame
		scrollNewFrame.size.height = frame.height - keyboardHeight

		scroll.frame = scrollNewFrame

		let bottomOffset = CGPoint(x: 0, y: scroll.contentSize.height - scroll.frame.height)
		scroll.setContentOffset(bottomOffset, animated: true)
	}

	open func keyboardWillHide(_ notification: Notification) {
		let keyboardHeight =
			(notification.userInfo![UIKeyboardFrameBeginUserInfoKey]! as AnyObject).cgRectValue.height

		var scrollNewFrame = scroll.frame
		scrollNewFrame.size.height = frame.height + keyboardHeight

		scroll.frame = scrollNewFrame
	}
}
