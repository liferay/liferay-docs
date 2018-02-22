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

open class GetUserByEmailLiferayConnector: GetUserBaseLiferayConnector {

	open let companyId: Int64
	open let emailAddress: String

	// MARK: Initializers

	public init(companyId: Int64, emailAddress: String) {
		self.companyId = companyId
		self.emailAddress = emailAddress

		super.init()
	}

	// MARK: ServerConnector

	override open func validateData() -> ValidationError? {
		let error = super.validateData()

		if error == nil {
			if emailAddress == "" {
				return ValidationError("login-screenlet", "validation-email")
			}
		}

		return error
	}

}

open class GetUserByEmailLiferay62Connector: GetUserByEmailLiferayConnector {

	// MARK: GetUserByEmailLiferayConnector

	override public init(companyId: Int64, emailAddress: String) {
		super.init(companyId: companyId, emailAddress: emailAddress)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let companyId = (self.companyId != 0) ? self.companyId : LiferayServerContext.companyId

			let service = LRUserService_v62(session: session)

			return try service?.getUserByEmailAddress(withCompanyId: companyId,
				emailAddress: emailAddress) as NSDictionary? ?? [:]
	}

}

open class GetUserByEmailLiferay70Connector: GetUserByEmailLiferayConnector {

	// MARK: GetUserByEmailLiferayConnector

	override public init(companyId: Int64, emailAddress: String) {
		super.init(companyId: companyId, emailAddress: emailAddress)
	}

	// MARK: GetUserBaseLiferayConnector

	override open func sendGetUserRequest(_ session: LRSession)
		throws -> NSDictionary {

			let companyId = (self.companyId != 0) ? self.companyId : LiferayServerContext.companyId

			let service = LRUserService_v7(session: session)

			return try service?.getUserByEmailAddress(withCompanyId: companyId,
				emailAddress: emailAddress) as NSDictionary? ?? [:]
	}

}
