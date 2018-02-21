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
	import KeychainAccess
#endif

@objc(BaseCredentialsStoreKeyChain)
open class BaseCredentialsStoreKeyChain: NSObject, CredentialsStore {

	open var authentication: LRAuthentication?
	open var userAttributes: [String:AnyObject]?

	open dynamic func storeCredentials(
			_ session: LRSession?,
			userAttributes: [String:AnyObject]?) -> Bool {

		guard let auth = session?.authentication else {
			return false
		}
		guard !(userAttributes?.isEmpty ?? true) else {
			return false
		}

		let keychain = BaseCredentialsStoreKeyChain.keychain()

		do {
			try keychain.set(String(LiferayServerContext.companyId),
				key: "companyId")
			try keychain.set(String(LiferayServerContext.groupId),
				key: "groupId")

			let userData = try? JSONSerialization.data(withJSONObject: userAttributes!,
				options: [])
			if let userData = userData {
				try keychain.set(userData, key: "user_attributes")

				storeAuth(keychain: keychain, auth: auth)

				return true
			}
		}
		catch {
			do {
				try keychain.remove("companyId")
				try keychain.remove("groupId")
				try keychain.remove("user_attributes")
			}
			catch {
			}
		}

		return false
	}

	open dynamic func removeStoredCredentials() -> Bool {
		let keychain = BaseCredentialsStoreKeyChain.keychain()

		do {
			try keychain.removeAll()
		}
		catch {
			return false
		}

		return true
	}

	open func loadStoredCredentials() -> Bool {
		return loadStoredCredentials(shouldLoadServer: false)
	}

	open func loadStoredCredentialsAndServer() -> Bool {
		return loadStoredCredentials(shouldLoadServer: true)
	}

	open func storeAuth(keychain: Keychain, auth: LRAuthentication) {
		fatalError("This method must be overriden")
	}

	open func loadAuth(keychain: Keychain) -> LRAuthentication? {
		fatalError("This method must be overriden")
	}

	open class func storedAuthType() -> AuthType? {
		guard let authType = try? keychain().get("auth_type") else {
			return nil
		}
		guard let authTypeValue = authType else {
			return nil
		}

		return AuthTypeFromString(authTypeValue)
	}

	open class func keychain() -> Keychain {
		let url = URL(string: LiferayServerContext.server)!
		return Keychain(server: url, protocolType: .https)
	}

	// MARK: Private methods

	private func loadStoredCredentials(shouldLoadServer: Bool) -> Bool {
		let keychain = BaseCredentialsStoreKeyChain.keychain()

		let companyId = try? keychain.get("companyId").flatMap { Int64($0) }
		let groupId = try? keychain.get("groupId").flatMap { Int64($0) }

		if shouldLoadServer {
			if let companyId = companyId ?? nil {
				LiferayServerContext.companyId = companyId
			}

			if let groupId = groupId ?? nil {
				LiferayServerContext.groupId = groupId
			}

		}
		else {
			if (companyId ?? 0) != LiferayServerContext.companyId
				|| (groupId ?? 0) != LiferayServerContext.groupId {
				return false
			}
		}

		guard let userData = try? keychain.getData("user_attributes") else {
			return false
		}

		if let userData = userData {
			let json = try? JSONSerialization.jsonObject(with: userData, options: [])

			userAttributes = json as? [String:AnyObject]
			authentication = loadAuth(keychain: keychain)

			return (authentication != nil && userAttributes != nil)
		}

		return false
	}
}
