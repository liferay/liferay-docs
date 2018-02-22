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

open class GetUserByScreenNameLiferayConnector: GetUserBaseLiferayConnector {

	open let companyId: Int64
	open let screenName: String

	// MARK: Initializers

	public init(companyId: Int64, screenName: String) {
		self.companyId = companyId
		self.screenName = screenName

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if screenName == "" {
				return ValidationError("login-screenlet", "validation-screenname")
			}
		}

		return error
	}

}

open class GetUserByScreenNameLiferay62Connector: GetUserByScreenNameLiferayConnector {

	// MARK: GetUserByScreenNameLiferayConnector

	override public init(companyId: Int64, screenName: String) {
		super.init(companyId: companyId, screenName: screenName)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let companyId = (self.companyId != 0) ? self.companyId : LiferayServerContext.companyId

			let service = LRUserService_v62(session: session)

			return try service?.getUserByScreenName(withCompanyId: companyId,
				screenName: screenName) as NSDictionary? ?? [:]
	}

}

open class GetUserByScreenNameLiferay70Connector: GetUserByScreenNameLiferayConnector {

	// MARK: GetUserByScreenNameLiferayConnector

	override public init(companyId: Int64, screenName: String) {
		super.init(companyId: companyId, screenName: screenName)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let companyId = (self.companyId != 0) ? self.companyId : LiferayServerContext.companyId

			let service = LRUserService_v7(session: session)

			return try service?.getUserByScreenName(withCompanyId: companyId,
				screenName: screenName) as NSDictionary? ?? [:]
	}

}
