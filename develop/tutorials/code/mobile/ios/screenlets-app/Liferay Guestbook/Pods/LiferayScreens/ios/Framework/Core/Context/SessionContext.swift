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
import Foundation

#if LIFERAY_SCREENS_FRAMEWORK
	import LRMobileSDK
	import LROAuth
#endif

@objc(SessionContext)
open class SessionContext: NSObject {

	open static var currentContext: SessionContext?
	open static var challengeResolver: ChallengeResolver?

	open let session: LRSession
	open let user: User

	open let cacheManager: CacheManager
	open var credentialsStorage: CredentialsStorage

	@available(*, deprecated: 2.0.1, message: "Use public property user")
	open var userId: Int64 {
		return user.userId
	}

	@available(*, deprecated: 2.0.1, message: "Access it throught user.attributes")
	open var userAttributes: [String : AnyObject] {
		return user.attributes
	}

	public init(session: LRSession, attributes: [String: AnyObject], store: CredentialsStore) {
		self.session = session
		self.user = User(attributes: attributes)

		let userId = user.userId

		cacheManager = LiferayServerContext.factory.createCacheManager(
			session: session,
			userId: userId)

		credentialsStorage = CredentialsStorage(store: store)

		super.init()
	}

	// MARK: Public properties

	open class var isLoggedIn: Bool {
		return currentContext?.session != nil
	}

	open var basicAuthUsername: String? {
		if let auth = session.authentication as? LRBasicAuthentication {
			return auth.username
		}

		if let auth = session.authentication as? LRCookieAuthentication {
			return auth.username
		}

		return nil
	}

	open var basicAuthPassword: String? {
		if let auth = session.authentication as? LRBasicAuthentication {
			return auth.password
		}

		if let auth = session.authentication as? LRCookieAuthentication {
			return auth.password
		}

		return nil
	}

	// MARK: Public methods

	open class func createEphemeralBasicSession(
			_ userName: String,
			_ password: String) -> LRSession {
		return LRSession(
			server: LiferayServerContext.server,
			authentication: LRBasicAuthentication(
				username: userName,
				password: password))
	}

	@discardableResult
	open class func loginWithBasic(
			username: String,
			password: String,
			userAttributes: [String:AnyObject]) -> LRSession {

		let authentication = LRBasicAuthentication(
			username: username,
			password: password)

		let session = LRSession(
			server: LiferayServerContext.server,
			authentication: authentication)

		let store = LiferayServerContext.factory.createCredentialsStore(AuthType.basic)

		SessionContext.currentContext =
			LiferayServerContext.factory.createSessionContext(
				session: session!,
				attributes: userAttributes,
				store: store)

		return session!
	}

	@discardableResult
	open class func loginWithOAuth(
			authentication: LROAuth,
			userAttributes: [String:AnyObject]) -> LRSession {

		let session = LRSession(
			server: LiferayServerContext.server,
			authentication: authentication)

		let store = LiferayServerContext.factory.createCredentialsStore(AuthType.oAuth)

		SessionContext.currentContext =
			LiferayServerContext.factory.createSessionContext(
				session: session!,
				attributes: userAttributes,
				store: store)

		return session!
	}

	@discardableResult
	open class func loginWithCookie(
		authentication: LRCookieAuthentication,
		userAttributes: [String:AnyObject]) -> LRSession {

		let session = LRSession(
			server: LiferayServerContext.server,
			authentication: authentication)

		let store = LiferayServerContext.factory.createCredentialsStore(AuthType.cookie)

		SessionContext.currentContext =
			LiferayServerContext.factory.createSessionContext(
				session: session!,
				attributes: userAttributes,
				store: store)

		return session!
	}

	open class func reloadCookieAuth(session: LRSession? = nil, callback: LRCookieBlockCallback) {
		var session = session
		if session == nil {
			session = SessionContext.currentContext?.createRequestSession()
		}

		let currentAttrs = SessionContext.currentContext?.user.attributes ?? [:]

		var challenge: ((URLAuthenticationChallenge?,
			((URLSession.AuthChallengeDisposition, URLCredential?) -> Swift.Void)?) -> Swift.Void)? = nil

		if let challengeResolver = SessionContext.challengeResolver {
			challenge = { challenge, completion in
				challengeResolver(challenge!, completion!)
			}
		}

		LRCookieSignIn.signIn(with: session, callback: LRCookieBlockCallback { session, error in

			if let session = session {
				SessionContext.loginWithCookie(authentication: session.authentication as! LRCookieAuthentication,
						userAttributes: currentAttrs)

				callback.callback(session, nil)
			}
			else {
				callback.callback(nil, error)
			}
		}, challenge: challenge)
	}

	open func createRequestSession() -> LRSession {
		return LRSession(session: session)
	}

	open func relogin(_ completed: (([String:AnyObject]?) -> Void)?) -> Bool {
		if session.authentication is LRBasicAuthentication {
			return reloginBasic(completed)
		}
		else if session.authentication is LROAuth {
			return reloginOAuth(completed)
		}
		else if session.authentication is LRCookieAuthentication {
			return reloginCookie(completed)
		}

		return false
	}

	open func reloginBasic(_ completed: (([String:AnyObject]?) -> Void)?) -> Bool {
		guard let userName = self.basicAuthUsername,
				let password = self.basicAuthPassword else {
			completed?(nil)
			return false
		}

		return refreshUserAttributes { attributes in
			if let attributes = attributes {
				SessionContext.loginWithBasic(
					username: userName,
					password: password,
					userAttributes: attributes)
			}

			completed?(attributes)
		}
	}

	open func reloginOAuth(_ completed: (([String:AnyObject]?) -> Void)?) -> Bool {
		guard let auth = self.session.authentication as? LROAuth else {
			completed?(nil)
			return false
		}

		return refreshUserAttributes { attributes in
			if let attributes = attributes {
				SessionContext.loginWithOAuth(authentication: auth, userAttributes: attributes)
			}
			else {
				SessionContext.logout()
			}

			completed?(attributes)
		}
	}

	open func reloginCookie(_ completed: (([String:AnyObject]?) -> Void)?) -> Bool {
		guard session.authentication is LRCookieAuthentication else {
			completed?(nil)
			return false
		}

		SessionContext.reloadCookieAuth(session: self.session, callback: LRCookieBlockCallback { (session, error) in
			guard session != nil, let auth = session?.authentication as? LRCookieAuthentication else {
				print("Error reloading the cookie auth\(error!)")
				completed?(nil)
				return
			}

			_ = SessionContext.currentContext?.refreshUserAttributes { attributes in
				if let attributes = attributes {
					SessionContext.loginWithCookie(authentication: auth, userAttributes: attributes)
				}
				else {
					SessionContext.logout()
				}

				completed?(attributes)
			}

		})

		return true
	}

	open func refreshUserAttributes(_ completed: (([String:AnyObject]?) -> Void)?) -> Bool {
		let session = self.createRequestSession()

		session.callback = LRBlockCallback(
			success: { obj in
				guard let attributes = obj as? [String:AnyObject] else {
					SessionContext.logout()
					completed?(nil)
					return
				}

				completed?(attributes)
			},
			failure: { _ in
				completed?(nil)
		})

		switch LiferayServerContext.serverVersion {
		case .v62:
			let srv = LRScreensuserService_v62(session: session)

			_ = try? srv?.getCurrentUser()

		case .v70:
			let srv = LRUserService_v7(session: session)

			_ = try? srv?.getCurrentUser()
		}

		return true
	}

	open class func logout() {
		if SessionContext.currentContext?.session.authentication as? LRCookieAuthentication != nil {
			HTTPCookieStorage.shared.removeCookies(since: .distantPast)
		}
		SessionContext.currentContext = nil
	}

	@discardableResult
	open func storeCredentials() -> Bool {
		return credentialsStorage.store(
				session: session,
				userAttributes: user.attributes)
	}

	@discardableResult
	open func removeStoredCredentials() -> Bool {
		return credentialsStorage.remove()
	}

	@discardableResult
	open class func loadStoredCredentials() -> Bool {
		return loadStoredCredentials(shouldLoadServer: false)
	}

	@discardableResult
	open class func loadStoredCredentialsAndServer() -> Bool {
		return loadStoredCredentials(shouldLoadServer: true)
	}

	private class func loadStoredCredentials(shouldLoadServer: Bool) -> Bool {
		guard let storage = CredentialsStorage.createFromStoredAuthType() else {
			return false
		}

		return loadStoredCredentials(storage, shouldLoadServer: shouldLoadServer)
	}

	@discardableResult
	open class func loadStoredCredentials(_ storage: CredentialsStorage, shouldLoadServer: Bool = false) -> Bool {
		guard let result = storage.load(shouldLoadServer: shouldLoadServer) else {
			return false
		}
		guard result.session.server != nil else {
			return false
		}

		SessionContext.currentContext =
			LiferayServerContext.factory.createSessionContext(
				session: result.session,
				attributes: result.userAttributes,
				store: storage.credentialsStore)

		return true
	}

	// Deprecated. Will be removed in next version
	open class func createSessionFromCurrentSession() -> LRSession? {
		return SessionContext.currentContext?.createRequestSession()
	}

}
