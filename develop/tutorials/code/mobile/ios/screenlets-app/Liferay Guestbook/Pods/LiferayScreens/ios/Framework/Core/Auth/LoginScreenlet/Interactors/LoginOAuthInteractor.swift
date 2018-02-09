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
import LROAuth
import LRMobileSDK

class LoginOAuthInteractor: Interactor, LRCallback {

	var resultUserAttributes: [String:AnyObject]?

	let consumerKey: String

	let consumerSecret: String

	var webViewController: OAuthWebViewController?

	var OAuthConfig: LROAuthConfig?

	var OAuthSession: LRSession?

	// MARK: Initializers

	init(screenlet: BaseScreenlet?,
			consumerKey: String,
			consumerSecret: String) {

		self.consumerKey = consumerKey
		self.consumerSecret = consumerSecret

		super.init(screenlet: screenlet)
	}

	// MARK: Interactor

	override func start() -> Bool {
		if screenlet?.presentingViewController == nil {
			print("ERROR: You need to set the presentingViewController before start OAuthInteractor\n")

			return false
		}

		OAuthConfig = LROAuthConfig(
			server: LiferayServerContext.server,
			consumerKey: consumerKey,
			consumerSecret: consumerSecret,
			callbackURL: "screens://oauth_callback")

		return requestToken()
	}

	// MARK: Private methods

	fileprivate func requestToken() -> Bool {
		LRRequestToken.requestToken(
				with: OAuthConfig,
				onSuccess: {
					if let URL = URL(string: ($0?.authorizeTokenURL)!) {
						self.showWebView(URL)
					}
					else {
						let err = NSError.errorWithCause(.invalidServerResponse,
								message: "OAuth's authorizeTokenURL is not valid: \($0?.authorizeTokenURL)\n")
						self.onFailure?(err)
					}
				},
				onFailure: { err in
					print("ERROR: Cannot get request token\n")
					self.onFailure?(err as! NSError)
				}
		)

		return true
	}

	fileprivate func showWebView(_ URL: Foundation.URL) {
		webViewController = OAuthWebViewController(
				URL: URL,
				themeName: screenlet?.themeName ?? "default")

		webViewController!.onAuthorized = { [weak webViewController] OAuthVerifier in
			webViewController?.dismiss(animated: true, completion: nil)

			self.OAuthConfig?.verifier = OAuthVerifier

			self.requestAccessToken()
		}

		if let vc = screenlet?.presentingViewController {
			vc.present(webViewController!, animated: true, completion: nil)
		}
	}

	fileprivate func requestAccessToken() {
		LRAccessToken.accessToken(
				with: OAuthConfig,
				onSuccess: { config in
					self.requestUserAttributes(config!)
				},
				onFailure: { err in
					print("ERROR: Cannot get access token\n")
					self.onFailure?(err as! NSError)
				}
		)
	}

	fileprivate func requestUserAttributes(_ config: LROAuthConfig) {
		OAuthSession = LRSession(
				server: LiferayServerContext.server,
				authentication: LROAuth(config: config))
		OAuthSession!.callback = self

		switch LiferayServerContext.serverVersion {
		case .v62:
			let srv = LRScreensuserService_v62(session: OAuthSession!)

			_ = try? srv?.getCurrentUser()

		case .v70:
			let srv = LRUserService_v7(session: OAuthSession!)

			_ = try? srv?.getCurrentUser()
		}

	}

	func onFailure(_ error: Error!) {
		onFailure?(error as NSError)
	}

	func onSuccess(_ result: Any!) {
		if let resultValue = result as? [String:AnyObject] {
			resultUserAttributes = resultValue

			SessionContext.loginWithOAuth(
					authentication: OAuthSession!.authentication as! LROAuth,
					userAttributes: resultValue)

			onSuccess?()
		}
	}

}
