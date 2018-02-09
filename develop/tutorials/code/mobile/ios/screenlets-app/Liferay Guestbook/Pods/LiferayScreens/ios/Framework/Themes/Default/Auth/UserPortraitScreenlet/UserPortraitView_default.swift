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

#if LIFERAY_SCREENS_FRAMEWORK
	import AFNetworking
#endif

// Global initial load
private func loadPlaceholderCache(_ done: ((UIImage?) -> Void)? = nil) {
	var image: UIImage?

	dispatch_async {
		image = Bundle.imageInBundles(
			name: "default-portrait-placeholder",
			currentClass: UserPortraitView_default.self)

		UserPortraitView_default.defaultPlaceholder = image

		dispatch_main {
			done?(image)
		}
	}
}

@objc(UserPortraitView_default)
open class UserPortraitView_default: BaseScreenletView, UserPortraitViewModel,
	UIActionSheetDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate {

	open static var defaultPlaceholder: UIImage?

	// MARK: Outlets

	@IBOutlet weak open var activityIndicator: UIActivityIndicatorView?

	@IBOutlet weak open var portraitImage: UIImageView?

	@IBOutlet weak var editButton: UIButton?

	override open var editable: Bool {
		didSet {
			self.editButton?.isHidden = !editable
			if editable {
				self.superview?.clipsToBounds = false
			}
		}
	}

	override open var progressMessages: [String:ProgressMessages] {
		return [
			"load-portrait": [
				.working: ""
			],
			"upload-portrait": [
				.working: "",
				.failure: LocalizedString("default", key: "userportrait-uploading-error", obj: self)
			]
		]
	}

	fileprivate let imagePicker = UIImagePickerController()

	// MARK: SignUpViewModel

	open var image: UIImage? {
		get {
			return portraitImage?.image
		}
		set {
			if let image = newValue {
				portraitImage?.image = image
			}
			else {
				loadDefaultPlaceholder()
			}
		}
	}

	open var borderWidth: CGFloat = 1.0 {
		didSet {
			portraitImage?.layer.borderWidth = borderWidth
		}
	}

	open var borderColor: UIColor? {
		didSet {
			portraitImage?.layer.borderColor = (borderColor ?? DefaultThemeBasicBlue).cgColor
		}
	}

	// MARK: BaseScreenletView

	override open func createProgressPresenter() -> ProgressPresenter {
		return UserPortraitDefaultProgressPresenter(spinner: activityIndicator!)
	}

	override open func onCreated() {
		super.onCreated()

		imagePicker.delegate = self
		imagePicker.allowsEditing = true
		imagePicker.modalPresentationStyle = .fullScreen
	}

	override open func onShow() {
		portraitImage?.layer.borderWidth = borderWidth
		portraitImage?.layer.borderColor = (borderColor ?? DefaultThemeBasicBlue).cgColor
		portraitImage?.layer.cornerRadius = DefaultThemeButtonCornerRadius
	}

	override open func onPreAction(name: String, sender: AnyObject?) -> Bool {
		if name == "edit-portrait" {
			let takeNewPicture = LocalizedString(
				"default", key: "userportrait-take-new-picture", obj: self)

			let chooseExisting = LocalizedString(
				"default", key: "userportrait-choose-existing-picture", obj: self)

			let alert = MediaSelector(
					viewController: self.presentingViewController!,
					types: [.imageEdited: chooseExisting, .camera: takeNewPicture],
					cancelMessage: "Cancel",
					alertTitle: "Change portrait") { (image, _) in

				self.userAction(name: "upload-portrait", sender: image)
			}

			alert.show()

			return false
		}

		return true
	}

	// MARK: Public methods

	/// Loads the placeholder with user information
	open func loadPlaceholder(for user: User) {
		loadDefaultPlaceholder()
	}

	open func loadDefaultPlaceholder() {
		dispatch_main {
			if let placeholder = UserPortraitView_default.defaultPlaceholder {
				self.portraitImage?.image = placeholder
			}
			else {
				loadPlaceholderCache {
					self.portraitImage?.image = $0
				}
			}
		}
	}
}
