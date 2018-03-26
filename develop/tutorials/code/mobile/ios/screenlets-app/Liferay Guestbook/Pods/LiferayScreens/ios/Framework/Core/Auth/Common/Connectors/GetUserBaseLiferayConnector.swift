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

open class GetUserBaseLiferayConnector: ServerConnector {

	open var resultUserAttributes: [String:AnyObject]?

	open var userName: String?
	open var password: String?

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if !SessionContext.isLoggedIn {
			if userName == nil {
				return ValidationError("login-screenlet", "undefined-username")
			}

			if password == nil {
				return ValidationError("login-screenlet", "undefined-password")
			}
		}

		return error
	}

	override open func createSession() -> LRSession? {
		if SessionContext.isLoggedIn {
			return SessionContext.currentContext?.createRequestSession()
		}
		guard let userName = userName, let password = password else {
			return nil
		}

		return LRSession(
				server: LiferayServerContext.server,
				authentication: LRBasicAuthentication(
						username: userName,
						password: password))
	}

	override open func doRun(session: LRSession) {
		do {
			let result = try sendGetUserRequest(session)

			if result["userId"] == nil {
				lastError = NSError.errorWithCause(.invalidServerResponse,
				                                   message: "No user found.")
				resultUserAttributes = nil
			}
			else {
				lastError = nil
				resultUserAttributes = extractUserAttributes(result)
			}
		}
		catch (let error as NSError) {
			lastError = error
			resultUserAttributes = nil
		}
	}

	// MARK: Internal methods

	@discardableResult
	internal func loginWithResult() -> Bool {
		guard let userAttributes = resultUserAttributes else {
			return false
		}
		guard let userName = self.userName else {
			return false
		}
		guard let password = self.password else {
			return false
		}

		SessionContext.currentContext?.removeStoredCredentials()

		SessionContext.loginWithBasic(
			username: userName,
			password: password,
			userAttributes: userAttributes)

		return true
	}

	internal func extractUserAttributes(_ result: NSDictionary?) -> [String: AnyObject]? {
		guard var userAttributes = result as? [String: AnyObject] else {
			return nil
		}

		// LSR-745: Liferay 7 sends all fields as string.
		// Some were numbers in Liferay 6.2

		let stringFields = ["userId", "companyId", "portraitId", "contactId"]

		stringFields.forEach {
			if let userId = userAttributes[$0] as? String {
				userAttributes[$0] = userId.asNumber!
			}
		}

		return userAttributes
	}

	// MARK: Template methods

	open func sendGetUserRequest(_ session: LRSession)
			throws -> NSDictionary {
		fatalError("sendGetUserRequest must be overriden")
	}

}
