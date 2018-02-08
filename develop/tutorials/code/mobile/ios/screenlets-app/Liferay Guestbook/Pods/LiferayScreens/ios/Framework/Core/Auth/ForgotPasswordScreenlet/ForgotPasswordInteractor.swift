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

class ForgotPasswordInteractor: ServerConnectorInteractor {

	var resultPasswordSent: Bool?

	// MARK: ServerConnectorInteractor

	override func createConnector() -> ForgotPasswordBaseLiferayConnector? {
		let screenlet = self.screenlet as! ForgotPasswordScreenlet

		if screenlet.anonymousApiUserName == nil || screenlet.anonymousApiPassword == nil {
			print("[ERROR] Anonymous credentials are required for this interaction\n")
			return nil
		}

		let connector: ForgotPasswordBaseLiferayConnector?

		switch BasicAuthMethod.create(screenlet.basicAuthMethod) {
			case .screenName:
				connector = LiferayServerContext.connectorFactory.createForgotPasswordByScreenNameConnector(
					viewModel: screenlet.viewModel,
					anonymousUsername: screenlet.anonymousApiUserName!,
					anonymousPassword: screenlet.anonymousApiPassword!)
			case .userId:
				connector = LiferayServerContext.connectorFactory.createForgotPasswordByUserIdConnector(
					viewModel: screenlet.viewModel,
					anonymousUsername: screenlet.anonymousApiUserName!,
					anonymousPassword: screenlet.anonymousApiPassword!)
			case .email:
				connector = LiferayServerContext.connectorFactory.createForgotPasswordByEmailConnector(
					viewModel: screenlet.viewModel,
					anonymousUsername: screenlet.anonymousApiUserName!,
					anonymousPassword: screenlet.anonymousApiPassword!)
		}

		connector!.companyId = screenlet.companyId

		return connector!
	}

	override func completedConnector(_ c: ServerConnector) {
		self.resultPasswordSent = (c as! ForgotPasswordBaseLiferayConnector).resultPasswordSent
	}

	// MARK: Interactor

	override func interactionResult() -> AnyObject? {
		return resultPasswordSent as AnyObject?
	}

}
