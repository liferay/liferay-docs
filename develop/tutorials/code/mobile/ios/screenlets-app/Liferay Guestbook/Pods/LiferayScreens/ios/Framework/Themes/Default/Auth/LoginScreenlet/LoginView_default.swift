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

@objc(LoginView_default)
open class LoginView_default: BaseScreenletView, LoginViewModel {

	// MARK: Outlets

	@IBOutlet open weak var userNameField: UITextField?

	@IBOutlet open weak var passwordField: UITextField?

	@IBOutlet open weak var loginButton: UIButton?

	@IBOutlet open weak var authorizeButton: UIButton?

	override open var progressMessages: [String:ProgressMessages] {
		return [
			BaseScreenlet.DefaultAction: [
				.working: LocalizedString("default", key: "login-loading-message", obj: self),
				.failure: LocalizedString("default", key: "login-loading-error", obj: self)]
		]
	}

	// MARK: AuthBasedViewModel

	open var basicAuthMethod: String? = BasicAuthMethod.email.rawValue {
		didSet {
			setBasicAuthMethodStyles(
					view: self,
					basicAuthMethod: BasicAuthMethod.create(basicAuthMethod),
					userNameField: userNameField)
		}
	}

	open var authType: String? = StringFromAuthType(AuthType.basic) {
		didSet {
			configureAuthType()
		}
	}

	// MARK: LoginViewModel

	open var userName: String? {
		get {
			return nullIfEmpty(userNameField?.text)
		}
		set {
			userNameField?.text = newValue
		}
	}

	open var password: String? {
		get {
			return nullIfEmpty(passwordField?.text)
		}
		set {
			passwordField?.text = newValue
		}
	}

	// MARK: BaseScreenletView

	override open func onCreated() {
		super.onCreated()

		setButtonDefaultStyle(loginButton)
		setButtonDefaultStyle(authorizeButton)

		configureAuthType()
	}

	override open func onSetTranslations() {
		userNameField?.placeholder = LocalizedString("default",
			key: BasicAuthMethod.create(basicAuthMethod).description, obj: self)

		passwordField?.placeholder = LocalizedString("default", key: "password-placeholder", obj: self)

		loginButton?.replaceAttributedTitle(LocalizedString("default", key: "signin-button", obj: self),
				forState: .normal)

		authorizeButton?.replaceAttributedTitle(LocalizedString("default", key: "authorize-button", obj: self),
				forState: .normal)
	}

	override open func onStartInteraction() {
		loginButton?.isEnabled = false
		authorizeButton?.isEnabled = false
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		loginButton?.isEnabled = true
		authorizeButton?.isEnabled = true
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	// MARK: Public methods

	open func configureAuthType() {
		let auth = AuthTypeFromString(authType ?? "") ?? .basic

		authorizeButton?.isHidden = (auth != .oAuth)
		loginButton?.superview?.isHidden = (auth == .oAuth)
	}

}
