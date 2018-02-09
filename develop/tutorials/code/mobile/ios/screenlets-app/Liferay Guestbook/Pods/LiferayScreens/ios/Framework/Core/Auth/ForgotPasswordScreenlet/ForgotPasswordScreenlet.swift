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

/// The ForgotPasswordScreenletDelegate protocol defines some methods that you use to manage the
/// ForgotPasswordScreenlet events. All of them are optional.
@objc(ForgotPasswordScreenletDelegate)
public protocol ForgotPasswordScreenletDelegate: BaseScreenletDelegate {

	/// Called when a password reset email is successfully sent.
	///
	/// - Parameters:
	///   - screenlet: Forgot password screenlet instance.
	///   - passwordSent: Indicates whether the email contains the new password
	/// or a password reset link.
	@objc optional func screenlet(_ screenlet: ForgotPasswordScreenlet,
			onForgotPasswordSent passwordSent: Bool)

	/// Called when an error occurs in the process.
	/// The NSError object describes the error.
	///
	/// - Parameters:
	///   - screenlet: Forgot password screenlet instance.
	///   - error: Error while requesting password.
	@objc optional func screenlet(_ screenlet: ForgotPasswordScreenlet,
			onForgotPasswordError error: NSError)

}

/// The Forgot Password Screenlet sends emails to registered users with their new passwords or 
/// password reset links, depending on the server configuration.
/// The available authentication methods are:
///	* Email address
///	* Screen name
///	* User id
@objc(ForgotPasswordScreenlet)
open class ForgotPasswordScreenlet: BaseScreenlet, BasicAuthBasedType, AnonymousBasicAuthType {

	// MARK: Inspectables

	/// The user name, email address, or userId (depending on the portal’s authentication method) 
	/// to use for authenticating the request.
	@IBInspectable open var anonymousApiUserName: String? = "test@liferay.com"

	/// The password to use to authenticate the request.
	@IBInspectable open var anonymousApiPassword: String? = "test"

	/// The authentication method that is presented to the user. This can be email, screenName, or 
	/// userId.
	@IBInspectable open var basicAuthMethod: String? = BasicAuthMethod.email.rawValue {
		didSet {
			(screenletView as? BasicAuthBasedType)?.basicAuthMethod = basicAuthMethod
		}
	}

	/// When set, the authentication is done for a user within the specified company. 
	/// If the value is 0, the company specified in LiferayServerContext is used.
	@IBInspectable var companyId: Int64 = 0

	// MARK: Public properties

	open var forgotPasswordDelegate: ForgotPasswordScreenletDelegate? {
		return delegate as? ForgotPasswordScreenletDelegate
	}

	open var viewModel: ForgotPasswordViewModel {
		return screenletView as! ForgotPasswordViewModel
	}

	open var saveCredentials: Bool {
		get { return false }
		set {}
	}

	// MARK: BaseScreenlet

	override open func onCreated() {
		super.onCreated()

		(screenletView as? BasicAuthBasedType)?.basicAuthMethod = basicAuthMethod

		if let userName = SessionContext.currentContext?.basicAuthUsername {
			viewModel.userName = userName
		}
	}

	override open func createInteractor(name: String, sender: AnyObject?) -> Interactor? {
		let interactor = ForgotPasswordInteractor(screenlet: self)

		interactor.onSuccess = {
			self.forgotPasswordDelegate?.screenlet?(self,
					onForgotPasswordSent: interactor.resultPasswordSent!)
		}

		interactor.onFailure = {
			self.forgotPasswordDelegate?.screenlet?(self, onForgotPasswordError: $0)
		}

		return interactor
	}

}
