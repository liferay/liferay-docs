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

@objc(SignUpView_default)
open class SignUpView_default: BaseScreenletView, SignUpViewModel {

	// MARK: Outlets

	@IBOutlet open weak var emailAddressField: UITextField?

	@IBOutlet open weak var passwordField: UITextField?

	@IBOutlet open weak var firstNameField: UITextField?

	@IBOutlet open weak var lastNameField: UITextField?

	@IBOutlet open weak var signUpButton: UIButton?

	@IBOutlet open weak var scroll: UIScrollView?

	// MARK: BaseScreenletView

	override open var progressMessages: [String:ProgressMessages] {
		return [
			"signup-action": [.working: LocalizedString("default", key: "signup-loading-message", obj: self),
				.failure: LocalizedString("default", key: "signup-loading-error", obj: self)],
			"save-action": [.working: LocalizedString("default", key: "signup-saving-message", obj: self),
				.failure: LocalizedString("default", key: "signup-saving-error", obj: self)]
		]
	}

	override open func onStartInteraction() {
		signUpButton?.isEnabled = false
	}

	override open func onFinishInteraction(_ result: AnyObject?, error: NSError?) {
		signUpButton?.isEnabled = true
	}

	// MARK: BaseScreenletView

	override open func onCreated() {
		super.onCreated()

		setButtonDefaultStyle(signUpButton)
	}

	override open func onSetTranslations() {
		firstNameField?.placeholder = LocalizedString("default", key: "first-name-placeholder", obj: self)
		lastNameField?.placeholder = LocalizedString("default", key: "last-name-placeholder", obj: self)
		emailAddressField?.placeholder = LocalizedString("default", key: "auth-method-email", obj: self)
		passwordField?.placeholder = LocalizedString("default", key: "password-placeholder", obj: self)

		signUpButton?.replaceAttributedTitle(
				LocalizedString("default", key: "signup-button", obj: self),
				forState: .normal)
	}

	override open func createProgressPresenter() -> ProgressPresenter {
		return DefaultProgressPresenter()
	}

	// MARK: SignUpViewModel

	open var emailAddress: String? {
		get {
			return nullIfEmpty(emailAddressField!.text)
		}
		set {
			emailAddressField!.text = newValue
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

	open var firstName: String? {
		get {
			return nullIfEmpty(firstNameField?.text)
		}
		set {
			firstNameField?.text = newValue
		}
	}

	open var lastName: String? {
		get {
			return nullIfEmpty(lastNameField?.text)
		}
		set {
			lastNameField?.text = newValue
		}
	}

	open var editCurrentUser: Bool = false {
		didSet {
			let key: String
			let actionName: String

			if editCurrentUser {
				key = "save-button"
				actionName = "save-action"

				self.firstName = SessionContext.currentContext?.user.firstName
				self.middleName = SessionContext.currentContext?.user.middleName
				self.lastName = SessionContext.currentContext?.user.lastName
				self.emailAddress = SessionContext.currentContext?.user.email
				self.password = SessionContext.currentContext?.basicAuthPassword
				self.screenName = SessionContext.currentContext?.user.screenName
				self.jobTitle = SessionContext.currentContext?.user.jobTitle
			}
			else {
				key = "signup-button"
				actionName = "signup-action"
			}

			self.signUpButton?.replaceAttributedTitle(
					LocalizedString("default", key: key, obj: self),
					forState: .normal)

			self.signUpButton?.restorationIdentifier = actionName
		}
	}

	// The following properties are not supported in this theme but
	// may be supported in a child theme

	open var screenName: String? {
		get { return nil }
		set {}
	}

	open var middleName: String? {
		get { return nil }
		set {}
	}

	open var jobTitle: String? {
		get { return nil }
		set {}
	}

}
