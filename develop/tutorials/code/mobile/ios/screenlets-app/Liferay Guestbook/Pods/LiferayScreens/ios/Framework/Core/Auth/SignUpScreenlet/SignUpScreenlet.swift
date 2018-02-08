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

/// The SignUpScreenletDelegate protocol defines some methods that you use to manage the
/// SignUpScreenlet events. All of them are optional.
@objc(SignUpScreenletDelegate)
public protocol SignUpScreenletDelegate: BaseScreenletDelegate {

	/// Called when sign up successfully completes.
	/// The user attributes are passed as a dictionary of keys (String or NSStrings)
	/// and values (AnyObject or NSObject).
	///
	/// - Parameters:
	///   - screenlet: Sign up screenlet instance.
	///   - attributes: User attributes.
	@objc optional func screenlet(_ screenlet: SignUpScreenlet,
			onSignUpResponseUserAttributes attributes: [String:AnyObject])

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Sign up screenlet instance.
	///   - error: Error in sign up.
	@objc optional func screenlet(_ screenlet: SignUpScreenlet,
			onSignUpError error: NSError)

}

/// The Sign Up Screenlet creates a new user in your Liferay instance: a new user of your app 
/// can become a new user in your portal. You can also use this Screenlet to save the credentials 
/// of the new user in their keychain. This enables auto login for future sessions. The Screenlet 
/// also supports navigation of form fields from the keyboard of the user’s device.
@objc(SignUpScreenlet)
open class SignUpScreenlet: BaseScreenlet, AnonymousBasicAuthType {

	// MARK: Inspectables

	/// The user name, email address, or user ID (depending on the portal’s authentication method) 
	/// to use for authenticating the request.
	@IBInspectable open var anonymousApiUserName: String? = "test@liferay.com"

	/// The password for use in authenticating the request.
	@IBInspectable open var anonymousApiPassword: String? = "test"

	/// Whether the user is logged in automatically after a successful sign up.
	@IBInspectable open var autoLogin: Bool = true

	/// Sets whether or not the user’s credentials and attributes are stored in the keychain after 
	/// a successful log in. This attribute is ignored if autologin is disabled.
	@IBInspectable open var saveCredentials: Bool = true

	/// When set, authentication is done for a user in the specified company.
	/// If the value is 0, the company specified in LiferayServerContext is used.
	@IBInspectable open var companyId: Int64 = 0

	// MARK: Outlets

	@IBOutlet open weak var autoLoginDelegate: LoginScreenletDelegate?

	// MARK: Public properties

	open var signUpDelegate: SignUpScreenletDelegate? {
		return delegate as? SignUpScreenletDelegate
	}

	open var viewModel: SignUpViewModel {
		return screenletView as! SignUpViewModel
	}

	// MARK: BaseScreenlet

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {

		switch name {
		case "signup-action":
			return createSignUpConnectorInteractor()
		case "save-action":
			return createSaveInteractor()
		default:
			return nil
		}
	}

	// MARK: Public methods

	/// Loads the current user throught editCurrentUser property.
	///
	/// - Returns: True if there is a session created, false otherwise.
	open func loadCurrentUser() -> Bool {
		if SessionContext.isLoggedIn {
			self.viewModel.editCurrentUser = true
			return true
		}
		return false
	}

	// MARK: Private methods

	fileprivate func createSignUpConnectorInteractor() -> SignUpInteractor {
		let interactor = SignUpInteractor(screenlet: self)

		interactor.onSuccess = {
			self.signUpDelegate?.screenlet?(self,
					onSignUpResponseUserAttributes: interactor.resultUserAttributes!)

			if self.autoLogin {
				self.doAutoLogin(interactor.resultUserAttributes!)

				if let ctx = SessionContext.currentContext, self.saveCredentials {
					ctx.removeStoredCredentials()

					if ctx.storeCredentials() {
						self.autoLoginDelegate?.screenlet?(self,
							onCredentialsSavedUserAttributes: interactor.resultUserAttributes!)
					}
				}
			}
		}

		interactor.onFailure = {
			self.signUpDelegate?.screenlet?(self, onSignUpError: $0)
			return
		}

		return interactor
	}

	fileprivate func createSaveInteractor() -> SaveUserInteractor {
		let interactor = SaveUserInteractor(screenlet: self)

		interactor.onSuccess = {
			if SessionContext.isLoggedIn {
				// refresh current session
				self.doAutoLogin(interactor.resultUserAttributes!)
			}

			self.signUpDelegate?.screenlet?(self,
					onSignUpResponseUserAttributes: interactor.resultUserAttributes!)
		}

		interactor.onFailure = {
			self.signUpDelegate?.screenlet?(self, onSignUpError: $0)
		}

		return interactor
	}

	fileprivate func doAutoLogin(_ userAttributes: [String:AnyObject]) {
		let userNameKeys: [BasicAuthMethod:String] = [
			.email: "emailAddress",
			.screenName: "screenName",
			.userId: "userId"
		]

		let currentAuth = BasicAuthMethod.fromUserName(anonymousApiUserName!)

		if let currentKey = userNameKeys[currentAuth],
				let userName = userAttributes[currentKey] as? String {

			SessionContext.loginWithBasic(
				username: userName,
				password: self.viewModel.password!,
				userAttributes: userAttributes)

			self.autoLoginDelegate?.screenlet?(self,
				onLoginResponseUserAttributes: userAttributes)
		}
	}

}
