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

@objc public protocol ScreensFactory {

	func createSessionContext(
		session: LRSession,
		attributes: [String: AnyObject],
		store: CredentialsStore) -> SessionContext

	func createCacheManager(session: LRSession, userId: Int64) -> CacheManager

	func createCredentialsStore(_ authType: AuthType) -> CredentialsStore

}

@objc open class ScreensFactoryImpl: NSObject, ScreensFactory {

	open func createSessionContext(
			session: LRSession,
			attributes: [String: AnyObject],
			store: CredentialsStore) -> SessionContext {
		return SessionContext(
			session: session,
			attributes: attributes,
			store: store)
	}

	open func createCacheManager(session: LRSession, userId: Int64) -> CacheManager {
		return CacheManager(session: session, userId: userId)
	}

	open func createCredentialsStore(_ authType: AuthType) -> CredentialsStore {
		switch authType {
		case .basic:
			return BasicCredentialsStoreKeyChain()
		case .oAuth:
			return OAuthCredentialsStoreKeyChain()
		case .cookie:
			return CookieCredentialsStoreKeyChain()
		}
	}

}
