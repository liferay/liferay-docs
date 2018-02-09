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
	import LRMobileSDK
	import LROAuth
	import KeychainAccess
#endif

open class OAuthCredentialsStoreKeyChain: BaseCredentialsStoreKeyChain {

	override open func storeAuth(keychain: Keychain, auth: LRAuthentication) {
		let oauth = auth as! LROAuth

		do {
			try keychain.set(StringFromAuthType(AuthType.oAuth), key: "auth_type")
			try keychain.set(oauth.config.consumerKey, key: "oauth_consumer_key")
			try keychain.set(oauth.config.consumerSecret, key: "oauth_consumer_secret")
			try keychain.set(oauth.config.token, key: "oauth_token")
			try keychain.set(oauth.config.tokenSecret, key: "oauth_token_secret")
		}
		catch {
		}
	}

	override open func loadAuth(keychain: Keychain) -> LRAuthentication? {

		let consumerKey = try? keychain.get("oauth_consumer_key")
		let consumerSecret = try? keychain.get("oauth_consumer_secret")
		let token = try? keychain.get("oauth_token")
		let tokenSecret = try? keychain.get("oauth_token_secret")

		if let consumerKey = consumerKey,
				let consumerSecret = consumerSecret,
				let token = token,
				let tokenSecret = tokenSecret {

			return LROAuth(
				consumerKey: consumerKey,
				consumerSecret: consumerSecret,
				token: token,
				tokenSecret: tokenSecret)
		}

		return nil
	}

}
