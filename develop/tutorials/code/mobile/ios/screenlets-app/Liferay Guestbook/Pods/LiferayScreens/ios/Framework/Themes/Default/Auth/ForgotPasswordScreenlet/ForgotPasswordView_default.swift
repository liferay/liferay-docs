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

@objc(ForgotPasswordView_default)
open class ForgotPasswordView_default: BaseScreenletView, ForgotPasswordViewModel {

	// MARK: Outlets

	@IBOutlet open weak var userNameField: UITextField?

	@IBOutlet open weak var requestPasswordButton: UIButton?

	override open var progressMessages: [String:ProgressMessages] {
		return [
			BaseScreenlet.DefaultAction: [
				.working: LocalizedString("default", key: "forgotpassword-loading-message", obj: self),
				.failure: LocalizedString("default", key: "forgotpassword-loading-error", obj: self),
				.success: LocalizedString("default", key: "forgotpassword-\(successMessageKey)", obj: self)
			]
		]
	}

	fileprivate var successMessageKey = "password-sent"

	// MARK: ForgotPasswordViewModel

	open var userName: String? {
		get {
			return nullIfEmpty(userNameField?.text)
		}
		set {
			userNameField?.text = newValue
		}
	}

	// MARK: BasicAuthBasedType

	open var basicAuthMethod: String? = BasicAuthMethod.email.rawValue {
		didSet {
			setBasicAuthMethodStyles(view: self,
			                         basicAuthMethod: BasicAuthMethod.create(basicAuthMethod),
			                         userNameField: userNameField)
		}
	}

	open var saveCredentials: Bool {
		get {
			return false
		}
		set {}
	}

	// MARK: BaseScreenletView

	override open func onCreated() {
		super.onCreated()

		setButtonDefaultStyle(requestPasswordButton)
	}

	override open func onSetTranslations() {
		userNameField?.placeholder = LocalizedString("default", key: "forgotpassword-username", obj: self)
		requestPasswordButton?.replaceAttributedTitle(
				LocalizedString("default", key: "forgotpassword-button", obj: self),
				forState: .normal)

	}

	override open func onStartInteraction() {
		requestPasswordButton?.isEnabled = false
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		requestPasswordButton?.isEnabled = true

		if let resultPasswordSent = result as? Bool {
			successMessageKey = resultPasswordSent ? "password-sent" : "reset-sent"
		}
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

}
