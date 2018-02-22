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
import LRMobileSDK

open class GetUserByUserIdLiferayConnector: GetUserBaseLiferayConnector {

	open let userId: Int64

	// MARK: Initializers

	public init(userId: Int64) {
		self.userId = userId

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if userId == 0 {
				return ValidationError("login-screenlet", "undefined-user")
			}
		}

		return error
	}

}

open class GetUserByUserIdLiferay62Connector: GetUserByUserIdLiferayConnector {

	// MARK: GetUserByUserIdLiferayConnector

	override public init(userId: Int64) {
		super.init(userId: userId)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let service = LRUserService_v62(session: session)

			return try service?.getUserById(withUserId: userId) as NSDictionary? ?? [:]
	}

}

open class GetUserByUserIdLiferay70Connector: GetUserByUserIdLiferayConnector {

	// MARK: GetUserByUserIdLiferayConnector

	override public init(userId: Int64) {
		super.init(userId: userId)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let service = LRUserService_v7(session: session)

			return try service?.getUserById(withUserId: userId) as NSDictionary? ?? [:]
	}

}
