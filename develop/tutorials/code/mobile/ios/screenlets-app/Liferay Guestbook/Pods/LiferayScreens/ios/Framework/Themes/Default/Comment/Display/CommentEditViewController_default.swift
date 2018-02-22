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

private let xibName = "CommentEditViewController_default"

@objc(CommentEditViewController_default)
open class CommentEditViewController_default: UIViewController, UITextViewDelegate {

	// MARK: Outlets

	@IBOutlet open var bodyTextView: UITextView?

	@IBOutlet open var confirmButton: UIButton?

	@IBOutlet open var cancelButton: UIButton?

	@IBOutlet open var scrollView: UIScrollView?

	open var confirmBodyClosure: ((String?) -> Void)?

	fileprivate var placeholderLabel: UILabel!

	fileprivate var initialBody: String?

	// MARK: Initializers

	public override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
		super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
	}

	public convenience init(body: String?) {
		self.init(nibName: xibName,
				bundle: Bundle.bundleForNibName(xibName, currentClass: type(of: self)))

		self.initialBody = body
	}

	required public init?(coder aDecoder: NSCoder) {
		super.init(coder: aDecoder)
	}

	// MARK: UIViewController

	override open func viewDidLoad() {
		confirmButton?.replaceAttributedTitle(
			LocalizedString("default", key: "comment-display-confirm", obj: self),
			forState: .normal)
		cancelButton?.replaceAttributedTitle(
			LocalizedString("default", key: "comment-display-cancel", obj: self),
			forState: .normal)

		let notificationCenter = NotificationCenter.default
		notificationCenter.addObserver(self, selector: #selector(adjustForKeyboard),
			name: NSNotification.Name.UIKeyboardWillHide, object: nil)
		notificationCenter.addObserver(self, selector: #selector(adjustForKeyboard),
			name: NSNotification.Name.UIKeyboardWillChangeFrame, object: nil)

		if let textView = bodyTextView, let font = textView.font {
			textView.text = initialBody
			textView.delegate = self

			//Add an UILabel as the placeholder of the UITextView
			placeholderLabel = UILabel()
			placeholderLabel.text = LocalizedString(
				"default", key: "comment-display-type", obj: self)
			placeholderLabel.font = UIFont.italicSystemFont(ofSize: font.pointSize)
			placeholderLabel.sizeToFit()
			placeholderLabel.frame.origin = CGPoint(x: 5, y: font.pointSize / 2)
			placeholderLabel.textColor = UIColor(white: 0, alpha: 0.3)
			placeholderLabel.isHidden = !textView.text.isEmpty
			textView.addSubview(placeholderLabel)

			bodyTextView?.becomeFirstResponder()
		}
	}

	// MARK: Keyboard action

	func adjustForKeyboard(_ notification: Notification) {
		if let keyboardScreenEndFrame =
				(notification.userInfo?[UIKeyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue,
				let scroll = scrollView {

			let keyboardViewEndFrame =
				view.convert(keyboardScreenEndFrame, from: view.window)

			if notification.name == NSNotification.Name.UIKeyboardWillHide {
				scroll.contentInset = UIEdgeInsets.zero
			}
			else {
				scroll.contentInset =
					UIEdgeInsets(top: 0, left: 0, bottom: keyboardViewEndFrame.height, right: 0)
			}

			scroll.scrollIndicatorInsets = scroll.contentInset
		}
	}

	// MARK: UITextViewDelegate

	open func textViewDidChange(_ textView: UITextView) {
		placeholderLabel.isHidden = !textView.text.isEmpty
	}

	// MARK: Actions

	@IBAction open func cancelButtonAction() {
		bodyTextView?.resignFirstResponder()
		confirmBodyClosure?(nil)
	}

	@IBAction open func confirmButtonAction() {
		bodyTextView?.resignFirstResponder()
		confirmBodyClosure?(bodyTextView?.text)
	}
}
