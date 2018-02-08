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

@objc(CommentAddView_default)
open class CommentAddView_default: BaseScreenletView, CommentAddViewModel {

	// MARK: Outlets

	@IBOutlet weak var addCommentTextField: UITextField?

	@IBOutlet weak var sendCommentButton: UIButton? {
		didSet {
			setButtonDefaultStyle(sendCommentButton)
			updateButton()
		}
	}

	open var body: String {
		get {
			return addCommentTextField?.text ?? ""
		}
		set {
			addCommentTextField?.text = newValue
			updateButton()
		}
	}

	// MARK: Public methods

	open func updateButton() {
		sendCommentButton?.isEnabled = !(addCommentTextField?.text?.isEmpty ?? false)

		if let sendCommentButton = sendCommentButton {
			sendCommentButton.backgroundColor =
				sendCommentButton.isEnabled ? DefaultThemeBasicBlue :
						DefaultThemeBasicBlue.withAlphaComponent(0.5)
		}
	}

	// MARK: BaseScreenletView

	override open func onShow() {
		addCommentTextField?.delegate = self
	}

	override open func onSetTranslations() {
		addCommentTextField?.placeholder = LocalizedString("default", key: "comment-add-placeholder", obj: self)
		sendCommentButton?.titleLabel?.text = LocalizedString("default", key: "comment-add-send", obj: self)
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	// MARK: Actions

	@IBAction func editingDidChangeAction() {
		updateButton()
	}

	// MARK: UITextFieldDelegate

	override open func textFieldShouldReturn(_ textField: UITextField) -> Bool {
		userAction(name: "add-comment", sender: textField)
		return super.textFieldShouldReturn(textField)
	}
}
