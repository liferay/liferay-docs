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

open class LoginBasicInteractor: ServerConnectorInteractor {

	open let companyId: Int64

	open let screenName: String?

	open let emailAddress: String?

	open let userId: Int64?

	open let password: String

	open let authMethod: BasicAuthMethod

	open var resultUserAttributes: [String:AnyObject]?

	// MARK: Initializers

	public init(loginScreenlet: LoginScreenlet) {
		companyId = loginScreenlet.companyId != 0
			? loginScreenlet.companyId : LiferayServerContext.companyId

		authMethod = BasicAuthMethod.create(loginScreenlet.basicAuthMethod)

		switch authMethod {
		case .screenName:
			screenName = loginScreenlet.viewModel.userName ?? ""
			emailAddress = nil
			userId = nil
		case .userId:
			userId = loginScreenlet.viewModel.userName?.asNumber?.int64Value ?? 0
			emailAddress = nil
			screenName = nil
		default:
			emailAddress = loginScreenlet.viewModel.userName ?? ""
			userId = nil
			screenName = nil
		}

		password = loginScreenlet.viewModel.password ?? ""

		super.init(screenlet: loginScreenlet)
	}

	public init(companyId: Int64, screenName: String, password: String) {
		self.authMethod = BasicAuthMethod.screenName
		self.companyId = (companyId != 0) ? companyId : LiferayServerContext.companyId
		self.screenName = screenName
		self.password = password
		self.emailAddress = nil
		self.userId = nil

		super.init(screenlet: nil)
	}

	public init(companyId: Int64, emailAddress: String, password: String) {
		self.authMethod = BasicAuthMethod.email
		self.companyId = (companyId != 0) ? companyId : LiferayServerContext.companyId
		self.emailAddress = emailAddress
		self.password = password
		self.screenName = nil
		self.userId = nil

		super.init(screenlet: nil)
	}

	public init(userId: Int64, password: String) {
		self.authMethod = BasicAuthMethod.userId
		self.companyId = 0
		self.userId = userId
		self.password = password
		self.emailAddress = nil
		self.screenName = nil

		super.init(screenlet: nil)
	}

	// MARK: ServerConnectorInteractor

	override open func createConnector() -> GetUserBaseLiferayConnector? {
		let connector: GetUserBaseLiferayConnector?

		if let screenName = self.screenName {
			connector = LiferayServerContext.connectorFactory.createLoginByScreenNameConnector(
				companyId: companyId,
				screenName: screenName,
				password: password)
		}
		else if let userId = self.userId {
			connector = LiferayServerContext.connectorFactory.createLoginByUserIdConnector(
				userId: userId,
				password: password)
		}
		else if let emailAddress = self.emailAddress {
			connector = LiferayServerContext.connectorFactory.createLoginByEmailConnector(
				companyId: companyId,
				emailAddress: emailAddress,
				password: password)
		}
		else {
			connector = nil
		}

		return connector
	}

	override open func completedConnector(_ c: ServerConnector) {
		self.resultUserAttributes = (c as! GetUserBaseLiferayConnector).resultUserAttributes
	}

}
